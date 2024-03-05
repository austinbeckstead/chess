package dataAccessTests;
import dataAccess.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;



public class CreateGameTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public CreateGameTest() throws DataAccessException {
    }
    @Test
    @Order(0)
    @DisplayName("Create Game")
    public void createGame() throws DataAccessException {
        assert((gameDAO.createGame("gameName").gameName().equals("gameName")));
    }
    @Test
    @Order(1)
    @DisplayName("Create Null Game")
    public void createNullGame() throws DataAccessException {
        try {
            gameDAO.clear();
            assert(gameDAO.createGame(null).gameName() == null);
        }
        catch(Exception e) {
            assert true;
        }
    }
//No negative test because this will always work

}


