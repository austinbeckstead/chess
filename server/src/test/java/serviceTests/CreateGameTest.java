package serviceTests;
import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
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

public class CreateGameTest {
    private static Server server;
    private final MemoryGameDAO gameDAO = new MemoryGameDAO();
    private final MemoryUserDAO userDAO = new MemoryUserDAO();

    private final MemoryAuthDAO authDAO = new MemoryAuthDAO();
    private final GameService gameService = new GameService(authDAO, userDAO, gameDAO);;
    private final UserService userService = new UserService(authDAO, userDAO, gameDAO);;

    private static TestModels.TestCreateRequest createRequest;
    @Test
    @Order(0)
    @DisplayName("Create Game No Auth")
    public void regEmpty() throws DataAccessException {
        GameData result = gameService.createGame("11","father");
        assert(result == null);
    }
    @Test
    @Order(1)
    @DisplayName("Successful Game Create")
    public void regDuplicate() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData result = gameService.createGame(token.authToken(), "father");
        assert(result.gameName().equals("father"));
    }
    @Test
    @Order(2)
    @DisplayName("Incorrect Password")
    public void regIncorrectPassword() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "wrong", null));
        assert(token == null);
    }

}


