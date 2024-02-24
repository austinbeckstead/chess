package service;

import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import model.AuthData;
import model.UserData;

public class UserService {
    public MemoryUserDAO userDAO;
    public MemoryAuthDAO authDAO;

    public UserService(){
        userDAO = new MemoryUserDAO();
        authDAO = new MemoryAuthDAO();
    }
    public AuthData addUser(UserData userData) throws DataAccessException {
        if(userDAO.getUser(userData) == null){
            userDAO.createUser(userData);
            return authDAO.createAuth(userData.username());
        }
        else{
            return null;
        }
    }


}
