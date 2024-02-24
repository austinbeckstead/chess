package dataAccess;

import model.UserData;

public interface UserDAO {
    void clear() throws DataAccessException;
    UserData getUser(UserData userData) throws DataAccessException;
    void createUser(UserData userData) throws DataAccessException;
    boolean isEmpty() throws DataAccessException;
    boolean verifyPassword(UserData userData) throws DataAccessException;
}
