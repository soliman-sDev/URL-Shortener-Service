package com.url.Shortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.url.Shortener.entity.URL;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlRequest {
    private Long id;
    private String longUrl;
    private int clickCount;
    private String shortCode;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private String Message;
    private int statusCode;
    private String error;
    private URL url;

}
