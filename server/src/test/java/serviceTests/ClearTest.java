package serviceTests;
import passoffTests.testClasses.TestModels;
import server.Server;

public class ClearTest {
    private static Server server;
    private static TestModels.TestCreateRequest createRequest;


    public static void main(String[] args) {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        createRequest = new TestModels.TestCreateRequest();
        createRequest.gameName = "Mr. Meeseeks";



    }
}
