package com.aapon.springbootrestapilearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class LoadTestApiController {
    private static long count = 1;

    @GetMapping("/api1")
    public static ResponseEntity<String> api1(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api1 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api2")
    public static ResponseEntity<String> api2(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api2 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api3")
    public static ResponseEntity<String> api3(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api3 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api4")
    public static ResponseEntity<String> api4(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api4 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api5")
    public static ResponseEntity<String> api5(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api5 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/apiWithHeaderData")
    public static synchronized ResponseEntity<String> apiWithHeaderData(@RequestHeader("Scenario-Id") String scenarioId, @RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] apiWithHeaderData ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), scenarioId, userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/apiWithPathData/{scenarioId}")
    public static synchronized ResponseEntity<String> apiWithPathData(@PathVariable String scenarioId, @RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] apiWithPathData ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), scenarioId, userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }
}
