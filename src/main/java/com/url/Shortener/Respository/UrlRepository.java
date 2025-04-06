package com.url.Shortener.Respository;

import com.url.Shortener.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<URL, Long> {
    Optional<URL> findByShortCode(String shortCode);
    void deleteAllByExpiresAtBefore(LocalDateTime timestamp);

}
