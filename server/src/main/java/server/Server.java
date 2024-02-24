package server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dataAccess.*;
import model.*;
import service.*;
import service.result.*;
import spark.*;

public class Server {

    private final MemoryGameDAO gameDAO = new MemoryGameDAO();
    private final MemoryUserDAO userDAO = new MemoryUserDAO();

    private final MemoryAuthDAO authDAO = new MemoryAuthDAO();
    private final GameService gameService = new GameService(authDAO, userDAO, gameDAO);;
    private final UserService userService = new UserService(authDAO, userDAO, gameDAO);;


    private Gson serializer;

    public int run(int desiredPort) {
        Spark.port(desiredPort);
        serializer = new Gson();

        Spark.staticFiles.location("web");
        // Register your endpoints and handle exceptions here.
        Spark.delete("/db", this::clearApplication);
        Spark.post("/user", this::registerUser);
        Spark.post("/session", this::loginUser);
        Spark.delete("/session", this::logoutUser);
        Spark.post("/game", this::createGame);



        Spark.exception(DataAccessException.class, this::exceptionHandler);

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
    private void exceptionHandler(DataAccessException ex, Request req, Response res) {
        res.status(ex.StatusCode());
    }
    private Object clearApplication(Request req, Response res) throws DataAccessException {
        gameService.clear();
        res.status(200);
        Result result = new Result(null, null);
        return serializer.toJson(result);
    }
    private Object registerUser(Request req, Response res){
        try {
            UserData user = serializer.fromJson(req.body(), UserData.class);
            if(user.username() == null || user.password() == null || user.email() == null){
                res.status(400);

                Result result = new Result(null, "Error: bad request");
                return serializer.toJson(result);
            }
            AuthData authToken = userService.addUser(user);
            if (authToken != null) {
                res.status(200);
                LoginResult result = new LoginResult(authToken.username(), authToken.authToken());
                return serializer.toJson(result);
            } else {
                res.status(403);
                Result result = new Result(null, "Error: already taken");
                return serializer.toJson(result);
            }
        }
        catch(Exception e){
            res.status(400);
            Result result = new Result(null, "Error: bad request");
            return serializer.toJson(result);
        }


    }
    private Object loginUser(Request req, Response res){
        var user = serializer.fromJson(req.body(), UserData.class);
        try {
            AuthData authToken = userService.loginUser(user);
            if (authToken != null) {
                res.status(200);
                LoginResult result = new LoginResult(authToken.username(), authToken.authToken());
                return serializer.toJson(result);
            } else {
                res.status(401);
                Result result = new Result(null, "Error: unauthorized");
                return serializer.toJson(result);
            }
        }
        catch(DataAccessException e){
            res.status(500);
            Result result = new Result(null, e.getMessage());
            return serializer.toJson(result);
        }

    }
    private Object logoutUser(Request req, Response res){
        try {
            var auth = req.headers("authorization");
            boolean success = userService.logoutUser(auth);
            if(success){
                res.status(200);
                Result result = new Result(null, null);
                return serializer.toJson(result);
            }
            else{
                res.status(401);
                Result result = new Result(null, "Error: unauthorized");
                return serializer.toJson(result);
            }
        }
        catch(Exception e){
            res.status(500);
            Result result = new Result(null, e.getMessage());
            return serializer.toJson(result);
        }

    }
    private Object createGame(Request req, Response res){
        var auth = req.headers("authorization");
        GameData game = serializer.fromJson(req.body(), GameData.class);
        GameData gameData = gameService.createGame(auth, game.gameName());
        if(gameData != null){
            res.status(200);
            GameResult result = new GameResult(gameData.gameID());
            return serializer.toJson(result);
        }
        else{
            res.status(401);
            Result result = new Result(null, "Error: unauthorized");
            return serializer.toJson(result);
        }
    }
}
