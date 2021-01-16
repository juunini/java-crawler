package EqualDarkCrawler.brands.killstar;

import EqualDarkCrawler.crawler.HTTPCrawler;
import org.jsoup.Jsoup;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getProductsURL() {
        return this.doc
                .select("#mp-collection-grid > div")
                .stream()
                .map(product -> "https://www.killstar.com" + product
                        .select("a")
                        .first()
                        .attr("href")
                        .replace("collections/new-womens/", ""))
                .collect(Collectors.toList());
    }
}
