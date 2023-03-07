package com.aapon.springbootrestapilearn.controller;

import com.github.javafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class MyApiController {
    private static long count = 1;

    @GetMapping("/normalApi")
    public ResponseEntity<String> normalApi(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/normalSyncApi")
    public synchronized ResponseEntity<String> normalSyncApi(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/fixedDelayApi")
    public ResponseEntity<String> fixedDelayApi(@RequestHeader(value = "User-Agent") String userAgent) throws InterruptedException {
        int wait = 80000;
        Thread.sleep(wait);
        String log = String.format("%s. [%s] ====(%s)====> [%s] ===> %s", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent, wait);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/randomDelayApi")
    public ResponseEntity<String> randomDelayApi(@RequestHeader(value = "User-Agent") String userAgent) throws InterruptedException {
        long wait = new Faker().random().nextInt(1, 10) * 100;
        Thread.sleep(wait);
        String log = String.format("%s. [%s] ====(%s)====> [%s] ===> %s", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent, wait);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/fixedDelaySyncApi")
    public synchronized ResponseEntity<String> fixedDelaySyncApi(@RequestHeader(value = "User-Agent") String userAgent) throws InterruptedException {
        int wait = 999;
        Thread.sleep(wait);
        String log = String.format("%s. [%s] ====(%s)====> [%s] ===> %s", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent, wait);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/randomDelaySyncApi")
    public synchronized ResponseEntity<String> randomDelaySyncApi(@RequestHeader(value = "User-Agent") String userAgent) throws InterruptedException {
        long wait = new Faker().random().nextInt(1, 10) * 100;
        Thread.sleep(wait);
        String log = String.format("%s. [%s] ====(%s)====> [%s] ===> %s", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent, wait);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @PostMapping("/printApiBody")
    public ResponseEntity<String> printApiBody(@RequestBody String data) {
        System.out.println(data);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/api1")
    public static synchronized ResponseEntity<String> api1(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api1 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api2")
    public static synchronized ResponseEntity<String> api2(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api2 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api3")
    public static synchronized ResponseEntity<String> api3(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api3 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api4")
    public static synchronized ResponseEntity<String> api4(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api4 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }

    @GetMapping("/api5")
    public static synchronized ResponseEntity<String> api5(@RequestHeader(value = "User-Agent") String userAgent) {
        String log = String.format("%s. [%s] api5 ====(%s)====> [%s]", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "version-v1", userAgent);
        System.out.println(log);
        return ResponseEntity.status(HttpStatus.OK).body(log);
    }
}