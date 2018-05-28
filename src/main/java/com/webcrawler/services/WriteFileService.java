package com.webcrawler.services;

import com.webcrawler.util.WebCrawlerConstants;
import com.webcrawler.vo.WebCrawlerVO;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.List;

public class WriteFileService {

    public void writeToFile(List<WebCrawlerVO> pages) {
        final File file = new File(WebCrawlerConstants.FILE_NAME);
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (WebCrawlerVO page : pages) {
                try {
                    String lineUrlTitle;
                    if (page.getStatusVisited().equals(WebCrawlerConstants.STATUS_SUCCESS)) {
                        lineUrlTitle = String.format(WebCrawlerConstants.TEXT_URL_HEADER, page.getPageUrl(),
                                StringUtils.EMPTY.equals(page.getPageTitle()) ? WebCrawlerConstants.TITLE_NOT_AVAILABLE : page.getPageTitle());
                        writer.write(lineUrlTitle);
                        //printing link section
                        String lineLinkCount = String.format(WebCrawlerConstants.LINK_COUNT, page.getLinksOnPage().size());
                        writer.write(WebCrawlerConstants.EXTRA_BREAK_LINE + lineLinkCount);
                        for (String link : page.getLinksOnPage()) {
                            writer.write(String.format(WebCrawlerConstants.LINK_URL, link));
                        }
                        //printing image section
                        String lineImageCount = String.format(WebCrawlerConstants.IMG_COUNT, page.getImagesOnPage().size());
                        writer.write(WebCrawlerConstants.EXTRA_BREAK_LINE + lineImageCount);
                        for (String image : page.getImagesOnPage()) {
                            writer.write(String.format(WebCrawlerConstants.IMG_URL, image));
                        }
                        //printing imports section
                        String lineImportsCount = String.format(WebCrawlerConstants.IMPORT_COUNT, page.getImportsOnPage().size());
                        writer.write(WebCrawlerConstants.EXTRA_BREAK_LINE + lineImportsCount);
                        for (String imports : page.getImportsOnPage()) {
                            writer.write(String.format(WebCrawlerConstants.IMPORT_URL, imports));
                        }
                        //printing scripts section
                        String lineScriptsCount = String.format(WebCrawlerConstants.SCRIPTS_COUNT, page.getScriptsOnPage().size());
                        writer.write(WebCrawlerConstants.EXTRA_BREAK_LINE + lineScriptsCount);
                        for (String scripts : page.getScriptsOnPage()) {
                            writer.write(String.format(WebCrawlerConstants.SCRIPTS_URL, scripts));
                        }
                    } else {
                        lineUrlTitle = String.format(WebCrawlerConstants.TEXT_URL_HEADER + WebCrawlerConstants.EXTRA_BREAK_LINE, page.getPageUrl(), WebCrawlerConstants.INVALID_URL);
                        writer.write(lineUrlTitle);
                    }

                    writer.write(WebCrawlerConstants.EXTRA_BREAK_LINE);
                } catch (IOException e) {
                    System.err.println(WebCrawlerConstants.STATUS_ERROR + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(WebCrawlerConstants.STATUS_ERROR + ": " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    System.out.println(WebCrawlerConstants.FILE_GENERATED);
                    writer.close();
                } catch (IOException e) {
                    System.err.println(WebCrawlerConstants.STATUS_ERROR + ": " + e.getMessage());
                }
            }
        }
    }
}
