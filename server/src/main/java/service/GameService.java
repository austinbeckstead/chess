package service;

import dataAccess.*;
public class GameService {
    private MemoryAuthDAO authDAO;
    private MemoryUserDAO userDAO;

    private MemoryGameDAO gameDAO;
    public GameService(){
        authDAO = new MemoryAuthDAO();
        userDAO = new MemoryUserDAO();
        gameDAO = new MemoryGameDAO();
    }

    public void clear() throws DataAccessException{
        authDAO.clear();
        userDAO.clear();
        gameDAO.clear();
    }
}
