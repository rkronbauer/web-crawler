package com.webcrawler;

import com.webcrawler.services.WebCrawlerService;

/**
 * Start generating web crawler
 *
 */
public class WebCrawlerApp {
    public static void main( String[] args ) {
        WebCrawlerService crawlerService = new WebCrawlerService();
        crawlerService.generate();
    }
}
