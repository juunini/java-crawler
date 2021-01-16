package EqualDarkCrawler.brands.killstar;

import EqualDarkCrawler.crawler.HTTPCrawler;
import EqualDarkCrawler.crawler.Size;

public class ProductCrawler extends HTTPCrawler implements EqualDarkCrawler.crawler.ProductCrawler {
    @Override
    public Boolean isValidPage() {
        return null;
    }

    @Override
    public Boolean isSoldOut() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public float getPrice() {
        return 0;
    }

    @Override
    public float getSalePrice() {
        return 0;
    }

    @Override
    public String[] getImages() {
        return new String[0];
    }

    @Override
    public Size[] getSizes() {
        return new Size[0];
    }

    @Override
    public String getDescription() {
        return null;
    }
}
