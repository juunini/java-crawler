package EqualDarkCrawler.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetFloatFromTextTest {
    @Test
    public void InsertTest() {
        float actual = new GetFloatFromText().insert("£79.99");

        assertEquals(79.99f, actual);
    }
}
