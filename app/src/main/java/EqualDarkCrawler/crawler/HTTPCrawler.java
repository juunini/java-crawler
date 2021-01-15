package EqualDarkCrawler.crawler;

import org.jsoup.nodes.Document;

public abstract class HTTPCrawler {
    protected Document doc;

    public abstract void getDocument(String url) throws Exception;
}
