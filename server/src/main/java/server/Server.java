package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import server.handler.*;
import service.GameService;
import service.ResponseException;
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
        Spark.exception(ResponseException.class, this::exceptionHandler);

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
    private void exceptionHandler(ResponseException ex, Request req, Response res) {
        res.status(ex.StatusCode());
    }
    private Object clearApplication(Request req, Response res) throws DataAccessException {
        gameService.clear();
        res.status(200);
        return "";
    }

}
