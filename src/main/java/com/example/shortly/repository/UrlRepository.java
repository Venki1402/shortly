package com.example.shortly.repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Thread-safe in-memory repository for URL mappings.
 * Acts as a persistence abstraction.
 */
public class UrlRepository {

    private static final UrlRepository INSTANCE = new UrlRepository();

    private final ConcurrentMap<String, String> urlStore = new ConcurrentHashMap<>();

    private UrlRepository() {
        // private constructor to enforce singleton
    }

    public static UrlRepository getInstance() {
        return INSTANCE;
    }

    /**
     * Stores a shortCode -> longUrl mapping.
     */
    public void save(String shortCode, String longUrl) {
        urlStore.put(shortCode, longUrl);
    }

    /**
     * Retrieves the original URL for a given short code.
     */
    public Optional<String> findByCode(String shortCode) {
        return Optional.ofNullable(urlStore.get(shortCode));
    }

    /**
     * Checks whether a short code already exists.
     */
    public boolean exists(String shortCode) {
        return urlStore.containsKey(shortCode);
    }

    /**
     * Clears all mappings (used for testing).
     */
    public void clear() {
        urlStore.clear();
    }
}
