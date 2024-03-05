package dataAccessTests;
import dataAccess.*;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;



public class GetUserTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public GetUserTest() throws DataAccessException {
    }

    @Test
    @Order(0)
    @DisplayName("Get User")
    public void getUser() throws DataAccessException {
        userDAO.clear();

        UserData userData = new UserData("username", "password", "email");
        userDAO.createUser(userData);
        assert userDAO.getUser(userData).username().equals(userData.username());
    }
    @Test
    @Order(1)
    @DisplayName("Get Invalid User")
    public void getInvalidUser() throws DataAccessException {
        userDAO.clear();
        UserData userData = new UserData("username", "password", "email");
        userDAO.createUser(userData);
        assert userDAO.getUser(new UserData("a", "b", "c")) == null;
    }
//No negative test because this will always work
}