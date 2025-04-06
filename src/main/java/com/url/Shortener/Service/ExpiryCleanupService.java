package com.url.Shortener.Service;

import com.url.Shortener.Respository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExpiryCleanupService {
    @Autowired
    private UrlRepository urlRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void cleanupExpiredUrls() {
        urlRepository.deleteAllByExpiresAtBefore(LocalDateTime.now());
    }
}
