package com.aapon.springbootrestapilearn.monitoring;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class InFlightRequestFilter extends OncePerRequestFilter {

    private final InFlightRequestCounter counter;

    public InFlightRequestFilter(InFlightRequestCounter counter) {
        this.counter = counter;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path != null && path.endsWith("/internal/inflight");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        counter.increment();
        try {
            filterChain.doFilter(request, response);
        } finally {
            counter.decrement();
        }
    }
}
