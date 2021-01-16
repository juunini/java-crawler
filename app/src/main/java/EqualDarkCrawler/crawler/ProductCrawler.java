package EqualDarkCrawler.crawler;

public interface ProductCrawler {
    void setTargetURL(String url) throws Exception;
    boolean isValidPage();
    boolean isSoldOut();
    String getName();
    float getPrice();
    float getSalePrice();
}
