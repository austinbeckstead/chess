package dataAccess;

import com.google.gson.Gson;
import model.GameData;
import model.UserData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.request.JoinRequest;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SqlGameDAO implements GameDAO {
    public SqlGameDAO() throws DataAccessException {
        configureDatabase();
    }

    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE games";
        executeUpdate(statement);
    }

    @Override
    public boolean isEmpty() throws DataAccessException {
        String query = "SELECT COUNT(*) FROM games";
        try (var conn = DatabaseManager.getConnection()) {
            try (var statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int rowCount = resultSet.getInt(1);
                    return rowCount == 0;
                }
            }
            // In case of an error or if the table does not exist, return false
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameData createGame(String gameName) throws DataAccessException {
        int gameID = new Random().nextInt(9000) + 1000;
        GameData gameData = new GameData(gameID, null, null, gameName, null);
        var statement = "INSERT INTO games (gameID, gameData) VALUES (?, ?)";
        var gameJSON = new Gson().toJson(gameData);
        executeUpdate(statement, String.valueOf(gameID), gameJSON);
        return gameData;
    }

    @Override
    public GameData[] listGames() throws DataAccessException {
        List<GameData> gameList = new ArrayList<>();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT gameData FROM games";
            try (var ps = conn.prepareStatement(statement)) {
                try (var rs = ps.executeQuery()) {
                    while (rs.next()) {
                        GameData gameData = readGame(rs);
                        if (gameData != null) {
                            gameList.add(gameData);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(500, String.format("Unable to list games: %s", e.getMessage()));
        }

        return gameList.toArray(new GameData[0]);
    }

    @Override
    public String joinGame(JoinRequest request, String username) throws DataAccessException {
        String id = String.valueOf(request.gameID());
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT gameData FROM games WHERE gameID=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setString(1, id);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        GameData gameData = readGame(rs);
                        if (gameData == null) {
                            return "Error: bad request";
                        }
                        if (request.playerColor() == null) {
                            return null;
                        } else if (request.playerColor().equals("WHITE")) {
                            if (gameData.whiteUsername() == null) {
                                GameData newData = new GameData(
                                        gameData.gameID(),
                                        username,
                                        gameData.blackUsername(),
                                        gameData.gameName(),
                                        gameData.game()
                                );
                                // Update the gameData in the database
                                var updateStatement = "UPDATE games SET gameData=? WHERE gameID=?";
                                executeUpdate(updateStatement, new Gson().toJson(newData), id);
                                return null;
                            } else {
                                return "Error: already taken";
                            }
                        } else {
                            if (gameData.blackUsername() == null) {
                                GameData newData = new GameData(
                                        gameData.gameID(),
                                        gameData.whiteUsername(),
                                        username,
                                        gameData.gameName(),
                                        gameData.game()
                                );
                                // Update the gameData in the database
                                var updateStatement = "UPDATE games SET gameData=? WHERE gameID=?";
                                executeUpdate(updateStatement, new Gson().toJson(newData), id);
                                return null;
                            } else {
                                return "Error: already taken";
                            }
                        }
                    } else {
                        return "Error: bad request";
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(500, String.format("Unable to join the game: %s", e.getMessage()));
        }
    }


    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS games (
              `gameID` varchar(255) NOT NULL,
              `gameData` TEXT NOT NULL,
              PRIMARY KEY (`gameID`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };

    private void configureDatabase() throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : createStatements) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(500, String.format("Unable to configure database: %s", ex.getMessage()));
        }
    }

    private void executeUpdate(String statement, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var ps = conn.prepareStatement(statement)) {
                for (var i = 0; i < params.length; i++) {
                    var param = params[i];
                    ps.setString(i + 1, (String) param);
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(500, String.format("unable to update database: %s, %s", statement, e.getMessage()));
        }
    }

    private GameData readGame(ResultSet rs) throws SQLException {
        var gameJSON = rs.getString("gameData");
        return new Gson().fromJson(gameJSON, GameData.class);
    }
}
