package EqualDarkCrawler.crawler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class HTTPCrawlerTest {
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

    private void setStatus200Server() {
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("<p>Hello, World!</p>")
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
                                .withBody("Internal Server Error")
                );
    }

    @Test
    void successGetDocument() throws Exception {
        setStatus200Server();

        HTTPCrawler crawler = new HTTPCrawler();
        crawler.getDocument("http://localhost:" + PORT);
    }

    @Test
    void failGetDocument() {
        setStatus500Server();

        HTTPCrawler crawler = new HTTPCrawler();
        try {
            crawler.getDocument("http://localhost:" + PORT);
        } catch (Exception e) {
            return;
        }

        fail();
    }
}
