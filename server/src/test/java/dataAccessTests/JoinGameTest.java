package dataAccessTests;
import dataAccess.*;
import model.GameData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;
import service.request.JoinRequest;


public class JoinGameTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public JoinGameTest() throws DataAccessException {
    }
    @Test
    @Order(0)
    @DisplayName("Join Game")
    public void joinGame() throws DataAccessException {
        GameData gameData = gameDAO.createGame("gameName");
        JoinRequest req = new JoinRequest("WHITE", gameData.gameID());
        assert(gameDAO.joinGame(req, "username") == null);
    }
    @Test
    @Order(1)
    @DisplayName("Join Game Bad ID")
    public void joinGameBadID() throws DataAccessException {
        GameData gameData = gameDAO.createGame("gameName");
        JoinRequest req = new JoinRequest("WHITE", 0);
        assert(gameDAO.joinGame(req, "username").equals("Error: bad request"));
    }
//No negative test because this will always work
}



