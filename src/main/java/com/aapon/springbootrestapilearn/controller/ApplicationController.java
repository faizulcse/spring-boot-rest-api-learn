package com.aapon.springbootrestapilearn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    @Value("${app.name: Employee Tracker}")
    private String appName;

    @Value("${app.version: version1}")
    private String appVersion;

    @GetMapping("/version")
    public String getAppDetails() {
        return appName + " - " + appVersion;
    }
}
