package dataAccessTests;
import dataAccess.*;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;



public class VerifyPasswordTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public VerifyPasswordTest() throws DataAccessException {
    }

    @Test
    @Order(0)
    @DisplayName("Verify Password")
    public void verifyPassword() throws DataAccessException {
        userDAO.clear();
        UserData userData = new UserData("username", "password", "email");
        userDAO.createUser(userData);
        assert userDAO.verifyPassword(userData);
    }
    @Test
    @Order(1)
    @DisplayName("Invalid Password")
    public void invalidPassword() throws DataAccessException {
        userDAO.clear();
        UserData userData = new UserData("username", "password", "email");
        userDAO.createUser(userData);
        assert !userDAO.verifyPassword(new UserData("username", "bad", "email"));
    }
}


