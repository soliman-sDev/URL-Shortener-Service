package com.url.Shortener.controller;

import com.url.Shortener.Service.UrlService;
import com.url.Shortener.dto.UrlRequest;
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

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlRequest urlRequest) {
        return ResponseEntity.ok("http://localhost:8080/api/" + urlService.shortUrl(urlRequest));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION,urlService.redirect(shortCode))
                .build();
    }
}
