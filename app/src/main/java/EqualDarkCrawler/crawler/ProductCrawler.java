package EqualDarkCrawler.crawler;

public interface ProductCrawler {
    public Boolean isValidPage();
    public Boolean isSoldOut();
    public String getName();
    public float getPrice();
    public float getSalePrice();
    public String[] getImages();
    public Size[] getSizes();
    public String getDescription();
}
