package com.snip.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class is responsible for validating links.
 */
@Component
public class LinkValidator {

    /**
     * Checks if a given link is valid.
     * 
     * @param link the link to be validated
     * @return true if the link is valid, false otherwise
     */
    public boolean isValidLink(String link) {
        // Define a regular expression pattern for a valid link
        String linkPattern = "^https?://[^\\s/$.?#].[^\\s]*$";
        
        // Compile the pattern
        Pattern pattern = Pattern.compile(linkPattern);
        
        // Create a matcher for the link
        Matcher matcher = pattern.matcher(link);
        
        // Check if the link matches the pattern
        return matcher.find();
    }
}