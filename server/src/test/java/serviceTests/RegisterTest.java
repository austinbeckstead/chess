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

public class RegisterTest {
    private static Server server;
    private final MemoryGameDAO gameDAO = new MemoryGameDAO();
    private final MemoryUserDAO userDAO = new MemoryUserDAO();

    private final MemoryAuthDAO authDAO = new MemoryAuthDAO();
    private final GameService gameService = new GameService(authDAO, userDAO, gameDAO);;
    private final UserService userService = new UserService(authDAO, userDAO, gameDAO);;

    private static TestModels.TestCreateRequest createRequest;
    @Test
    @Order(0)
    @DisplayName("Register When Empty")
    public void regEmpty() throws DataAccessException {
        String username = "username";
        AuthData token = userService.addUser(new UserData(username, "password", "email"));
        assert(token.username().equals(username));
    }
    @Test
    @Order(1)
    @DisplayName("Register Duplicate Username")
    public void regDuplicate() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.addUser(new UserData(username, "password", "email"));
        assert(token == null);
    }

}
