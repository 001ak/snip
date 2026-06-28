package com.snip.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

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

    /**
     * Checks if a given URL is reachable by sending a HEAD request.
     * 
     * @param url the URL to be checked
     * @return true if the URL is reachable, false otherwise
     */
    public boolean isUrlReachable(String url) {
        RestTemplate restTemplate = new RestTemplate();
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

    /**
     * Checks if a given URL is reachable by sending a HEAD request with a timeout.
     * 
     * @param url the URL to be checked
     * @param timeout the timeout in milliseconds
     * @return true if the URL is reachable, false otherwise
     */
    public boolean isUrlReachableWithTimeout(String url, int timeout) {
        RestTemplate restTemplate = new RestTemplate();
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