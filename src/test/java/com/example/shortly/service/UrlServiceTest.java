package com.example.shortly.service;

import com.example.shortly.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlServiceTest {

    private UrlService urlService;
    private UrlRepository repository;

    @BeforeEach
    void setup() {
        urlService = new UrlService();
        repository = UrlRepository.getInstance();
        repository.clear();
    }

    @Test
    void validUrl_shouldReturnShortCode() {
        String code = urlService.shortenUrl("https://example.com");

        assertNotNull(code);
        assertEquals(8, code.length());
    }

    @Test
    void invalidUrl_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> urlService.shortenUrl("ftp://example.com"));
    }

    @Test
    void shortenedUrl_shouldBeResolvable() {
        String code = urlService.shortenUrl("https://example.com");

        assertTrue(urlService.resolveUrl(code).isPresent());
        assertEquals(
                "https://example.com",
                urlService.resolveUrl(code).get());
    }

    @Test
    void unknownShortCode_shouldReturnEmpty() {
        assertTrue(urlService.resolveUrl("unknown").isEmpty());
    }
}
