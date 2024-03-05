package dataAccessTests;
import dataAccess.*;
import model.GameData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;

import java.util.Objects;


public class ListGamesTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public ListGamesTest() throws DataAccessException {
    }

    @Test
    @Order(0)
    @DisplayName("List Games")
    public void listGames() throws DataAccessException {
        gameDAO.createGame("gameName");
        GameData[] list = gameDAO.listGames();
        assert(list.length == 1);
    }
    @Test
    @Order(1)
    @DisplayName("Empty List")
    public void emptyList() throws DataAccessException {
        gameDAO.clear();
        GameData[] list = gameDAO.listGames();
        assert(list.length != 1);
    }

}


