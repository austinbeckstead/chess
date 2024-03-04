package serviceTests;
import dataAccess.*;
import model.AuthData;
import model.GameData;
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
    private final GameDAO gameDAO = new MemoryGameDAO();
    private final UserDAO userDAO = new MemoryUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();
    GameService gameService = new GameService(authDAO, userDAO, gameDAO);
    UserService userService = new UserService(authDAO, userDAO, gameDAO);

    private static TestModels.TestCreateRequest createRequest;

    public ClearTest() throws DataAccessException {
    }

    @Test
    @Order(0)
    @DisplayName("Clear Empty")
    public void clearTest() throws DataAccessException {
        gameService.clear();
        assert gameService.isEmpty();
    }
    @Test
    @Order(1)
    @DisplayName("Clear With User")
    public void registerClearTest() throws DataAccessException {
        userService.addUser(new UserData("username", "password", "email"));
        gameService.clear();
        assert gameService.isEmpty();
    }
    @Test
    @Order(2)
    @DisplayName("Clear Auth")
    public void clearAuth() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        gameService.clear();
        assert gameService.isEmpty();
    }
    @Test
    @Order(3)
    @DisplayName("Clear Game")
    public void clearGame() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData result = gameService.createGame(token.authToken(), "father");
        gameService.clear();
        assert gameService.isEmpty();
    }

}
