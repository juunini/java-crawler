package EqualDarkCrawler.utils;

public class GetFloatFromText {
    public float insert(String s) {
        String i = s.replaceAll("[^0-9|.]", "");
        return Float.parseFloat(i);
    }
}
