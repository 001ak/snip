package com.snip.util;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Utility class for validating URLs.
 * 
 * @author [Your Name]
 */
@Component
public class UrlValidator {

    /**
     * Checks if a given URL is valid.
     * 
     * @param url the URL to be validated
     * @return true if the URL is valid, false otherwise
     */
    public boolean isValidUrl(String url) {
        try {
            URI uri = new URI(url);
            return uri.getScheme() != null && uri.getHost() != null;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}