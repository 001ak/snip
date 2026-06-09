package com.snip.service;

import com.snip.entity.ShortLink;
import com.snip.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service class for managing short links.
 */
@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;

    /**
     * Constructs a new ShortLinkService instance.
     * @param shortLinkRepository the repository for short links
     */
    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    /**
     * Creates a new short link.
     * @param shortLink the short link to create
     * @return the created short link
     */
    public ShortLink createShortLink(ShortLink shortLink) {
        return shortLinkRepository.save(shortLink);
    }

    /**
     * Retrieves a short link by its ID.
     * @param id the ID of the short link to retrieve
     * @return the retrieved short link, or null if not found
     */
    public ShortLink getShortLinkById(Long id) {
        return shortLinkRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing short link.
     * @param shortLink the short link to update
     * @return the updated short link
     */
    public ShortLink updateShortLink(ShortLink shortLink) {
        return shortLinkRepository.save(shortLink);
    }

    /**
     * Deletes a short link by its ID.
     * @param id the ID of the short link to delete
     */
    public void deleteShortLink(Long id) {
        shortLinkRepository.deleteById(id);
    }

    /**
     * Enum for sort fields.
     */
    public enum SortField {
        ID,
        URL,
        CREATED_AT
    }

    /**
     * Sorts short links based on the specified sort field.
     * @param sortField the field to sort by
     * @param ascending whether to sort in ascending order
     * @return the sorted list of short links
     */
    public Iterable<ShortLink> sortShortLinks(SortField sortField, boolean ascending) {
        String sortBy = sortField.name().toLowerCase();
        String direction = ascending ? "asc" : "desc";
        return shortLinkRepository.findAllByOrderBy(sortBy + " " + direction);
    }

    /**
     * Records a click for the given short link ID asynchronously.
     * @param id the ID of the short link
     */
    @Async
    public void recordClickAsync(Long id) {
        ShortLink shortLink = getShortLinkById(id);
        if (shortLink != null) {
            shortLink.setTotalClicks(shortLink.getTotalClicks() + 1);
            updateShortLink(shortLink);
        }
    }
}