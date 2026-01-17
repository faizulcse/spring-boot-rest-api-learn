package com.aapon.springbootrestapilearn.monitoring;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InFlightRequestCounter {

    private final AtomicInteger inFlight = new AtomicInteger(0);

    public int increment() {
        return inFlight.incrementAndGet();
    }

    public int decrement() {
        return inFlight.decrementAndGet();
    }

    public int get() {
        return inFlight.get();
    }
}
