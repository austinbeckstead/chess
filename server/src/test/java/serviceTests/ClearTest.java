package serviceTests;
import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;
import server.Server;
import service.GameService;
import service.UserService;


public class ClearTest {
    private static Server server;
    private final MemoryGameDAO gameDAO = new MemoryGameDAO();
    private final MemoryUserDAO userDAO = new MemoryUserDAO();

    private final MemoryAuthDAO authDAO = new MemoryAuthDAO();

    private static TestModels.TestCreateRequest createRequest;

    @Test
    @Order(0)
    @DisplayName("Clear Without User")
    public void clearTest() throws DataAccessException {
        GameService gameService = new GameService(authDAO, userDAO, gameDAO);
        gameService.clear();
        assert gameService.isEmpty();
    }
    @Test
    @Order(0)
    @DisplayName("Clear With User")
    public void registerClearTest() throws DataAccessException {
        GameService gameService = new GameService(authDAO, userDAO, gameDAO);
        UserService userService = new UserService(authDAO, userDAO, gameDAO);
        userService.addUser(new UserData("username", "password", "email"));
        gameService.clear();
        assert gameService.isEmpty();
    }

}
