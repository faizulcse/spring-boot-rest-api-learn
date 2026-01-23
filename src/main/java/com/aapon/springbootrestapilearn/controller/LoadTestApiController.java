package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoadTestApiController {
    @GetMapping(ApiEndpoints.WITH_HEADER_DATA)
    public synchronized ResponseEntity<String> apiWithHeaderData(@RequestHeader("scenario_id") String scenario_id) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.WITH_PATH_DATA)
    public synchronized ResponseEntity<String> apiWithPathData(@PathVariable String scenario_id) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.NORMAL)
    public ResponseEntity<String> normalApi() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.NORMAL_SYNC)
    public synchronized ResponseEntity<String> normalSyncApi() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.FIXED_DELAY)
    public ResponseEntity<String> fixedDelayApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.RANDOM_DELAY)
    public ResponseEntity<String> randomDelayApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.FIXED_DELAY_SYNC)
    public synchronized ResponseEntity<String> fixedDelaySyncApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @GetMapping(ApiEndpoints.RANDOM_DELAY_SYNC)
    public synchronized ResponseEntity<String> randomDelaySyncApi(@RequestParam Long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new JsonObject().toString());
    }

    @PostMapping(ApiEndpoints.PRINT_BODY)
    public ResponseEntity<String> printApiBody(@RequestBody String data) {
        final JsonElement json;
        try {
            json = JsonParser.parseString(data);
        } catch (JsonSyntaxException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new JsonObject().toString());
        }

        if (!json.isJsonObject()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new JsonObject().toString());
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(json.getAsJsonObject().toString());
    }
}
