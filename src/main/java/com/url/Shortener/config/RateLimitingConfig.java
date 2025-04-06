package com.url.Shortener.config;


import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitingConfig {
    @Bean
    public Bucket bucket() {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(1).refillGreedy(1, Duration.ofMinutes(1)))
                .build();
    }
}
