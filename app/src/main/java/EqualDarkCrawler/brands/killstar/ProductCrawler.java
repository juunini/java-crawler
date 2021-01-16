package EqualDarkCrawler.brands.killstar;

import EqualDarkCrawler.crawler.HTTPCrawler;
import EqualDarkCrawler.crawler.Size;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

public class ProductCrawler extends HTTPCrawler implements EqualDarkCrawler.crawler.ProductCrawler {
    @Override
    public boolean isValidPage() {
        return !this.doc
                .select("[uk-grid] h2")
                .isEmpty();
    }

    @Override
    public boolean isSoldOut() {
        Elements soldOutSelector = this.doc.select(".mp-product-when-available > button > span");
        String selectorText = soldOutSelector.text().replaceAll("[^a-zA-Z]", "");
        return selectorText.equalsIgnoreCase("soldout");
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
