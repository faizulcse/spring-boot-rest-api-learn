package com.aapon.springbootrestapilearn.monitoring;

import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class InFlightRequestFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(InFlightRequestFilter.class);
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
                String status = response.getStatus() < 400 ? "✅" : "❌";
                String queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
                log.info(String.format("%s [status: %s] %-6s %s", status, response.getStatus(), request.getMethod(), request.getRequestURL() + queryString));
            }
        } finally {
            counter.decrement();
        }
    }
}
