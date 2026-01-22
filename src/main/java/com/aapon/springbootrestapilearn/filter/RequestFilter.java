package com.aapon.springbootrestapilearn.filter;

import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.google.gson.JsonObject;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter extends OncePerRequestFilter {
    private static long count = 1;
    private final AtomicInteger inFlight = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);
    private final boolean LOGGER_ENABLED = System.getenv("LOGGER_ENABLED") != null && System.getenv("LOGGER_ENABLED").equals("true");
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private final boolean enabled;
    private final long capacity;
    private final long refillTokens;
    private final Duration refillPeriod;

    public RequestFilter(
            @Value("${app.ratelimit.enabled:true}") boolean enabled,
            @Value("${app.ratelimit.capacity:60}") long capacity,
            @Value("${app.ratelimit.refill-tokens:60}") long refillTokens,
            @Value("${app.ratelimit.refill-seconds:60}") long refillSeconds) {
        this.enabled = enabled;
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillPeriod = Duration.ofSeconds(refillSeconds);
    }

    public int getInFlightCount() {
        return inFlight.get();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path != null && path.endsWith(ApiEndpoints.INFLIGHT);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            inFlight.incrementAndGet();

            if (!enabled) {
                filterChain.doFilter(request, response);
                return;
            }

            String key = resolveClientKey(request);
            Bucket bucket = buckets.computeIfAbsent(key, k -> newBucket());

            ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
            if (probe.isConsumed()) {
                response.setHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
                filterChain.doFilter(request, response);
                return;
            }

            long waitForNanos = probe.getNanosToWaitForRefill();
            long retryAfterSeconds = Math.max(1, Duration.ofNanos(waitForNanos).getSeconds());

            response.setStatus(429);
            response.setHeader("Retry-After", String.valueOf(retryAfterSeconds));
            response.setContentType("application/json");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("message", "Too many requests");
            response.getWriter().write(jsonObject.toString());
        } finally {
            inFlight.decrementAndGet();
            if (LOGGER_ENABLED) {
                String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                String status = response.getStatus() < 400 ? "✅" : "❌";
                String queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
                String msg = String.format(
                        "%-10s [%s]   %s [status: %s] %-6s %s",
                        count++,
                        timeStamp,
                        status,
                        response.getStatus(),
                        request.getMethod(),
                        request.getRequestURL() + queryString
                );
                System.out.println(msg);
            }
        }
    }

    private Bucket newBucket() {
        Refill refill = Refill.intervally(refillTokens, refillPeriod);
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    private String resolveClientKey(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
