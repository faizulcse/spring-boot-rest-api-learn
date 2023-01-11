package com.aapon.springbootrestapilearn.controller;

import com.github.javafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class MyController {
    private static long count = 1;

    @GetMapping("/info")
    public ResponseEntity<String> getAppInfo(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.ok().body(log);
    }

    @GetMapping("/load_test")
    public ResponseEntity<String> loadTest(@RequestHeader(value = "User-Agent") String userAgent) throws InterruptedException {
        long wait = new Faker().random().nextInt(1, 10) * 100;
        Thread.sleep(wait);
        String log = String.format("%s. [%s] ====(%s)====> [%s] ===> %s", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent, wait);
        System.out.println(log);
        return ResponseEntity.ok().body(log);
    }
}
