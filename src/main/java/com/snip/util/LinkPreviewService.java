package com.snip.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service class to fetch Open Graph metadata from a given URL.
 */
@Service
public class LinkPreviewService {

    /**
     * Fetches the Open Graph title and description from a given URL.
     * 
     * @param url the URL to fetch metadata from
     * @return a string array containing the title and description
     * @throws IOException if an I/O error occurs
     */
    public String[] fetchOGMetadata(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Element titleElement = doc.selectFirst("meta[property='og:title']");
            Element descriptionElement = doc.selectFirst("meta[property='og:description']");

            String title = titleElement != null ? titleElement.attr("content") : null;
            String description = descriptionElement != null ? descriptionElement.attr("content") : null;

            return new String[] { title, description };
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch Open Graph metadata", e);
        }
    }
}