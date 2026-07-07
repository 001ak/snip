package com.snip.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.commons.validator.routines.UrlValidator;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

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

    /**
     * Checks if a URL is reachable.
     * 
     * @param url the URL to be checked
     * @return true if the URL is reachable, false otherwise
     */
    public boolean isUrlReachable(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator validator = new UrlValidator(schemes);
        return validator.isValid(url);
    }

    /**
     * Checks if a URL is reachable by sending a HEAD request.
     * 
     * @param url the URL to be checked
     * @return true if the URL is reachable, false otherwise
     */
    public boolean isUrlReachableUsingHeadRequest(String url) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });
        try {
            HttpStatus statusCode = restTemplate.headForHeaders(url).getStatusCode();
            return statusCode.is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}