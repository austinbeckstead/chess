package dataAccessTests;
import dataAccess.*;
import model.AuthData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;



public class GetAuthTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public GetAuthTest() throws DataAccessException {
    }
    @Test
    @Order(0)
    @DisplayName("Create Auth")
    public void getAuthTest() throws DataAccessException {
        AuthData authData = authDAO.createAuth("username");
        String authToken = authData.authToken();
        assert authDAO.getAuth(authToken).username().equals(authData.username());

    }
    @Test
    @Order(1)
    @DisplayName("Bad Auth")
    public void badAuthTest() throws DataAccessException {
        assert authDAO.getAuth("THIS") == null;
    }
}

