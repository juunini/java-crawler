package EqualDarkCrawler.crawler;

public interface ProductCrawler {
    boolean isValidPage();
    boolean isSoldOut();
    String getName();
    float getPrice();
    float getSalePrice();
}
