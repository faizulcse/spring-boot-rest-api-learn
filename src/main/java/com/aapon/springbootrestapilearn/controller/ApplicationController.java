package com.aapon.springbootrestapilearn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aapon.springbootrestapilearn.CustomApiLogger.loggerGenerator;


@RestController
public class ApplicationController {
    @Value("${app.name: Employee Tracker}")
    private String appName;

    @Value("${app.version: version1}")
    private String appVersion;

    @GetMapping("/version")
    public ResponseEntity<String> normalApi() {
        loggerGenerator("/version");
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }
}
