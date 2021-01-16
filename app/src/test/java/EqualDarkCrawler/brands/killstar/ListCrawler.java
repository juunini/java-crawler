package EqualDarkCrawler.brands.killstar;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;
import java.io.InputStream;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class ListCrawlerTest {
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

    private void setSuccessCaseServer() {
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

    private void setFailCaseServer() {
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

    private void setStatus500Server() {
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/")
                )
                .respond(
                        response()
                                .withStatusCode(500)
                );
    }

    @Test
    void successGetDocument() throws Exception {
        setSuccessCaseServer();

        ListCrawler crawler = new ListCrawler();
        crawler.getDocument("http://localhost:" + PORT);
    }

    @Test
    void failGetDocument() throws Exception {
        setStatus500Server();

        ListCrawler crawler = new ListCrawler();
        try {
            crawler.getDocument("http://localhost:" + PORT);
        } catch (Exception e) {
            return;
        }

        fail();
    }

    @Test
    void successIsValidPage() throws Exception {
        setSuccessCaseServer();

        ListCrawler crawler = new ListCrawler();
        crawler.getDocument("http://localhost:" + PORT);
        boolean isValidPage = crawler.isValidPage();

        assertTrue(isValidPage);
    }

    @Test
    void failIsValidPage() throws Exception {
        setFailCaseServer();

        ListCrawler crawler = new ListCrawler();
        crawler.getDocument("http://localhost:" + PORT);
        boolean isValidPage = crawler.isValidPage();

        assertFalse(isValidPage);
    }
}
