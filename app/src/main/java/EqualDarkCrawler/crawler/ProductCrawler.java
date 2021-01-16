package EqualDarkCrawler.crawler;

public interface ProductCrawler {
    public boolean isValidPage();
    public boolean isSoldOut();
    public String getName();
    public float getPrice();
    public float getSalePrice();
}
