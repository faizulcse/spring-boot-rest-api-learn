package com.aapon.springbootrestapilearn.monitoring;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InFlightRequestCounter {

    private final AtomicInteger inFlight = new AtomicInteger(0);

    public InFlightRequestCounter(MeterRegistry meterRegistry) {
        Gauge.builder("in_flight", inFlight, AtomicInteger::get)
                .register(meterRegistry);
    }

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
