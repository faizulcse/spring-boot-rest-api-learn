package com.aapon.springbootrestapilearn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomApiLogger {
    private static long count = 1;

    public static void loggerGenerator(String endpoint) {
        System.out.printf("%-10s [%s] %s%n", count++, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "http://localhost:9090/api/v1" + endpoint);
    }
}
