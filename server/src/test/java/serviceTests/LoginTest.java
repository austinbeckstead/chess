package serviceTests;
import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;
import server.Server;
import service.GameService;
import service.UserService;

public class LoginTest {
    private static Server server;
    private final MemoryGameDAO gameDAO = new MemoryGameDAO();
    private final MemoryUserDAO userDAO = new MemoryUserDAO();

    private final MemoryAuthDAO authDAO = new MemoryAuthDAO();
    private final GameService gameService = new GameService(authDAO, userDAO, gameDAO);;
    private final UserService userService = new UserService(authDAO, userDAO, gameDAO);;

    private static TestModels.TestCreateRequest createRequest;
    @Test
    @Order(0)
    @DisplayName("Login When Empty")
    public void regEmpty() throws DataAccessException {
        String username = "username";
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        assert(token == null);
    }
    @Test
    @Order(1)
    @DisplayName("Successful login")
    public void regDuplicate() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        assert(token.username().equals(username));
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

