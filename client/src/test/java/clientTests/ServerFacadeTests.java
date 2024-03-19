package clientTests;

import dataAccess.DataAccessException;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.*;
import server.Server;
import service.request.JoinRequest;
import service.result.GameResult;
import service.result.ListGamesResult;
import service.result.LoginResult;
import service.result.Result;
import ui.ServerFacade;


public class ServerFacadeTests {

    private static Server server;
    static ServerFacade facade;


    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        String serverUrl = "http://localhost:" + port;
        facade = new ServerFacade(serverUrl);

    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    @Order(0)
    @DisplayName("Clear Data Succeeds")
    public void clearData() throws DataAccessException {
        Result r = (Result) facade.clear();
        assert (r.message() == null);
    }
    @Order(1)
    @DisplayName("Clear Data Clears a User")
    public void clearUser() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        Object r = facade.register(data);
        facade.clear();
        try{
            facade.login(data);
            assert false;
        }
        catch(DataAccessException e) {
            assert true;
        }
    }


    @Test
    @Order(2)
    @DisplayName("Register User")
    public void registerUser() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        Object r = facade.register(data);
        assert(r instanceof LoginResult);
    }
    @Test
    @Order(3)
    @DisplayName("Register Duplicate User")
    public void registerDuplicateUser() throws DataAccessException {
        try {
            facade.clear();
            UserData data = new UserData("username", "password", "fake@fake.com");
            facade.register(data);
            Object r = facade.register(data);
            assert(false);
        }
        catch (DataAccessException e){
            assert(true);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Login User")
    public void loginUser() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        facade.register(data);
        Object r = facade.login(data);
        assert(r instanceof LoginResult);
    }

    @Test
    @Order(5)
    @DisplayName("Incorrect Password")
    public void incorrectPassword() throws DataAccessException {
        try {
            facade.clear();
            UserData data = new UserData("username", "password", "fake@fake.com");
            facade.register(data);
            UserData loginData = new UserData("username", "passwor", "fake@fake.com");
            Object r = facade.login(loginData);
            assert(false);
        }
        catch(DataAccessException e) {
            assert (true);
        }

    }
    @Test
    @Order(6)
    @DisplayName("Logout User")
    public void logoutUser() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        LoginResult r = (LoginResult) facade.register(data);
        String authToken = r.authToken();
        Object result = facade.logout(authToken);
        assert(result instanceof Result);
    }
    @Test
    @Order(7)
    @DisplayName("Logout Invalid Auth")
    public void invalidAuth() throws DataAccessException {
        try {
            facade.logout("bad");
            assert(false);
        }
        catch(DataAccessException e) {
            assert (true);
        }
    }

    @Test
    @Order(8)
    @DisplayName("Create Game")
    public void createGame() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        GameData gameData = new GameData(0, null, null, "Name", null);
        LoginResult r = (LoginResult) facade.register(data);
        String authToken = r.authToken();
        Object result = facade.create(gameData, authToken);
        assert(result instanceof GameResult);
    }
    @Test
    @Order(9)
    @DisplayName("Create Game Invalid Auth")
    public void createGameInvalidAuth() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        GameData gameData = new GameData(0, null, null, "Name", null);
        try {
            facade.create(gameData, "bad");
            assert(false);
        }
        catch(DataAccessException e) {
            assert (true);
        }
    }
    @Test
    @Order(10)
    @DisplayName("List Games")
    public void listGames() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        GameData gameData = new GameData(0, null, null, "Name", null);
        LoginResult r = (LoginResult) facade.register(data);
        String authToken = r.authToken();
        Object result = facade.create(gameData, authToken);
        Object listResult = facade.list(authToken);
        assert (listResult instanceof ListGamesResult && (((ListGamesResult) listResult).games()).length != 0);
    }
    @Test
    @Order(11)
    @DisplayName("List Games Invalid Auth")
    public void listGamesInvalidAuth() throws DataAccessException {
        facade.clear();
        try {
            facade.list("bad");
            assert(false);
        }
        catch(DataAccessException e) {
            assert (true);
        }
    }
    @Test
    @Order(12)
    @DisplayName("Join Game")
    public void joinGame() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        GameData gameData = new GameData(0, null, null, "Name", null);
        LoginResult r = (LoginResult) facade.register(data);
        String authToken = r.authToken();
        GameResult createResult = (GameResult) facade.create(gameData, authToken);
        int gameID = createResult.gameID();
        JoinRequest req = new JoinRequest("WHITE", gameID);
        Object result = facade.join(req, authToken);
        assert(result instanceof Result);
    }
    @Test
    @Order(13)
    @DisplayName("Join Full Game")
    public void joinFullGame() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        GameData gameData = new GameData(0, null, null, "Name", null);
        LoginResult r = (LoginResult) facade.register(data);
        String authToken = r.authToken();
        GameResult createResult = (GameResult) facade.create(gameData, authToken);
        int gameID = createResult.gameID();
        JoinRequest req = new JoinRequest("WHITE", gameID);
        facade.join(req, authToken);
        try{
            facade.join(req, authToken);
            assert(false);
        }
        catch(DataAccessException e){
            assert(true);
        }
    }
    @Test
    @Order(14)
    @DisplayName("Observe Game")
    public void observeGame() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        GameData gameData = new GameData(0, null, null, "Name", null);
        LoginResult r = (LoginResult) facade.register(data);
        String authToken = r.authToken();
        GameResult createResult = (GameResult) facade.create(gameData, authToken);
        int gameID = createResult.gameID();
        JoinRequest req = new JoinRequest(null, gameID);
        Object result = facade.join(req, authToken);
        assert(result instanceof Result);
    }
    @Test
    @Order(15)
    @DisplayName("Observe Invalid")
    public void observeInvalidGame() throws DataAccessException {
        facade.clear();
        UserData data = new UserData("username", "password", "fake@fake.com");
        GameData gameData = new GameData(0, null, null, "Name", null);
        LoginResult r = (LoginResult) facade.register(data);
        String authToken = r.authToken();
        GameResult createResult = (GameResult) facade.create(gameData, authToken);
        JoinRequest req = new JoinRequest(null, 0);
        try{
            facade.join(req, authToken);
            assert(false);
        }
        catch(DataAccessException e){
            assert(true);
        }
    }

}
