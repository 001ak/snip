package com.snip.util;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UserAgentParser is a utility class to parse user agent strings and extract device, browser, and OS information.
 */
@Component
public class UserAgentParser {

    private static final String DEVICE_PATTERN = "(Mobile|Tablet|Desktop)";
    private static final String BROWSER_PATTERN = "(Chrome|Firefox|Safari|Edge|IE)";
    private static final String OS_PATTERN = "(Windows|Mac OS X|Linux|Android|iOS)";
    private static final String COMPREHENSIVE_DEVICE_PATTERN = "(iPhone|iPad|Android|Windows Phone|BlackBerry|PlayStation|Xbox|Nintendo)";

    /**
     * Parses a user agent string and returns a map of device, browser, and OS information.
     * 
     * @param userAgent the user agent string to parse
     * @return a map containing device, browser, and OS information
     */
    public Map<String, String> parseUserAgent(String userAgent) {
        Map<String, String> info = new HashMap<>();
        info.put("device", parseDeviceType(userAgent));
        Pattern browserPattern = Pattern.compile(BROWSER_PATTERN);
        Pattern osPattern = Pattern.compile(OS_PATTERN);

        Matcher browserMatcher = browserPattern.matcher(userAgent);
        Matcher osMatcher = osPattern.matcher(userAgent);

        if (browserMatcher.find()) {
            info.put("browser", browserMatcher.group());
        }
        if (osMatcher.find()) {
            info.put("os", osMatcher.group());
        }
        return info;
    }

    /**
     * Parses a user agent string and returns the device type.
     * 
     * @param userAgent the user agent string to parse
     * @return the device type
     */
    public String parseDeviceType(String userAgent) {
        Pattern devicePattern = Pattern.compile(DEVICE_PATTERN);
        Matcher deviceMatcher = devicePattern.matcher(userAgent);
        if (deviceMatcher.find()) {
            return deviceMatcher.group();
        }
        return "Unknown";
    }

    /**
     * Parses a user agent string and returns the device type using a comprehensive list of known devices.
     * 
     * @param userAgent the user agent string to parse
     * @return the device type
     */
    public String parseComprehensiveDeviceType(String userAgent) {
        Pattern comprehensiveDevicePattern = Pattern.compile(COMPREHENSIVE_DEVICE_PATTERN);
        Matcher comprehensiveDeviceMatcher = comprehensiveDevicePattern.matcher(userAgent);
        if (comprehensiveDeviceMatcher.find()) {
            return comprehensiveDeviceMatcher.group();
        }
        return parseDeviceType(userAgent);
    }
}