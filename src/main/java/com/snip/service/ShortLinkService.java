package com.snip.service;

import com.snip.entity.ShortLink;
import com.snip.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.snip.util.AliasGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * Service class for managing short links.
 */
@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;
    private final AliasGenerator aliasGenerator;

    /**
     * Constructs a new ShortLinkService instance.
     * @param shortLinkRepository the repository for short links
     * @param aliasGenerator the alias generator
     */
    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository, AliasGenerator aliasGenerator) {
        this.shortLinkRepository = shortLinkRepository;
        this.aliasGenerator = aliasGenerator;
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

    /**
     * Updates a short link with the given ID.
     * @param id the ID of the short link to update
     * @param newUrl the new URL for the short link
     * @return the updated short link, or null if not found
     */
    public ShortLink updateShortLink(Long id, String newUrl) {
        ShortLink shortLink = getShortLinkById(id);
        if (shortLink != null) {
            shortLink.setUrl(newUrl);
            return updateShortLink(shortLink);
        }
        return null;
    }

    /**
     * Deletes a short link by its alias.
     * @param alias the alias of the short link to delete
     */
    public void deleteShortLink(String alias) {
        shortLinkRepository.deleteById(alias);
    }

    /**
     * Generates a unique alias for a short link.
     * @return the generated alias
     */
    public String generateAlias() {
        String alias;
        do {
            alias = aliasGenerator.generateAlias();
        } while (shortLinkRepository.existsByAlias(alias));
        return alias;
    }

    /**
     * Creates a new short link with a password.
     * @param shortLink the short link to create
     * @param password the password for the short link
     * @return the created short link
     */
    public ShortLink createShortLink(ShortLink shortLink, String password) {
        shortLink.setPassword(hashPassword(password));
        return createShortLink(shortLink);
    }

    /**
     * Hashes a password using SHA-256.
     * @param password the password to hash
     * @return the hashed password
     */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}