package dataAccess;

import com.google.gson.Gson;
import model.AuthData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SqlAuthDAO implements AuthDAO {
    public SqlAuthDAO() throws DataAccessException {
        configureDatabase();
    }

    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE auth";
        executeUpdate(statement);
    }

    @Override
    public AuthData createAuth(String username) throws DataAccessException {
        var statement = "INSERT INTO auth (authToken, name) VALUES (?, ?)";
        //var deleteStatement = "DELETE FROM auth WHERE name=?";
        AuthData authToken = new AuthData(username, UUID.randomUUID().toString());
        //executeUpdate(deleteStatement, authToken.username());
        executeUpdate(statement, authToken.authToken(), authToken.username());
        return authToken;
    }

    @Override
    public boolean isEmpty() {
        String query = "SELECT COUNT(*) FROM auth";
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
    public AuthData getAuth(String authToken) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT authToken, name FROM auth WHERE authToken=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setString(1, authToken);
                try (var rs = ps.executeQuery()) {
                    return readAuth(rs);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(500, String.format("unable to get AUTH: %s", e.getMessage()));
        }
    }

    @Override
    public void removeAuth(String authToken) throws DataAccessException {
        var statement = "DELETE FROM auth WHERE authToken=?";
        executeUpdate(statement, authToken);
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  auth (
              `authToken` varchar(255) NOT NULL,
              `name` varchar(255) NOT NULL,
              PRIMARY KEY (`authToken`)
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

    private AuthData readAuth(ResultSet rs) throws SQLException {
        if (rs.next()) {
            var authToken = rs.getString("authToken");
            var username = rs.getString("name");
            return new AuthData(username, authToken);
        } else {
            return null;
        }
    }
}
