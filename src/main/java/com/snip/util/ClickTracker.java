package com.snip.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ClickTracker is a utility class responsible for tracking and recording clicks.
 */
@Component
public class ClickTracker {

    private AtomicLong clickCount;

    /**
     * Initializes the click count to 0.
     */
    public ClickTracker() {
        this.clickCount = new AtomicLong(0);
    }

    /**
     * Records a click by incrementing the click count.
     */
    public void recordClick() {
        clickCount.incrementAndGet();
    }

    /**
     * Returns the total number of clicks recorded.
     * @return the total number of clicks
     */
    public long getClickCount() {
        return clickCount.get();
    }
}