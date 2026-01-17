package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.monitoring.InFlightRequestCounter;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    private final InFlightRequestCounter inFlightRequestCounter;

    public MonitoringController(InFlightRequestCounter inFlightRequestCounter) {
        this.inFlightRequestCounter = inFlightRequestCounter;
    }

    @GetMapping("/internal/inflight")
    public ResponseEntity<String> inflight() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("in_flight", inFlightRequestCounter.get());
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }
}
