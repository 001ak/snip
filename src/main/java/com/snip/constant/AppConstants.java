package com.snip.constant;

import org.springframework.stereotype.Component;

/**
 * This class contains project-wide constants.
 * 
 * @author [Your Name]
 */
@Component
public class AppConstants {

    /**
     * Maximum allowed length for alias.
     */
    public static final int MAX_ALIAS_LENGTH = 50;

    /**
     * Minimum allowed length for alias.
     */
    public static final int MIN_ALIAS_LENGTH = 3;

    /**
     * Rate limiting: maximum requests per minute.
     */
    public static final int MAX_REQUESTS_PER_MINUTE = 100;

    /**
     * Rate limiting: maximum requests per hour.
     */
    public static final int MAX_REQUESTS_PER_HOUR = 5000;

    /**
     * Default page size for pagination.
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * Maximum page size for pagination.
     */
    public static final int MAX_PAGE_SIZE = 100;

    /**
     * Gets the maximum allowed length for alias.
     * 
     * @return the maximum allowed length for alias
     */
    public int getMaxAliasLength() {
        return MAX_ALIAS_LENGTH;
    }

    /**
     * Gets the minimum allowed length for alias.
     * 
     * @return the minimum allowed length for alias
     */
    public int getMinAliasLength() {
        return MIN_ALIAS_LENGTH;
    }

    /**
     * Gets the rate limiting: maximum requests per minute.
     * 
     * @return the rate limiting: maximum requests per minute
     */
    public int getMaxRequestsPerMinute() {
        return MAX_REQUESTS_PER_MINUTE;
    }

    /**
     * Gets the rate limiting: maximum requests per hour.
     * 
     * @return the rate limiting: maximum requests per hour
     */
    public int getMaxRequestsPerHour() {
        return MAX_REQUESTS_PER_HOUR;
    }

    /**
     * Gets the default page size for pagination.
     * 
     * @return the default page size for pagination
     */
    public int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    /**
     * Gets the maximum page size for pagination.
     * 
     * @return the maximum page size for pagination
     */
    public int getMaxPageSize() {
        return MAX_PAGE_SIZE;
    }
}