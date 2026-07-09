package com.snip.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.snip.repository.ClickRepository;
import com.snip.model.Click;

/**
 * Service class for handling analytics queries.
 */
@Service
public class AnalyticsService {

    private final ClickRepository clickRepository;

    public AnalyticsService(ClickRepository clickRepository) {
        this.clickRepository = clickRepository;
    }

    /**
     * Retrieves a list of all analytics data.
     * 
     * @return a list of analytics data
     */
    public List<?> getAllAnalytics() {
        // TO DO: implement method
        return null;
    }

    /**
     * Retrieves analytics data by a specific identifier.
     * 
     * @param id the identifier
     * @return the analytics data
     */
    public Object getAnalyticsById(String id) {
        // TO DO: implement method
        return null;
    }

    /**
     * Creates new analytics data.
     * 
     * @param data the analytics data to create
     * @return the created analytics data
     */
    public Object createAnalytics(Object data) {
        // TO DO: implement method
        return null;
    }

    /**
     * Updates existing analytics data.
     * 
     * @param id   the identifier
     * @param data the updated analytics data
     * @return the updated analytics data
     */
    public Object updateAnalytics(String id, Object data) {
        // TO DO: implement method
        return null;
    }

    /**
     * Deletes analytics data by a specific identifier.
     * 
     * @param id the identifier
     */
    public void deleteAnalytics(String id) {
        // TO DO: implement method
    }

    /**
     * Retrieves daily click statistics for a given link alias.
     * 
     * @param linkAlias the link alias
     * @return the daily click statistics
     */
    public Object getDailyClickStatistics(String linkAlias) {
        // TO DO: implement method
        return null;
    }

    /**
     * Retrieves daily click statistics for a given link alias.
     * 
     * @param alias the link alias
     * @return the daily click statistics
     */
    public Map<String, Object> getDailyClickStatistics(String alias) {
        // TO DO: implement method
        return null;
    }

    /**
     * Retrieves daily click statistics for a given link id.
     * 
     * @param linkId the link id
     * @return the daily click statistics
     */
    public Map<String, Long> getDailyClickStatistics(String linkId) {
        // Retrieve click data from the database
        List<Click> clicks = clickRepository.findByLinkId(linkId);
        
        // Group clicks by date and count
        Map<String, Long> dailyClicks = clicks.stream()
            .collect(Collectors.groupingBy(click -> click.getClickedAt().toLocalDate().toString(), Collectors.counting()));
        
        return dailyClicks;
    }

    /**
     * Retrieves daily click statistics for a given link alias.
     * 
     * @param alias the link alias
     * @return the daily click statistics
     */
    public List<DailyStatsResponse> getDailyClickStats(String alias) {
        List<Click> clicks = clickRepository.findByAlias(alias);
        return clicks.stream()
            .collect(Collectors.groupingBy(click -> click.getClickedAt().toLocalDate().toString()))
            .entrySet().stream()
            .map(entry -> new DailyStatsResponse(entry.getKey(), entry.getValue().size()))
            .collect(Collectors.toList());
    }
}

class DailyStatsResponse {
    private String date;
    private int count;

    public DailyStatsResponse(String date, int count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }
}