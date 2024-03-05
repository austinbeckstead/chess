package dataAccessTests;
import dataAccess.*;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;



public class CreateUserTest {
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();


    private static TestModels.TestCreateRequest createRequest;

    public CreateUserTest() throws DataAccessException {
    }

    @Test
    @Order(0)
    @DisplayName("Create User")
    public void createUser() throws DataAccessException {
        userDAO.clear();
        UserData userData = new UserData("username", "password", "email");
        userDAO.createUser(userData);
        assert userDAO.getUser(userData).username().equals(userData.username());
    }
    @Test
    @Order(1)
    @DisplayName("Create Null User")
    public void createNullUser() throws DataAccessException {
        try {
            userDAO.clear();
            userDAO.createUser(null);
            assert false;
        }
        catch(Exception e) {
            assert true;
        }
    }
//No negative test because this will always work
}


