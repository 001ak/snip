package com.snip.util;

import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Utility class for generating random base62 aliases.
 */
@Component
public class AliasGenerator {

    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int DEFAULT_ALIAS_LENGTH = 6;
    private static final int ALIAS_MIN_CUSTOM_LENGTH = 3;
    private static final int ALIAS_MAX_CUSTOM_LENGTH = 20;
    private static final Random random = new Random();

    /**
     * Generates a random base62 alias of the specified length.
     * 
     * @param length the length of the alias to generate
     * @return a random base62 alias
     */
    public String generateAlias(int length) {
        return generateAlias(length, new HashSet<>());
    }

    /**
     * Generates a random base62 alias of the specified length, checking for collisions.
     * 
     * @param length the length of the alias to generate
     * @param existingAliases a set of existing aliases to check for collisions
     * @return a random base62 alias
     */
    public String generateAlias(int length, Set<String> existingAliases) {
        if (length < 1) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        String alias;
        do {
            alias = generateRandomString(length);
        } while (existingAliases.contains(alias));
        return alias;
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(BASE62_CHARS.charAt(random.nextInt(BASE62_CHARS.length())));
        }
        return sb.toString();
    }

    /**
     * Generates a random base62 alias of the default length.
     * 
     * @return a random base62 alias
     */
    public String generateDefaultAlias() {
        return generateAlias(DEFAULT_ALIAS_LENGTH);
    }

    /**
     * Generates a custom base62 alias, checking if it's valid and available.
     * 
     * @param customAlias the custom alias to generate
     * @param existingAliases a set of existing aliases to check for collisions
     * @return the custom alias if it's valid and available
     */
    public String generateCustomAlias(String customAlias, Set<String> existingAliases) {
        if (customAlias == null || customAlias.isEmpty()) {
            throw new IllegalArgumentException("Custom alias cannot be null or empty");
        }
        for (char c : customAlias.toCharArray()) {
            if (BASE62_CHARS.indexOf(c) == -1) {
                throw new IllegalArgumentException("Custom alias contains invalid characters");
            }
        }
        if (existingAliases.contains(customAlias)) {
            throw new IllegalArgumentException("Custom alias is already in use");
        }
        return customAlias;
    }

    /**
     * Generates a custom base62 alias, checking if it's valid and available.
     * 
     * @param customAlias the custom alias to generate
     * @return the custom alias if it's valid and available
     */
    public String generateCustomAlias(String customAlias) {
        return generateCustomAlias(customAlias, new HashSet<>());
    }

    /**
     * Generates a custom base62 alias, checking if it's valid.
     * 
     * @param customAlias the custom alias to generate
     * @return the custom alias if it's valid
     */
    public String generateCustomAliasValidation(String customAlias) {
        if (customAlias == null || customAlias.isEmpty()) {
            throw new IllegalArgumentException("Custom alias cannot be empty");
        }
        if (customAlias.length() < ALIAS_MIN_CUSTOM_LENGTH || customAlias.length() > ALIAS_MAX_CUSTOM_LENGTH) {
            throw new IllegalArgumentException("Custom alias must be between " + ALIAS_MIN_CUSTOM_LENGTH + " and " + ALIAS_MAX_CUSTOM_LENGTH + " characters long");
        }
        // Check if the custom alias contains only alphanumeric characters and hyphens
        if (!customAlias.matches("^[a-zA-Z0-9-]+$")) {
            throw new IllegalArgumentException("Custom alias can only contain alphanumeric characters and hyphens");
        }
        return customAlias;
    }

    /**
     * Generates a custom base62 alias, checking if it's valid.
     * 
     * @param customAlias the custom alias to generate
     * @return the custom alias if it's valid
     */
    public String generateCustomAlias(String customAlias) {
        if (customAlias.length() < ALIAS_MIN_CUSTOM_LENGTH || customAlias.length() > ALIAS_MAX_CUSTOM_LENGTH) {
            throw new IllegalArgumentException("Custom alias must be between " + ALIAS_MIN_CUSTOM_LENGTH + " and " + ALIAS_MAX_CUSTOM_LENGTH + " characters");
        }
        for (char c : customAlias.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '-') {
                throw new IllegalArgumentException("Custom alias can only contain letters, digits, and hyphens");
            }
        }
        return customAlias;
    }

    /**
     * Checks if a custom alias is valid.
     * 
     * @param alias the alias to check
     * @return true if the alias is valid, false otherwise
     */
    public boolean isValidCustomAlias(String alias) {
        return alias.length() >= ALIAS_MIN_CUSTOM_LENGTH && alias.length() <= ALIAS_MAX_CUSTOM_LENGTH && alias.matches("^[a-zA-Z0-9-]+$");
    }
}