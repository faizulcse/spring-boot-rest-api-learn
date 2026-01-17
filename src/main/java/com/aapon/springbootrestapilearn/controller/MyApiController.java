package com.aapon.springbootrestapilearn.controller;

import net.datafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aapon.springbootrestapilearn.CustomApiLogger.loggerGenerator;

@RestController
public class MyApiController {
    @GetMapping("/normalApi")
    public ResponseEntity<String> normalApi() {
        loggerGenerator("/normalApi");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/normalSyncApi")
    public synchronized ResponseEntity<String> normalSyncApi() {
        loggerGenerator("/normalSyncApi");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/fixedDelayApi")
    public ResponseEntity<String> fixedDelayApi() throws InterruptedException {
        Thread.sleep(80000);
        loggerGenerator("/fixedDelayApi");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/randomDelayApi")
    public ResponseEntity<String> randomDelayApi() throws InterruptedException {
        Thread.sleep(new Faker().number().numberBetween(1, 10) * 100L);
        loggerGenerator("/randomDelayApi");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/fixedDelaySyncApi")
    public synchronized ResponseEntity<String> fixedDelaySyncApi() throws InterruptedException {
        Thread.sleep(999);
        loggerGenerator("/fixedDelaySyncApi");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping("/randomDelaySyncApi")
    public synchronized ResponseEntity<String> randomDelaySyncApi() throws InterruptedException {
        Thread.sleep(new Faker().number().numberBetween(1, 10) * 100L);
        loggerGenerator("/randomDelaySyncApi");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @PostMapping("/printApiBody")
    public ResponseEntity<String> printApiBody(@RequestBody String data) {
        loggerGenerator("/printApiBody");
        return ResponseEntity.ok().body(data);
    }
}