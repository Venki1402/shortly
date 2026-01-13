package com.example.shortly.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashGeneratorTest {

    @Test
    void sameInput_shouldGenerateSameCode() {
        String url = "https://example.com";
        String code1 = HashGenerator.generateShortCode(url);
        String code2 = HashGenerator.generateShortCode(url);

        assertEquals(code1, code2);
    }

    @Test
    void generatedCode_shouldHaveExpectedLength() {
        String code = HashGenerator.generateShortCode("https://example.com");
        assertEquals(8, code.length());
    }

    @Test
    void generatedCode_shouldBeAlphaNumeric() {
        String code = HashGenerator.generateShortCode("https://example.com");
        assertTrue(code.matches("[A-Za-z0-9]+"));
    }
}
