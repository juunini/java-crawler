package EqualDarkCrawler.brands.killstar;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class ProductCrawlerTest {
    private static final int PORT = 9000;
    private static ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(PORT);
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
    }

    private byte[] readHTMLFile(String filePath) {
        InputStream resourceAsStream = getClass().getClassLoader()
                .getResourceAsStream(filePath);
        try {
            assert resourceAsStream != null;
            return IOUtils.toByteArray(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("file IO 실패");
        }
    }

    private void setProductsServer() {
        byte[] responseBody = readHTMLFile("killstar_products_page.html");
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(responseBody)
                );
    }

    private void setNoSaleProductServer() {
        byte[] responseBody = readHTMLFile("killstar_no_sale_product_page.html");
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(responseBody)
                );
    }

    private void setSaleProductServer() {
        byte[] responseBody = readHTMLFile("killstar_sale_product_page.html");
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(responseBody)
                );
    }

    private void setSoldOutServer() {
        byte[] responseBody = readHTMLFile("killstar_sold_out_product_page.html");
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(responseBody)
                );
    }

    @Test
    public void successIsValidPage() throws Exception {
        setSaleProductServer();

        ProductCrawler crawler = new ProductCrawler();
        crawler.getDocument("http://localhost:" + PORT);
        boolean isValidPage = crawler.isValidPage();

        assertTrue(isValidPage);
    }

    @Test
    public void failedIsValidPage() throws Exception {
        setProductsServer();

        ProductCrawler crawler = new ProductCrawler();
        crawler.getDocument("http://localhost:" + PORT);
        boolean isValidPage = crawler.isValidPage();

        assertFalse(isValidPage);
    }

    @Test
    public void falseIsSoldOut() throws Exception {
        setSaleProductServer();

        ProductCrawler crawler = new ProductCrawler();
        crawler.getDocument("http://localhost:" + PORT);
        boolean isSoldOut = crawler.isSoldOut();

        assertFalse(isSoldOut);
    }

    @Test
    public void trueIsSoldOut() throws Exception {
        setSoldOutServer();

        ProductCrawler crawler = new ProductCrawler();
        crawler.getDocument("http://localhost:" + PORT);
        boolean isSoldOut = crawler.isSoldOut();

        assertTrue(isSoldOut);
    }

    @Test
    public void getName() throws Exception {
        setSaleProductServer();

        ProductCrawler crawler = new ProductCrawler();
        crawler.getDocument("http://localhost:" + PORT);
        String name = crawler.getName();

        assertEquals("Milky Way Babydoll Dress", name);
    }
}
