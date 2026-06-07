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
}