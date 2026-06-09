package com.snip.controller;

import com.snip.entity.Link;
import com.snip.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing short links.
 *
 * @author [Your Name]
 */
@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    /**
     * Constructs a new LinkController instance.
     *
     * @param linkService the link service to use
     */
    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    /**
     * Creates a new short link.
     *
     * @param link the link to create
     * @return the created link
     */
    @PostMapping
    public ResponseEntity<Link> createLink(@RequestBody Link link) {
        Link createdLink = linkService.createLink(link);
        return new ResponseEntity<>(createdLink, HttpStatus.CREATED);
    }

    /**
     * Retrieves all short links.
     *
     * @return a list of all short links
     */
    @GetMapping
    public ResponseEntity<List<Link>> getAllLinks() {
        List<Link> links = linkService.getAllLinks();
        return new ResponseEntity<>(links, HttpStatus.OK);
    }

    /**
     * Retrieves a short link by ID.
     *
     * @param id the ID of the link to retrieve
     * @return the retrieved link
     */
    @GetMapping("/{id}")
    public ResponseEntity<Link> getLinkById(@PathVariable Long id) {
        Link link = linkService.getLinkById(id);
        return new ResponseEntity<>(link, HttpStatus.OK);
    }

    /**
     * Updates a short link.
     *
     * @param id   the ID of the link to update
     * @param link the updated link
     * @return the updated link
     */
    @PutMapping("/{id}")
    public ResponseEntity<Link> updateLink(@PathVariable Long id, @RequestBody Link link) {
        Link updatedLink = linkService.updateLink(id, link);
        return new ResponseEntity<>(updatedLink, HttpStatus.OK);
    }

    /**
     * Deletes a short link by ID.
     *
     * @param id the ID of the link to delete
     * @return a success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLink(@PathVariable Long id) {
        linkService.deleteLink(id);
        return new ResponseEntity<>("Link deleted successfully", HttpStatus.OK);
    }
}