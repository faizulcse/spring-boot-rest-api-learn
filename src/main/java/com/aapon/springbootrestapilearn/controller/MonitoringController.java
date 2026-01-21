package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.filter.RequestFilter;
import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    private final RequestFilter requestFilter;

    public MonitoringController(RequestFilter rateLimitingFilter) {
        this.requestFilter = rateLimitingFilter;
    }

    @GetMapping(ApiEndpoints.INFLIGHT)
    public ResponseEntity<String> inflight() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("in_flight", requestFilter.getInFlightCount());
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }
}
