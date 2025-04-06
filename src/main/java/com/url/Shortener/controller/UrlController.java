package com.url.Shortener.controller;

import com.url.Shortener.Service.UrlService;
import com.url.Shortener.dto.UrlRequest;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {
    @Autowired
    private UrlService urlService;
    @Autowired
    private Bucket bucket;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlRequest urlRequest) {
        return ResponseEntity.ok("http://localhost:8080/api/" + urlService.shortUrl(urlRequest));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){
        if(bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION,urlService.redirect(shortCode))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Rate limit exceeded Try again later.");
        }
    }

    @PostMapping("/shortenB")
    public ResponseEntity<?> shortenURL(@RequestBody UrlRequest urlRequest) {
        if(bucket.tryConsume(1)) {
            return ResponseEntity.ok("http://localhost:8080/api/" + urlService.shortUrl(urlRequest));
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Rate limit exceeded Try again later.");
        }
    }


}
