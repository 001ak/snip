package com.snip.controller;

import com.snip.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles redirects for password-protected links.
 */
@Controller
public class RedirectController {

    private final LinkService linkService;

    /**
     * Constructs a new RedirectController instance.
     * @param linkService the link service to use
     */
    @Autowired
    public RedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    /**
     * Handles GET requests to /{alias} and verifies password for password-protected links.
     * @param alias the link alias
     * @param password the password for the link (if password-protected)
     * @param model the model to add attributes to
     * @return the redirect URL or an error page
     */
    @GetMapping("/{alias}")
    public String getLink(@PathVariable String alias, @RequestParam(required = false) String password, Model model) {
        return linkService.getLink(alias, password, model);
    }
}