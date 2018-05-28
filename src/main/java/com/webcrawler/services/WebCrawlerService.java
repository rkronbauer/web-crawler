package com.webcrawler.services;

import com.webcrawler.util.WebCrawlerConstants;
import com.webcrawler.vo.WebCrawlerVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class WebCrawlerService {

    private Set<String> pagesVisited;
    private List<String> pagesToVisit;
    private List<WebCrawlerVO> pages;

    public WebCrawlerService() {
        pagesVisited = new HashSet<>();
        pagesToVisit = new ArrayList<>();
        pages = new ArrayList<>();
    }

    /**
     * Start process by pre-defined URL and call write file service to generate the file
     */
    public void generate() {
        crawl(WebCrawlerConstants.URL);
        WriteFileService writeFileService = new WriteFileService();
        writeFileService.writeToFile(pages);
    }

    /**
     * Verify which URL should seek and fire it
     *
     * @param url
     */
    public void crawl(String url) {
        if (pagesToVisit.isEmpty()) {
            getLinksAndImagesFromUrl(url);
        }
        if (WebCrawlerConstants.FULL_CRAWLER)
            getNextUrl();
    }

    /**
     * Seek page requested and validate all links and images retrieved from page
     *
     * @param url
     */
    public void getLinksAndImagesFromUrl(String url) {
        Document document = getDocumentPage(url);

        //URL not accessed is not tracked here
        if (document != null) {
            WebCrawlerVO webCrawlerVO = new WebCrawlerVO();
            webCrawlerVO.setPageUrl(url);
            webCrawlerVO.setPageTitle(getPageTitle(document));
            webCrawlerVO.setLinksOnPage(getLinksOnPage(document));
            webCrawlerVO.setImagesOnPage(getImagesOnPage(document));
            webCrawlerVO.setImportsOnPage(getImportsOnPage(document));
            webCrawlerVO.setScriptsOnPage(getScriptsOnPage(document));
            webCrawlerVO.setStatusVisited(WebCrawlerConstants.STATUS_SUCCESS);
            pages.add(webCrawlerVO);
        }
    }

    /**
     * Get HTML page from URL
     *
     * @param url
     * @return document
     */
    public Document getDocumentPage(String url) {
        try {
            System.out.println(String.format(WebCrawlerConstants.FETCHING_URL, url));
            Connection connection = Jsoup.connect(url)
                    .userAgent(WebCrawlerConstants.USER_AGENT)
                    .timeout(WebCrawlerConstants.TIMEOUT);
            Document document = connection.get();
            if (connection.response().contentType().contains(WebCrawlerConstants.HTML_PAGE)) return document;
        } catch (IOException e) {
            System.err.println(String.format(WebCrawlerConstants.STATUS_ERROR_LOG, e.getMessage()));
            //Tracking URL with issue on retrieving here
            WebCrawlerVO webCrawlerVO = new WebCrawlerVO();
            webCrawlerVO.setPageUrl(url);
            webCrawlerVO.setStatusVisited(WebCrawlerConstants.STATUS_ERROR);
            pages.add(webCrawlerVO);
        } finally {
            pagesVisited.add(url);
        }
        return null;
    }

    /**
     * Get all links retrieved from page and filter the ones should be accessed
     *
     * @param document
     * @return linksOnPage
     */
    public List<String> getLinksOnPage(Document document) {
        Elements linksPage = document.select(WebCrawlerConstants.LINKS_SELECTOR);
        List<String> linksOnPage = new ArrayList<>();
        String linkAbs;
        for (Element link : linksPage) {
            linkAbs = link.attr(WebCrawlerConstants.ABS_URL_HREF);
            linksOnPage.add(linkAbs);
            validateUrlToVisit(linkAbs);
        }
        return linksOnPage;
    }

    /**
     * Get only retrieved images displayed on the page
     *
     * @param document
     * @return imagesOnPage
     */
    public List<String> getImagesOnPage(Document document) {
        Elements imagesPage = document.select(WebCrawlerConstants.MEDIA_SELECTOR);
        List<String> imagesOnPage = new ArrayList<>();
        for (Element image : imagesPage) {
            if (image.tagName().equals(WebCrawlerConstants.IMG)
                    && !image.attr(WebCrawlerConstants.IMG_STYLE).contains(WebCrawlerConstants.IMG_DISPLAY_NONE)) {
                imagesOnPage.add(image.attr(WebCrawlerConstants.ABS_URL_SRC));
            }
        }
        return imagesOnPage;
    }

    /**
     * Get import links
     * @param document
     * @return
     */
    public List<String> getImportsOnPage(Document document) {
        Elements importsPage = document.select(WebCrawlerConstants.IMPORTS_SELECTOR);
        List<String> importsOnPage = new ArrayList<>();
        for (Element link : importsPage) {
            importsOnPage.add(link.attr(WebCrawlerConstants.IMPORTS_ABS_URL));
        }
        return importsOnPage;
    }

    /**
     * Get script links
     * @param document
     * @return
     */
    public List<String> getScriptsOnPage(Document document) {
        Elements scriptsPage = document.select(WebCrawlerConstants.SCRIPTS_SELECTOR);
        List<String> scriptsOnPage = new ArrayList<>();
        for (Element script : scriptsPage) {
            scriptsOnPage.add(script.attr(WebCrawlerConstants.SCRIPTS_ABS_URL));
        }
        return scriptsOnPage;
    }

    /**
     * Get page title
     *
     * @param document
     * @return pageTitle
     */
    public String getPageTitle(Document document) {
        String pageTitle;
        Elements metaTitle = document.select(WebCrawlerConstants.PAGE_TITLE_SELECTOR);
        if (metaTitle != null) {
            pageTitle = metaTitle.attr(WebCrawlerConstants.TITLE_CONTENT);
        } else {
            pageTitle = document.title();
        }
        return pageTitle;
    }

    /**
     * Get valid URL should be accessed
     * Valid URLs are at the same domain of main URL seeked, was not previously accessed and does not download any file
     *
     * @param url
     */
    public void validateUrlToVisit(String url) {
        if (url.contains(WebCrawlerConstants.URL_TO_VISIT) && !pagesToVisit.contains(url) && !Arrays.stream(WebCrawlerConstants.FILES).anyMatch(url::contains)) {
            pagesToVisit.add(url);
        }
    }

    /**
     * Get next URL not accessed previously
     */
    public void getNextUrl() {
        String nextUrl;
        while (!pagesToVisit.isEmpty()) {
            nextUrl = pagesToVisit.get(0);
            if (!pagesVisited.contains(nextUrl)) {
                getLinksAndImagesFromUrl(nextUrl);
            }
            pagesToVisit.remove(0);
        }
    }

}
