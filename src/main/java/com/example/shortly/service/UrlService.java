package com.example.shortly.service;

import com.example.shortly.repository.UrlRepository;
import com.example.shortly.util.HashGenerator;
import com.example.shortly.util.UrlValidator;

import java.util.Optional;

/**
 * Core business logic for URL shortening.
 */
public class UrlService {

    private final UrlRepository repository;

    public UrlService() {
        this.repository = UrlRepository.getInstance();
    }

    /**
     * Creates a short URL code for a valid long URL.
     *
     * @throws IllegalArgumentException if URL is invalid
     */
    public String shortenUrl(String longUrl) {
        if (!UrlValidator.isValid(longUrl)) {
            throw new IllegalArgumentException("Invalid URL provided");
        }

        String shortCode = HashGenerator.generateShortCode(longUrl);

        // Handle rare collisions deterministically
        int attempt = 1;
        while (repository.exists(shortCode)) {
            shortCode = HashGenerator.generateShortCode(longUrl + attempt);
            attempt++;
        }

        repository.save(shortCode, longUrl);
        return shortCode;
    }

    /**
     * Resolves a short code to the original URL.
     */
    public Optional<String> resolveUrl(String shortCode) {
        return repository.findByCode(shortCode);
    }
}
