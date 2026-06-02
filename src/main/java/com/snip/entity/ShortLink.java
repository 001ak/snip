package com.snip.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a short link entity.
 */
@Entity
@Table(name = "short_links")
public class ShortLink {

    /**
     * Unique identifier for the short link.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The alias for the short link.
     */
    @Column(unique = true)
    private String alias;

    /**
     * The original URL.
     */
    private String originalUrl;

    /**
     * The title of the short link.
     */
    private String title;

    /**
     * The description of the short link.
     */
    private String description;

    /**
     * Whether the alias is custom.
     */
    private Boolean isCustomAlias;

    /**
     * Whether the short link is active.
     */
    private Boolean isActive;

    /**
     * Whether the short link is password protected.
     */
    private Boolean isPasswordProtected;

    /**
     * The password hash for the short link.
     */
    private String passwordHash;

    /**
     * The expiration date for the short link.
     */
    private LocalDateTime expiresAt;

    /**
     * The maximum number of clicks allowed.
     */
    private Integer maxClicks;

    /**
     * The total number of clicks.
     */
    private Integer totalClicks;

    /**
     * The number of unique clicks.
     */
    private Integer uniqueClicks;

    /**
     * The creation date for the short link.
     */
    private LocalDateTime createdAt;

    /**
     * The last update date for the short link.
     */
    private LocalDateTime updatedAt;

    /**
     * Gets the id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the alias.
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the original URL.
     * @return the original URL
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * Sets the original URL.
     * @param originalUrl the original URL to set
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    /**
     * Gets the title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets whether the alias is custom.
     * @return whether the alias is custom
     */
    public Boolean getCustomAlias() {
        return isCustomAlias;
    }

    /**
     * Sets whether the alias is custom.
     * @param customAlias whether the alias is custom
     */
    public void setCustomAlias(Boolean customAlias) {
        isCustomAlias = customAlias;
    }

    /**
     * Gets whether the short link is active.
     * @return whether the short link is active
     */
    public Boolean getActive() {
        return isActive;
    }

    /**
     * Sets whether the short link is active.
     * @param active whether the short link is active
     */
    public void setActive(Boolean active) {
        isActive = active;
    }

    /**
     * Gets whether the short link is password protected.
     * @return whether the short link is password protected
     */
    public Boolean getPasswordProtected() {
        return isPasswordProtected;
    }

    /**
     * Sets whether the short link is password protected.
     * @param passwordProtected whether the short link is password protected
     */
    public void setPasswordProtected(Boolean passwordProtected) {
        isPasswordProtected = passwordProtected;
    }

    /**
     * Gets the password hash.
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the password hash.
     * @param passwordHash the password hash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets the expiration date.
     * @return the expiration date
     */
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the expiration date.
     * @param expiresAt the expiration date to set
     */
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Gets the maximum number of clicks.
     * @return the maximum number of clicks
     */
    public Integer getMaxClicks() {
        return maxClicks;
    }

    /**
     * Sets the maximum number of clicks.
     * @param maxClicks the maximum number of clicks to set
     */
    public void setMaxClicks(Integer maxClicks) {
        this.maxClicks = maxClicks;
    }

    /**
     * Gets the total number of clicks.
     * @return the total number of clicks
     */
    public Integer getTotalClicks() {
        return totalClicks;
    }

    /**
     * Sets the total number of clicks.
     * @param totalClicks the total number of clicks to set
     */
    public void setTotalClicks(Integer totalClicks) {
        this.totalClicks = totalClicks;
    }

    /**
     * Gets the number of unique clicks.
     * @return the number of unique clicks
     */
    public Integer getUniqueClicks() {
        return uniqueClicks;
    }

    /**
     * Sets the number of unique clicks.
     * @param uniqueClicks the number of unique clicks to set
     */
    public void setUniqueClicks(Integer uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }

    /**
     * Gets the creation date.
     * @return the creation date
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation date.
     * @param createdAt the creation date to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update date.
     * @return the last update date
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update date.
     * @param updatedAt the last update date to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortLink shortLink = (ShortLink) o;
        return Objects.equals(id, shortLink.id) && Objects.equals(alias, shortLink.alias) && Objects.equals(originalUrl, shortLink.originalUrl) && Objects.equals(title, shortLink.title) && Objects.equals(description, shortLink.description) && Objects.equals(isCustomAlias, shortLink.isCustomAlias) && Objects.equals(isActive, shortLink.isActive) && Objects.equals(isPasswordProtected, shortLink.isPasswordProtected) && Objects.equals(passwordHash, shortLink.passwordHash) && Objects.equals(expiresAt, shortLink.expiresAt) && Objects.equals(maxClicks, shortLink.maxClicks) && Objects.equals(totalClicks, shortLink.totalClicks) && Objects.equals(uniqueClicks, shortLink.uniqueClicks) && Objects.equals(createdAt, shortLink.createdAt) && Objects.equals(updatedAt, shortLink.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias, originalUrl, title, description, isCustomAlias, isActive, isPasswordProtected, passwordHash, expiresAt, maxClicks, totalClicks, uniqueClicks, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "ShortLink{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCustomAlias=" + isCustomAlias +
                ", isActive=" + isActive +
                ", isPasswordProtected=" + isPasswordProtected +
                ", passwordHash='" + passwordHash + '\'' +
                ", expiresAt=" + expiresAt +
                ", maxClicks=" + maxClicks +
                ", totalClicks=" + totalClicks +
                ", uniqueClicks=" + uniqueClicks +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}