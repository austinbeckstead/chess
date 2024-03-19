package service;

import dataAccess.*;
import model.AuthData;
import model.UserData;

import javax.xml.crypto.Data;

public class UserService {
    public UserDAO userDAO;
    public AuthDAO authDAO;
    public GameDAO gameDAO;

    public UserService(AuthDAO authDAO, UserDAO userDAO, GameDAO gameDAO){
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
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
    public AuthData loginUser(UserData userData) throws DataAccessException{
        if(userDAO.getUser(userData) != null && userDAO.verifyPassword(userData)){
            return authDAO.createAuth(userData.username());
        }
        else{
            return null;
        }
    }
    public boolean logoutUser(String auth) throws DataAccessException{
        if(authDAO.getAuth(auth) != null){
            authDAO.removeAuth(auth);
            return true;
        }
        else{
            return false;
        }

    }


}
