package com.aapon.springbootrestapilearn.controller;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aapon.springbootrestapilearn.CustomApiLogger.loggerGenerator;


@RestController
public class ApplicationController {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @GetMapping("/app_info")
    public ResponseEntity<String> normalApi() {
        loggerGenerator("/app_info");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", appName);
        jsonObject.addProperty("version", appVersion);
        jsonObject.addProperty("port", port);
        jsonObject.addProperty("context_path", contextPath);
        jsonObject.addProperty("base_url", "http://localhost:" + port + contextPath);
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }
}
