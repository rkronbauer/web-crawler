package com.webcrawler.util;

public interface WebCrawlerConstants {

    public static final boolean FULL_CRAWLER = true;

    public static final String URL = "https://www.sicredi.com.br";
    public static final String URL_TO_VISIT = "www.sicredi.com.br";
    public static final String FILE_NAME = "Sicred_Sitemap.txt";
    public static final String ABS_URL_HREF = "abs:href";
    public static final String LINKS_SELECTOR = "a[href^=\"http\"]";
    public static final String MEDIA_SELECTOR = "[src]";
    public static final String ABS_URL_SRC = "abs:src";
    public static final String IMG_STYLE = "style";
    public static final String IMG = "img";
    public static final String IMG_DISPLAY_NONE = "display:none";
    public static final String PAGE_TITLE_SELECTOR = "meta[property=og:title]";
    public static final String TITLE_CONTENT = "content";
    public static final String IMPORTS_SELECTOR = "link[href]";
    public static final String IMPORTS_ABS_URL = "abs:href";
    public static final String SCRIPTS_SELECTOR = "script[src]";
    public static final String SCRIPTS_ABS_URL = "abs:src";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0";
    public static final String HTML_PAGE = "text/html";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";

    public static final String[] FILES = {".pdf",".doc",".xls"};

    public static final int TIMEOUT = 60000;

    //FILE SECTION
    public static final String TEXT_URL_HEADER = "- URL: %s (title: %s)\n";
    public static final String INVALID_URL = "INVALID URL";
    public static final String TITLE_NOT_AVAILABLE = "TITLE NOT AVAILABLE";
    public static final String IMG_COUNT = "IMAGES: (%s)\n";
    public static final String IMG_URL = " * img: <%s>\n";
    public static final String LINK_COUNT = "LINKS: (%s)\n";
    public static final String LINK_URL = " * link: <%s>\n";
    public static final String IMPORT_COUNT = "IMPORTS: (%s)\n";
    public static final String IMPORT_URL = " * link: <%s>\n";
    public static final String SCRIPTS_COUNT = "SCRIPTS: (%s)\n";
    public static final String SCRIPTS_URL = " * link: <%s>\n";
    public static final String EXTRA_BREAK_LINE = "\n";

    //LOG SECTION
    public static final String STATUS_ERROR_LOG = "ERROR: %s";
    public static final String FETCHING_URL = "Fetching URL: %s";
    public static final String FILE_GENERATED = "File 'Sicred_Sitemap.txt' generated at your project folder.";
}
