package ui;

import dataAccess.DataAccessException;
import model.GameData;
import model.UserData;
import service.request.JoinRequest;
import service.result.*;

public class ServerFacade {
    private final ClientCommunicator communicator;

    public ServerFacade(String serverUrl){
        this.communicator = new ClientCommunicator(serverUrl);
    }
    public Object clear() throws DataAccessException {
        var path = "/db";
        return communicator.makeRequest("DELETE", path, null, null, Result.class);
    }
    public Object register(UserData data) throws DataAccessException{
        var path = "/user";
        return communicator.makeRequest("POST", path, data, null, Result.class);
    }
    public Object login(UserData data) throws DataAccessException{
        var path = "/session";
        return communicator.makeRequest("POST", path, data, null, LoginResult.class);
    }
    public Object logout(String auth) throws DataAccessException{
        var path = "/session";
        return communicator.makeRequest("DELETE", path, null, auth, Result.class);
    }
    public Object create(GameData data, String auth) throws DataAccessException{
        var path = "/game";
        return communicator.makeRequest("POST", path, data, auth, GameResult.class);
    }
    public Object list(String auth) throws DataAccessException{
        var path = "/game";
        return communicator.makeRequest("GET", path, null, auth, ListGamesResult.class);
    }
    public Object join(JoinRequest request, String auth) throws DataAccessException{
        var path = "/game";
        return communicator.makeRequest("PUT", path, request, auth, Result.class);
    }
}
