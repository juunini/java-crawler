package EqualDarkCrawler.brands.killstar;

import EqualDarkCrawler.crawler.HTTPCrawler;
import org.jsoup.Jsoup;

public class ListCrawler extends HTTPCrawler implements EqualDarkCrawler.crawler.ListCrawler {
    @Override
    public void getDocument(String url) throws Exception {
        this.doc = Jsoup.connect(url).get();
    }

    @Override
    public boolean isValidPage() {
        return !this.doc
                .select("#mp-collection-grid > div")
                .isEmpty();
    }

    @Override
    public String[] getProductsURL() {
        return new String[0];
    }
}
