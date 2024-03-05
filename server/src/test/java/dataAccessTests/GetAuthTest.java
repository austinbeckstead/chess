package dataAccessTests;
import dataAccess.*;
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
    public void registerUser() throws DataAccessException {
        assert((authDAO.createAuth("username")).username().equals("username"));
    }
    @Test
    @Order(1)
    @DisplayName("Invalid username")
    public void createAuthTest() throws DataAccessException {
        try {
            authDAO.createAuth(null);
            assert(false);
        }catch(Exception e){
            assert(true);
        }
    }

}

