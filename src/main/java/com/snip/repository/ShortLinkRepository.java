package com.snip.repository;

import com.snip.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for ShortLink entity.
 * Provides methods for finding by alias and retrieving all short links.
 */
@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {

    /**
     * Finds a short link by its alias.
     * 
     * @param alias the alias to search for
     * @return the short link with the given alias, or null if not found
     */
    @Query("SELECT sl FROM ShortLink sl WHERE sl.alias = :alias")
    ShortLink findByAlias(@Param("alias") String alias);

    /**
     * Retrieves all short links.
     * 
     * @return a list of all short links
     */
    @Override
    @Query("SELECT sl FROM ShortLink sl")
    List<ShortLink> findAll();
}