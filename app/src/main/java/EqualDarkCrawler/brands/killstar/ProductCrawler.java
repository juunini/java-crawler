package EqualDarkCrawler.brands.killstar;

import EqualDarkCrawler.crawler.HTTPCrawler;
import EqualDarkCrawler.utils.GetFloatFromText;
import org.jsoup.select.Elements;

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
        return this.doc
                .select("[uk-grid] h2")
                .first()
                .text();
    }

    @Override
    public float getPrice() {
        String priceStr = this.doc
                .select("[data-price-wrapper] .money")
                .first()
                .text();
        return new GetFloatFromText().insert(priceStr);
    }

    @Override
    public float getSalePrice() {
        String priceStr = this.doc
                .select("[data-price-wrapper] .money")
                .last()
                .text();
        return new GetFloatFromText().insert(priceStr);
    }
}
