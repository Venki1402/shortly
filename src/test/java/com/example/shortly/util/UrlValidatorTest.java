package com.example.shortly.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlValidatorTest {

    @Test
    void validHttpUrl_shouldPass() {
        assertTrue(UrlValidator.isValid("http://example.com"));
    }

    @Test
    void validHttpsUrl_shouldPass() {
        assertTrue(UrlValidator.isValid("https://example.com/path"));
    }

    @Test
    void invalidScheme_shouldFail() {
        assertFalse(UrlValidator.isValid("ftp://example.com"));
    }

    @Test
    void javascriptScheme_shouldFail() {
        assertFalse(UrlValidator.isValid("javascript:alert(1)"));
    }

    @Test
    void emptyUrl_shouldFail() {
        assertFalse(UrlValidator.isValid(""));
    }

    @Test
    void nullUrl_shouldFail() {
        assertFalse(UrlValidator.isValid(null));
    }

    @Test
    void tooLongUrl_shouldFail() {
        String longUrl = "https://example.com/" + "a".repeat(3000);
        assertFalse(UrlValidator.isValid(longUrl));
    }
}
