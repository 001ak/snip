package com.snip.controller;

import com.snip.dto.LinkDetailsDto;
import com.snip.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class responsible for handling analytics-related endpoints.
 */
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    /**
     * Constructs an instance of AnalyticsController with the given AnalyticsService.
     * @param analyticsService the analytics service to use
     */
    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * Retrieves link details, including total clicks, unique clicks, and other relevant metrics.
     * @param linkId the ID of the link to retrieve details for
     * @return a ResponseEntity containing the link details
     */
    @GetMapping("/link/{linkId}")
    public ResponseEntity<LinkDetailsDto> getLinkDetails(@PathVariable Long linkId) {
        LinkDetailsDto linkDetails = analyticsService.getLinkDetails(linkId);
        return new ResponseEntity<>(linkDetails, HttpStatus.OK);
    }

    /**
     * Retrieves a list of link details for all links.
     * @return a ResponseEntity containing the list of link details
     */
    @GetMapping("/links")
    public ResponseEntity<List<LinkDetailsDto>> getAllLinkDetails() {
        List<LinkDetailsDto> linkDetailsList = analyticsService.getAllLinkDetails();
        return new ResponseEntity<>(linkDetailsList, HttpStatus.OK);
    }
}