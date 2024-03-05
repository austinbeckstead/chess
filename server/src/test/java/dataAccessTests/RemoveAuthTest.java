package dataAccessTests;
import dataAccess.*;
import model.AuthData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;



public class RemoveAuthTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;


    public RemoveAuthTest() throws DataAccessException {
    }

    @Test
    @Order(0)
    @DisplayName("Remove Auth")
    public void getAuthTest() throws DataAccessException {
        AuthData authData = authDAO.createAuth("username");
        String authToken = authData.authToken();
        authDAO.removeAuth(authToken);
        assert authDAO.getAuth(authToken) == null;
    }
}

