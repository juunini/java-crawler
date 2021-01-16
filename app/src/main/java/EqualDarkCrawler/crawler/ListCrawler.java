package EqualDarkCrawler.crawler;

import java.util.List;

public interface ListCrawler {
    boolean isValidPage();
    List<String> getProductsURL();
}
