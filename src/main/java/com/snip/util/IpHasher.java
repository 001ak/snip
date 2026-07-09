package com.snip.util;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Utility class for hashing IP addresses using SHA-256.
 */
@Component
public class IpHasher {

    /**
     * Hashes an IP address using SHA-256.
     * 
     * @param ipAddress the IP address to be hashed
     * @return the hashed IP address
     */
    public String hashIp(String ipAddress) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(ipAddress.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * Generates a random alphanumeric string of a specified length.
     * 
     * @param length the length of the salt string
     * @return a random alphanumeric string
     */
    public String generateSalt(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder salt = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            salt.append(characters.charAt(random.nextInt(characters.length())));
        }
        return salt.toString();
    }
}