package com.aapon.springbootrestapilearn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

;


@RestController
public class MyController {
    private static long count = 1;

    @GetMapping("/info")
    public ResponseEntity<String> getAppInfo() {
        String log = String.format("%s. [%s]=============> %s", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1");
        System.out.println(log);
        return ResponseEntity.ok().body(log);
    }
}
