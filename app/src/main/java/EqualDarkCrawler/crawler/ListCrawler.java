package EqualDarkCrawler.crawler;

import java.util.List;

public interface ListCrawler {
    public boolean isValidPage();
    public List<String> getProductsURL();
}
