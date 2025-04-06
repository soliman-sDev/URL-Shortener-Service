package com.url.Shortener.Service;

import com.url.Shortener.Respository.UrlRepository;
import com.url.Shortener.dto.UrlRequest;
import com.url.Shortener.entity.URL;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;


@Service
@Transactional
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public String shortUrl(UrlRequest urlRequest) {
            String shortCode = generateShortCode();
            URL url = new URL();
            url.setShortCode(shortCode);
            url.setLongUrl(urlRequest.getLongUrl());
            url.setExpiresAt(urlRequest.getExpiresAt());
            urlRepository.save(url);
        return shortCode;
    }

    @Deprecated
    private String generateShortCode() {
        return RandomStringUtils.randomAlphabetic(6);
    }


    public String redirect(String shortCode) {
        URL url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "URL NOT FOUND"));
        if (url.getExpiresAt() != null && LocalDateTime.now().isAfter(url.getExpiresAt())) {
            throw new ResponseStatusException(HttpStatus.GONE, "URL Expired");
        }
        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);
        return url.getLongUrl();
    }
}
