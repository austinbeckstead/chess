package serviceTests;/*package serviceTests;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;
import server.Server;
import service.GameService;


public class ClearTest {
    private static Server server;

    private static TestModels.TestCreateRequest createRequest;

    @Test
    @Order(0)
    @DisplayName("Clear Test")
    public void clearTest() throws DataAccessException {
        GameService service = new GameService();
        service.clear();
        assert service.gameDAO.data.isEmpty();
    }
}*/

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import passoffTests.testClasses.TestModels;

import server.Server;


public class ClearTest {
    private static Server server;
    private static TestModels.TestCreateRequest createRequest;
    public static void main(String[] args) {
        // Define the URL of the server's clear API endpoint
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        String clearEndpoint = "http://localhost:" + port + "/db";

        // Create an HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create a DELETE request to the clear API endpoint
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(clearEndpoint))
                .DELETE()
                .build();

        // Send the request and handle the response
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            System.out.println("Response Status Code: " + statusCode);

            if (statusCode == 200) {
                System.out.println("Clear request succeeded");
            } else {
                System.out.println("Clear request failed with status code " + statusCode);
            }
        } catch (Exception e) {
            System.err.println("Error making clear request: " + e.getMessage());
        }
    }
}
