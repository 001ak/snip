package com.snip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.snip.entity.Click;

/**
 * ClickRepository is responsible for encapsulating data access to Click entity.
 * It provides basic CRUD operations.
 * 
 * @author [Your Name]
 */
@Repository
public interface ClickRepository extends CrudRepository<Click, Long> {

    /**
     * Finds a Click by its id.
     * 
     * @param id the id of the Click to find
     * @return the Click with the given id, or null if not found
     */
    @Override
    Click findById(Long id);

    /**
     * Saves a Click to the database.
     * 
     * @param click the Click to save
     * @return the saved Click
     */
    @Override
    <S extends Click> S save(S click);

    /**
     * Deletes a Click by its id.
     * 
     * @param id the id of the Click to delete
     */
    @Override
    void deleteById(Long id);

    /**
     * Finds all Clicks in the database.
     * 
     * @return a list of all Clicks
     */
    @Override
    Iterable<Click> findAll();
}