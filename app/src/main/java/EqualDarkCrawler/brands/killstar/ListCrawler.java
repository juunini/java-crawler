package EqualDarkCrawler.brands.killstar;

import EqualDarkCrawler.crawler.HTTPCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ListCrawler extends HTTPCrawler implements EqualDarkCrawler.crawler.ListCrawler {
    @Override
    public void getDocument(String url) throws Exception {
        this.doc = Jsoup.connect(url).get();
    }

    @Override
    public Boolean isValidPage() {
        return null;
    }

    @Override
    public String[] getProductsURL() {
        return new String[0];
    }
}
