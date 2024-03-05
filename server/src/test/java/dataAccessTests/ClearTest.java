package dataAccessTests;
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
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public ClearTest() throws DataAccessException {
    }
    @Test
    @Order(0)
    @DisplayName("Clear Data")
    public void clearData() throws DataAccessException {
        gameDAO.clear();
        userDAO.clear();
        authDAO.clear();
        userDAO.createUser(new UserData("username", "password", "email"));
        AuthData token = authDAO.createAuth("username");
        GameData result = gameDAO.createGame("gameName");
        gameDAO.clear();
        userDAO.clear();
        authDAO.clear();
        assert (gameDAO.isEmpty() && userDAO.isEmpty() && authDAO.isEmpty());
    }
    @Test
    @Order(0)
    @DisplayName("Clear Empty")
    public void clearEmpty() throws DataAccessException {
        gameDAO.clear();
        userDAO.clear();
        authDAO.clear();
        assert (gameDAO.isEmpty() && userDAO.isEmpty() && authDAO.isEmpty());
    }

}

