package dataAccess;

import model.GameData;
import service.request.JoinRequest;

public interface GameDAO {
    void clear() throws DataAccessException;
    GameData getGame(String id) throws DataAccessException;
    boolean isEmpty() throws DataAccessException;
    GameData createGame(String gameName) throws DataAccessException;
    GameData[] listGames() throws DataAccessException;
    String joinGame(JoinRequest request, String username) throws DataAccessException;
}
