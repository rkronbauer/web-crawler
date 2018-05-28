package com.webcrawler.vo;

import java.util.List;

public class WebCrawlerVO {
    private String pageUrl;
    private String pageTitle;
    private List<String> linksOnPage;
    private List<String> imagesOnPage;
    private List<String> importsOnPage;
    private List<String> scriptsOnPage;
    private String statusVisited;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public List<String> getLinksOnPage() {
        return linksOnPage;
    }

    public void setLinksOnPage(List<String> linksOnPage) {
        this.linksOnPage = linksOnPage;
    }

    public List<String> getImagesOnPage() {
        return imagesOnPage;
    }

    public void setImagesOnPage(List<String> imagesOnPage) {
        this.imagesOnPage = imagesOnPage;
    }

    public List<String> getImportsOnPage() {
        return importsOnPage;
    }

    public void setImportsOnPage(List<String> importsOnPage) {
        this.importsOnPage = importsOnPage;
    }

    public List<String> getScriptsOnPage() {
        return scriptsOnPage;
    }

    public void setScriptsOnPage(List<String> scriptsOnPage) {
        this.scriptsOnPage = scriptsOnPage;
    }

    public String getStatusVisited() {
        return statusVisited;
    }

    public void setStatusVisited(String statusVisited) {
        this.statusVisited = statusVisited;
    }
}
