package EqualDarkCrawler.crawler;

import java.util.List;

public interface ListCrawler {
    void setTargetURL(String url) throws Exception;
    boolean isValidPage() throws Exception;
    List<String> getProductsURL() throws Exception;
}
