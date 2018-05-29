# Web Crawler

Web Crawler to retrieve internal and external links, image's urls and static files urls based on a given URL(http://www.sicredi.com.br). This app will visit all internal URLs and fetch the same information for all internal pages. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

 - [Java8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
 - [Maven](https://maven.apache.org/) - Dependency Management

### Installing

After installing Java 8 and getting Maven working on your local machine, you will need to clone this project and run the following command line on your project cloned path:

```git
mvn clean install
```

### Executiong

After above steps, you should be able to run de project executing below Maven command in the same project path you ran the above Maven command:

```git
mvn exec:java -D exec.mainClass=com.webcrawler.WebCrawlerApp
```

### Additional information

If you don't want to fetch all internal links from a given URL, you just need to set the constant variable FULL_CRAWLER to false at WebCrawlerConstants class:

```java
public interface WebCrawlerConstants {
    public static final boolean FULL_CRAWLER = true;
}
```

## Authors

* **Raffael Kronbauer** - *Initial work* - [rkronbauer](https://github.com/rkronbauer)
