package com.aapon.springbootrestapilearn;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class CustomApiLogger {
    private static long count = 1;
    private static final String port = "9090";
    private static final String contextPath = "/api/v1";
    private static final String baseUrl = "http://localhost:" + port + contextPath;
    private static final boolean LOGGER_ENABLED = System.getenv("LOGGER_ENABLED") != null && System.getenv("LOGGER_ENABLED").equals("true");

    public static void loggerGenerator(String endpoint) {
        if (LOGGER_ENABLED) {
            System.out.printf("%-10s [%s] %s%n", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), baseUrl + endpoint);
        }
    }
}
