package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.UserData;
import service.GameService;
import service.Result;
import spark.*;

public class Server {
    private GameService gameService;
    private Gson serializer;

    public int run(int desiredPort) {
        Spark.port(desiredPort);
        gameService = new GameService();
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
        Result result = gameService.addUser(user);
        return new Gson().toJson(user);

    }


}
