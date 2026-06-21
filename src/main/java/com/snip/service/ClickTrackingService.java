package com.snip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.snip.repository.ClickTrackingRepository;

/**
 * Service class responsible for tracking clicks.
 * 
 * @author [Your Name]
 */
@Service
public class ClickTrackingService {

    private final ClickTrackingRepository clickTrackingRepository;

    /**
     * Constructor to inject ClickTrackingRepository.
     * 
     * @param clickTrackingRepository the repository to use
     */
    @Autowired
    public ClickTrackingService(ClickTrackingRepository clickTrackingRepository) {
        this.clickTrackingRepository = clickTrackingRepository;
    }

    /**
     * Records a click asynchronously.
     * 
     * @param clickId the ID of the click to record
     */
    @Async
    public void recordClickAsync(String clickId) {
        // Record click logic here
        clickTrackingRepository.saveClick(clickId);
    }
}