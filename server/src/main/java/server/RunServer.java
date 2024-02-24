package server;

import passoffTests.testClasses.TestModels;

public class RunServer {
    private static Server server;
    private static TestModels.TestCreateRequest createRequest;
    public static void main(String[] args) {
        // Define the URL of the server's clear API endpoint
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        String clearEndpoint = "http://localhost:" + port + "/db";

    }
}
