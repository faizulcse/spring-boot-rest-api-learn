package com.aapon.springbootrestapilearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.aapon.springbootrestapilearn.CustomApiLogger.loggerGenerator;

@RestController
public class LoadTestApiController {
    @GetMapping("/api1")
    public static ResponseEntity<String> api1() {
        loggerGenerator("/api1");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/api2")
    public static ResponseEntity<String> api2() {
        loggerGenerator("/api2");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/api3")
    public static ResponseEntity<String> api3() {
        loggerGenerator("/api3");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/api4")
    public static ResponseEntity<String> api4() {
        loggerGenerator("/api4");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/api5")
    public static ResponseEntity<String> api5() {
        loggerGenerator("/api5");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/apiWithHeaderData")
    public static synchronized ResponseEntity<String> apiWithHeaderData(@RequestHeader("scenarioId") String scenarioId) {
        loggerGenerator("/apiWithHeaderData?scenarioId=" + scenarioId);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/apiWithPathData/{scenarioId}")
    public static synchronized ResponseEntity<String> apiWithPathData(@PathVariable String scenarioId) {
        loggerGenerator("/apiWithPathData/" + scenarioId);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }
}
