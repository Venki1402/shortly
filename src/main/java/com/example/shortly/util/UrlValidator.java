package com.example.shortly.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Validates user-provided URLs to prevent security issues
 * such as open redirects and malformed input.
 */
public final class UrlValidator {

    private static final Set<String> ALLOWED_SCHEMES = Set.of("http", "https");
    private static final int MAX_URL_LENGTH = 2048;

    private UrlValidator() {
        // utility class
    }

    /**
     * Validates whether the given URL is safe and acceptable.
     */
    public static boolean isValid(String inputUrl) {
        if (inputUrl == null || inputUrl.isBlank()) {
            return false;
        }

        if (inputUrl.length() > MAX_URL_LENGTH) {
            return false;
        }

        try {
            URL url = new URL(inputUrl);
            return ALLOWED_SCHEMES.contains(url.getProtocol());
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
