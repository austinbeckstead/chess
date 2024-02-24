package service;

import dataAccess.*;
import model.GameData;

public class GameService {
    public MemoryAuthDAO authDAO;
    public MemoryUserDAO userDAO;

    public MemoryGameDAO gameDAO;
    public GameService(MemoryAuthDAO authDAO, MemoryUserDAO userDAO, MemoryGameDAO gameDAO){
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }

    public void clear() throws DataAccessException{
        authDAO.clear();
        userDAO.clear();
        gameDAO.clear();
    }
    public boolean isEmpty(){
        return (authDAO.isEmpty() && userDAO.isEmpty() && gameDAO.isEmpty());
    }
    public GameData createGame(String auth, String gameName){
        if(authDAO.getAuth(auth) != null) {
            return gameDAO.createGame(gameName);
        }
        else{
            return null;
        }
    }



}
