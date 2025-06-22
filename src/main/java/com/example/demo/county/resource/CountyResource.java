package com.example.demo.county.resource;

import com.example.demo.county.service.CountyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountyResource {

    public static final String TEXT_PLAIN_UTF8 = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8";
    private final CountyService countyService;

    public CountyResource(CountyService countyService) {
        this.countyService = countyService;
    }

    @GetMapping("/county/{countyNumber}")
    public ResponseEntity<String> getCountyName(@PathVariable String countyNumber) {
        String countyName = countyService.fetchCountyName(countyNumber);
        if (countyName == null) {
            return createResponseEntityNotFound(countyNumber);
        }
        return createResponseEntityOk(countyName);
    }

    private static ResponseEntity<String> createResponseEntityNotFound(String countyNumber) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN_UTF8)
                .body("County not found for countyNumber=" + countyNumber);
    }

    private static ResponseEntity<String> createResponseEntityOk(String countyName) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN_UTF8)
                .body(countyName);
    }
}
