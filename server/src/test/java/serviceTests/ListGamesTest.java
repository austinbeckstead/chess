package serviceTests;

import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
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

public class ListGamesTest {

        private static Server server;
        private final MemoryGameDAO gameDAO = new MemoryGameDAO();
        private final MemoryUserDAO userDAO = new MemoryUserDAO();

        private final MemoryAuthDAO authDAO = new MemoryAuthDAO();
        private final GameService gameService = new GameService(authDAO, userDAO, gameDAO);;
        private final UserService userService = new UserService(authDAO, userDAO, gameDAO);;

        private static TestModels.TestCreateRequest createRequest;
        @Test
        @Order(0)
        @DisplayName("List Games No Auth")
        public void listGamesNoAuth() throws DataAccessException {
            GameData[] result = gameService.listGames("1");
            assert(result == null);
        }
        @Test
        @Order(1)
        @DisplayName("List No Games")
        public void listNothing() throws DataAccessException {
            gameService.clear();
            String username = "username";
            userService.addUser(new UserData(username, "password", "email"));
            AuthData token = userService.loginUser(new UserData(username, "password", null));
            GameData[] result = gameService.listGames(token.authToken());
            assert(result.length == 0);
        }
    @Test
    @Order(2)
    @DisplayName("List Games")
    public void listGames() throws DataAccessException {
        gameService.clear();
        String username = "username";
        userService.addUser(new UserData(username, "password", "email"));
        AuthData token = userService.loginUser(new UserData(username, "password", null));
        GameData gameData = gameService.createGame(token.authToken(), "game");
        GameData[] result = gameService.listGames(token.authToken());
        assert(result[0] == gameData);
    }

    }




