package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoadTestApiController {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @GetMapping(ApiEndpoints.APP_INFO)
    public ResponseEntity<String> appInfoApi() {
        JsonObject appInfo = new JsonObject();
        appInfo.addProperty("name", appName);
        appInfo.addProperty("version", appVersion);
        appInfo.addProperty("port", port);
        appInfo.addProperty("context_path", contextPath);

        return ResponseEntity.status(HttpStatus.OK).body(appInfo.toString());
    }

    @GetMapping(ApiEndpoints.WITH_HEADER_DATA)
    public synchronized ResponseEntity<String> apiWithHeaderData(@RequestHeader("scenario_id") String scenario_id) {
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.WITH_PATH_DATA)
    public synchronized ResponseEntity<String> apiWithPathData(@PathVariable String scenario_id) {
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.NORMAL)
    public ResponseEntity<String> normalApi() {
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.NORMAL_SYNC)
    public synchronized ResponseEntity<String> normalSyncApi() {
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.FIXED_DELAY)
    public ResponseEntity<String> fixedDelayApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.RANDOM_DELAY)
    public ResponseEntity<String> randomDelayApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.FIXED_DELAY_SYNC)
    public synchronized ResponseEntity<String> fixedDelaySyncApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.RANDOM_DELAY_SYNC)
    public synchronized ResponseEntity<String> randomDelaySyncApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).body(new JsonObject().toString());
    }

    @PostMapping(ApiEndpoints.PRINT_BODY)
    public ResponseEntity<String> printApiBody(@RequestBody String data) {
        return ResponseEntity.ok().body(data);
    }
}
