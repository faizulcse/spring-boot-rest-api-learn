package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.aapon.springbootrestapilearn.utils.ApiLogger;
import com.google.gson.JsonObject;
import net.datafaker.Faker;
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
        ApiLogger.loggerGenerator(ApiEndpoints.APP_INFO);

        JsonObject appInfo = new JsonObject();
        appInfo.addProperty("name", appName);
        appInfo.addProperty("version", appVersion);
        appInfo.addProperty("port", port);
        appInfo.addProperty("context_path", contextPath);

        return ResponseEntity.status(HttpStatus.OK).body(appInfo.toString());
    }

    @GetMapping(ApiEndpoints.WITH_HEADER_DATA)
    public synchronized ResponseEntity<String> apiWithHeaderData(@RequestHeader("scenario_id") String scenario_id) {
        ApiLogger.loggerGenerator(ApiEndpoints.WITH_HEADER_DATA + "?scenario_id=" + scenario_id);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping(ApiEndpoints.WITH_PATH_DATA)
    public synchronized ResponseEntity<String> apiWithPathData(@PathVariable String scenario_id) {
        ApiLogger.loggerGenerator(ApiEndpoints.WITH_PATH_DATA.replace("{scenario_id}", scenario_id));
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping(ApiEndpoints.NORMAL)
    public ResponseEntity<String> normalApi() {
        ApiLogger.loggerGenerator(ApiEndpoints.NORMAL);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping(ApiEndpoints.NORMAL_SYNC)
    public synchronized ResponseEntity<String> normalSyncApi() {
        ApiLogger.loggerGenerator(ApiEndpoints.NORMAL_SYNC);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping(ApiEndpoints.FIXED_DELAY)
    public ResponseEntity<String> fixedDelayApi() throws InterruptedException {
        Thread.sleep(80000);
        ApiLogger.loggerGenerator(ApiEndpoints.FIXED_DELAY);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping(ApiEndpoints.RANDOM_DELAY)
    public ResponseEntity<String> randomDelayApi() throws InterruptedException {
        Thread.sleep(new Faker().number().numberBetween(1, 10) * 100L);
        ApiLogger.loggerGenerator(ApiEndpoints.RANDOM_DELAY);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping(ApiEndpoints.FIXED_DELAY_SYNC)
    public synchronized ResponseEntity<String> fixedDelaySyncApi() throws InterruptedException {
        Thread.sleep(999);
        ApiLogger.loggerGenerator(ApiEndpoints.FIXED_DELAY_SYNC);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping(ApiEndpoints.RANDOM_DELAY_SYNC)
    public synchronized ResponseEntity<String> randomDelaySyncApi() throws InterruptedException {
        Thread.sleep(new Faker().number().numberBetween(1, 10) * 100L);
        ApiLogger.loggerGenerator(ApiEndpoints.RANDOM_DELAY_SYNC);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @PostMapping(ApiEndpoints.PRINT_BODY)
    public ResponseEntity<String> printApiBody(@RequestBody String data) {
        ApiLogger.loggerGenerator(ApiEndpoints.PRINT_BODY);
        return ResponseEntity.ok().body(data);
    }
}
