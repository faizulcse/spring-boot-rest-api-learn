package com.aapon.springbootrestapilearn.monitoring;

import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class InFlightRequestFilter extends OncePerRequestFilter {
    private static long count = 1;
    private final boolean LOGGER_ENABLED = System.getenv("LOGGER_ENABLED") != null && System.getenv("LOGGER_ENABLED").equals("true");

    private final InFlightRequestCounter counter;

    public InFlightRequestFilter(InFlightRequestCounter counter) {
        this.counter = counter;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path != null && path.endsWith(ApiEndpoints.INFLIGHT);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        counter.increment();
        try {
            filterChain.doFilter(request, response);
            if (LOGGER_ENABLED) {
                String api_url = String.format("%-6s %s %s", request.getMethod(), response.getStatus(), request.getRequestURL() + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
                System.out.printf("%-10s [%s] %s%n", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), api_url);
            }
        } finally {
            counter.decrement();
        }
    }
}
