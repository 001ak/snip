package com.snip.controller;

import com.snip.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for handling analytics-related requests.
 */
@RestController
@RequestMapping("/api/v1")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    /**
     * Constructs an instance of AnalyticsController.
     * 
     * @param analyticsService the service for analytics operations
     */
    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * Handles GET requests to /links/{alias}/analytics and returns the analytics data for the given link alias.
     * 
     * @param alias the alias of the link
     * @return the analytics data for the given link alias
     */
    @GetMapping("/links/{alias}/analytics")
    public ResponseEntity<Map<String, Object>> getLinkAnalytics(@PathVariable String alias) {
        Map<String, Object> analyticsData = analyticsService.getAnalyticsData(alias);
        return ResponseEntity.ok(analyticsData);
    }
}