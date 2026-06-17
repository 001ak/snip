package com.snip.service;

import com.snip.entity.ShortLink;
import com.snip.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.snip.util.AliasGenerator;
import com.snip.util.IpHasher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import com.snip.exception.AliasGenerationFailedException;
import com.snip.exception.AliasAlreadyExistsException;
import com.snip.constants.AppConstants;

/**
 * Service class for managing short links.
 */
@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;
    private final AliasGenerator aliasGenerator;
    private final IpHasher ipHasher;

    /**
     * Constructs a new ShortLinkService instance.
     * @param shortLinkRepository the repository for short links
     * @param aliasGenerator the alias generator
     * @param ipHasher the ip hasher
     */
    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository, AliasGenerator aliasGenerator, IpHasher ipHasher) {
        this.shortLinkRepository = shortLinkRepository;
        this.aliasGenerator = aliasGenerator;
        this.ipHasher = ipHasher;
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

    /**
     * Verifies the password for a short link.
     * @param id the ID of the short link
     * @param password the password to verify
     * @return true if the password is correct, false otherwise
     */
    public boolean verifyPassword(Long id, String password) {
        ShortLink shortLink = getShortLinkById(id);
        if (shortLink != null) {
            return shortLink.getPassword().equals(hashPassword(password));
        }
        return false;
    }

    /**
     * Verifies the password for a short link by alias.
     * @param alias the alias of the short link
     * @param password the password to verify
     * @return true if the password is correct, false otherwise
     */
    public boolean verifyPassword(String alias, String password) {
        ShortLink shortLink = shortLinkRepository.findByAlias(alias);
        if (shortLink != null) {
            return shortLink.getPassword().equals(hashPassword(password));
        }
        return false;
    }

    /**
     * Verifies the password for a short link using the stored password hash and the provided password.
     * @param shortLink the short link to verify
     * @param password the password to verify
     * @return true if the password is correct, false otherwise
     */
    public boolean verifyPassword(ShortLink shortLink, String password) {
        if (shortLink != null && shortLink.getPassword() != null) {
            return shortLink.getPassword().equals(hashPassword(password));
        }
        return false;
    }

    /**
     * Verifies the password for a short link using the IpHasher utility to hash the IP address.
     * @param id the ID of the short link
     * @param password the password to verify
     * @param ipAddress the IP address to hash
     * @return true if the password is correct, false otherwise
     */
    public boolean verifyPasswordWithIpHash(Long id, String password, String ipAddress) {
        ShortLink shortLink = getShortLinkById(id);
        if (shortLink != null) {
            String ipHash = ipHasher.hashIp(ipAddress);
            return shortLink.getPassword().equals(hashPassword(password + ipHash));
        }
        return false;
    }

    /**
     * Updates a short link in the database.
     * @param shortLink the short link to update
     * @return the updated short link
     */
    public ShortLink updateShortLinkDetails(ShortLink shortLink) {
        ShortLink existingShortLink = getShortLinkById(shortLink.getId());
        if (existingShortLink != null) {
            existingShortLink.setUrl(shortLink.getUrl());
            existingShortLink.setAlias(shortLink.getAlias());
            existingShortLink.setPassword(shortLink.getPassword());
            return shortLinkRepository.save(existingShortLink);
        }
        return null;
    }

    /**
     * Generates a custom alias for a short link.
     * @param alias the custom alias to generate
     * @return the generated custom alias
     * @throws AliasGenerationFailedException if the alias length is invalid
     * @throws AliasAlreadyExistsException if the alias already exists
     */
    public String generateCustomAlias(String alias) {
        if (alias.length() < AppConstants.MIN_ALIAS_LENGTH || alias.length() > AppConstants.MAX_ALIAS_LENGTH) {
            throw new AliasGenerationFailedException("Alias length must be between " + AppConstants.MIN_ALIAS_LENGTH + " and " + AppConstants.MAX_ALIAS_LENGTH + ".");
        }
        // Check if alias already exists
        if (shortLinkRepository.findByAlias(alias) != null) {
            throw new AliasAlreadyExistsException("Alias already exists.");
        }
        return alias;
    }
}