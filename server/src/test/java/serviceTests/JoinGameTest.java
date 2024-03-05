package serviceTests;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import passoffTests.testClasses.TestModels;
import server.Server;
import service.GameService;
import service.UserService;
import service.request.JoinRequest;

public class JoinGameTest {
    private static Server server;
    private final GameDAO gameDAO = new SqlGameDAO();
    private final UserDAO userDAO = new SqlUserDAO();

    private final AuthDAO authDAO = new SqlAuthDAO();
    private final GameService gameService = new GameService(authDAO, userDAO, gameDAO);;
    private final UserService userService = new UserService(authDAO, userDAO, gameDAO);;

    private static TestModels.TestCreateRequest createRequest;

    public JoinGameTest() throws DataAccessException {
    }

    @Test
    @Order(0)
    @DisplayName("Join game")
    public void joinGame() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData result = gameService.createGame(token.authToken(), "father");
        String message = gameService.joinGame(token.authToken(), new JoinRequest("WHITE", result.gameID()));
        assert (message == null);
    }
    @Test
    @Order(1)
    @DisplayName("Join Full Game")
    public void joinFullGame() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData result = gameService.createGame(token.authToken(), "father");
        gameService.joinGame(token.authToken(), new JoinRequest("WHITE", result.gameID()));
        String message = gameService.joinGame(token.authToken(), new JoinRequest("WHITE", result.gameID()));
        assert (message.equals("Error: already taken"));
    }
    @Test
    @Order(2)
    @DisplayName("Join Game no Auth")
    public void joinNoAuth() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData result = gameService.createGame(token.authToken(), "father");
        String message = gameService.joinGame(null, new JoinRequest("WHITE", result.gameID()));
        assert (message.equals("Error: unauthorized"));
    }
    @Test
    @Order(3)
    @DisplayName("Join Nonexistent Game")
    public void joinNonexistentGame() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData result = gameService.createGame(token.authToken(), "father");
        String message = gameService.joinGame(token.authToken(), new JoinRequest("WHITE", 99));
        assert (message.equals("Error: bad request"));
    }
    @Test
    @Order(4)
    @DisplayName("Spectate game")
    public void spectateGame() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData result = gameService.createGame(token.authToken(), "father");
        String message = gameService.joinGame(token.authToken(), new JoinRequest(null, result.gameID()));
        assert (message == null);
    }

}


