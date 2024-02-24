package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.AuthData;
import model.UserData;
import service.GameService;
import service.UserService;
import service.Result;
import spark.*;

public class Server {
    private GameService gameService;
    private UserService userService;
    private Gson serializer;

    public int run(int desiredPort) {
        Spark.port(desiredPort);
        gameService = new GameService();
        userService = new UserService();

        serializer = new Gson();

        Spark.staticFiles.location("web");
        // Register your endpoints and handle exceptions here.
        Spark.delete("/db", this::clearApplication);
        Spark.post("/user", this::registerUser);
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
        String message = "";
        return serializer.toJson(message);
    }
    private Object registerUser(Request req, Response res) throws DataAccessException {
        var user = serializer.fromJson(req.body(), UserData.class);
        AuthData authToken = userService.addUser(user);
        if(authToken != null) {
            res.status(200);
            return serializer.toJson(authToken);
        }
        else{
            res.status(403);
            Result result = new Result(null, "Error: already taken");
            return serializer.toJson(result);
        }

    }


}
