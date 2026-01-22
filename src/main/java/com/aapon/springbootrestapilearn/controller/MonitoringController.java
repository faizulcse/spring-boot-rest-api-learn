package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.filter.RequestFilter;
import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    private final RequestFilter requestFilter;

    public MonitoringController(RequestFilter rateLimitingFilter) {
        this.requestFilter = rateLimitingFilter;
    }

    @GetMapping(ApiEndpoints.APP_INFO)
    public ResponseEntity<String> appInfoApi() {
        JsonObject appInfo = new JsonObject();
        appInfo.addProperty("name", appName);
        appInfo.addProperty("version", appVersion);
        appInfo.addProperty("port", port);
        appInfo.addProperty("context_path", contextPath);

        return ResponseEntity.status(HttpStatus.OK).body(appInfo.toString());
    }

    @GetMapping(ApiEndpoints.INFLIGHT)
    public ResponseEntity<String> inflight() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("in_flight", requestFilter.getInFlightCount());
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }
}
