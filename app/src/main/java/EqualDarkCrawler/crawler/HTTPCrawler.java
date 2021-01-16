package EqualDarkCrawler.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTTPCrawler {
    protected Document doc;

    public void getDocument(String url) throws Exception {
        this.doc = Jsoup.connect(url).get();
    };
}
