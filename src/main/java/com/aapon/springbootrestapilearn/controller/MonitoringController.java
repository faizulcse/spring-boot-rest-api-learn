package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.filter.RequestFilter;
import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class MonitoringController {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    private final RequestFilter requestFilter;

    public MonitoringController(RequestFilter rateLimitingFilter) {
        this.requestFilter = rateLimitingFilter;
    }

    @GetMapping(ApiEndpoints.APP_INFO)
    public ResponseEntity<String> appInfoApi() {
        JsonObject appInfo = new JsonObject();
        appInfo.addProperty("name", appName);
        appInfo.addProperty("version", appVersion);
        appInfo.addProperty("port", port);
        appInfo.addProperty("context_path", contextPath);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(appInfo.toString());
    }

    @GetMapping(ApiEndpoints.INFLIGHT)
    public ResponseEntity<String> inflight() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("in_flight", requestFilter.getInFlightCount());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toString());
    }

    @GetMapping(ApiEndpoints.CLIENT_INFO)
    public ResponseEntity<String> getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        String userAgent  = request.getHeader("User-Agent");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        JsonObject client = new JsonObject();
        client.addProperty("ip", ipAddress);
        client.addProperty("userAgent", userAgent);

        Matcher m = Pattern.compile("\\(([^)]*)\\)").matcher(userAgent);
        if (m.find()) {
            String agent = m.group(1);
            client.addProperty("platform", agent.split("; ")[0]);
            client.addProperty("os", agent.split("; ")[1]);
        }

        client.addProperty("method", request.getMethod());
        client.addProperty("uri", request.getRequestURI());
        client.addProperty("query", request.getQueryString());
        client.addProperty("scheme", request.getScheme());
        client.addProperty("secure", request.isSecure());
        client.addProperty("acceptLanguage", request.getHeader("Accept-Language"));
        client.addProperty("referer", request.getHeader("Referer"));
        client.addProperty("origin", request.getHeader("Origin"));
        client.addProperty("host", request.getHeader("Host"));
        client.addProperty("xForwardedProto", request.getHeader("X-Forwarded-Proto"));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(client.toString());
    }
}
