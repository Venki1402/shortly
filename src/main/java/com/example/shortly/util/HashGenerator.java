package com.example.shortly.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for generating short URL codes
 * using SHA-256 hashing and Base62 encoding.
 */
public final class HashGenerator {

    private static final String BASE62_CHARS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int SHORT_CODE_LENGTH = 8;

    private HashGenerator() {
        // prevent instantiation
    }

    /**
     * Generates a URL-safe short code from the input string.
     */
    public static String generateShortCode(String input) {
        byte[] hash = sha256(input);
        return base62Encode(hash).substring(0, SHORT_CODE_LENGTH);
    }

    private static byte[] sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 is guaranteed to exist in the JVM
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }

    private static String base62Encode(byte[] bytes) {
        StringBuilder result = new StringBuilder();

        for (byte b : bytes) {
            int index = (b & 0xFF) % BASE62_CHARS.length();
            result.append(BASE62_CHARS.charAt(index));
        }
        return result.toString();
    }
}
