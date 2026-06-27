package com.snip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snip.service.AnalyticsService;

/**
 * This class is responsible for handling analytics related requests.
 * 
 * @author [Your Name]
 */
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    /**
     * Constructor to initialize the AnalyticsService.
     * 
     * @param analyticsService the analytics service to be used
     */
    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * This method is used to get the analytics data.
     * 
     * @return the analytics data
     */
    @GetMapping
    public ResponseEntity<String> getAnalytics() {
        try {
            String analyticsData = analyticsService.getAnalyticsData();
            return new ResponseEntity<>(analyticsData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}