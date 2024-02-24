package service;

import dataAccess.*;
import model.AuthData;
import model.GameData;
import service.request.JoinRequest;

import javax.xml.crypto.Data;

public class GameService {
    public AuthDAO authDAO;
    public UserDAO userDAO;

    public GameDAO gameDAO;
    public GameService(AuthDAO authDAO, UserDAO userDAO, GameDAO gameDAO){
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }

    public void clear() throws DataAccessException{
        authDAO.clear();
        userDAO.clear();
        gameDAO.clear();
    }
    public boolean isEmpty() throws DataAccessException{
        return (authDAO.isEmpty() && userDAO.isEmpty() && gameDAO.isEmpty());
    }
    public GameData createGame(String auth, String gameName) throws DataAccessException{
        if(authDAO.getAuth(auth) != null) {
            return gameDAO.createGame(gameName);
        }
        else{
            return null;
        }
    }
    public GameData[] listGames(String auth) throws DataAccessException {
        if(authDAO.getAuth(auth) != null) {
            return gameDAO.listGames();
        }
        else{
            return null;
        }

    }
    public String joinGame(String auth, JoinRequest request) throws DataAccessException{
        AuthData authToken = authDAO.getAuth(auth);
        if(authToken != null) {
            return gameDAO.joinGame(request, authToken.username());
        }
        else{
            return "Error: unauthorized";
        }

        }

}
