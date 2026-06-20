package com.snip.util;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Service to track and limit redirect requests per IP address.
 */
@Service
public class RateLimitService {

    private final ConcurrentMap<String, Long> ipRequestTimestamps = new ConcurrentHashMap<>();

    /**
     * Checks if the IP address has exceeded the rate limit.
     * 
     * @param ipAddress the IP address to check
     * @param maxRequests the maximum number of requests allowed per minute
     * @return true if the IP address has exceeded the rate limit, false otherwise
     */
    public boolean isRateLimitExceeded(String ipAddress, int maxRequests) {
        long now = System.currentTimeMillis();
        ipRequestTimestamps.compute(ipAddress, (k, v) -> {
            if (v == null) {
                return now;
            } else if (now - v > TimeUnit.MINUTES.toMillis(1)) {
                return now;
            } else {
                return v;
            }
        });
        long count = ipRequestTimestamps.values().stream()
                .filter(timestamp -> now - timestamp <= TimeUnit.MINUTES.toMillis(1))
                .count();
        return count > maxRequests;
    }

    /**
     * Tracks a redirect request from the given IP address.
     * 
     * @param ipAddress the IP address making the request
     */
    public void trackRequest(String ipAddress) {
        ipRequestTimestamps.put(ipAddress, System.currentTimeMillis());
    }
}