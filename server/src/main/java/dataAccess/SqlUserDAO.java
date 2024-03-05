package dataAccess;

import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SqlUserDAO implements UserDAO{
    public SqlUserDAO() throws DataAccessException{
        configureDatabase();
    }
    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE users";
        executeUpdate(statement);
    }

    @Override
    public UserData getUser(UserData userData) throws DataAccessException {
        try(var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT username, userData FROM users WHERE username=?";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(1, userData.username());
                try(var rs = ps.executeQuery()){
                    return readUser(rs);
                }
            }
        }
        catch(SQLException e){
            throw new DataAccessException(500, String.format("unable to get USER: %s", e.getMessage()));
        }
    }

    @Override
    public void createUser(UserData userData) throws DataAccessException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(userData.password());
        UserData newData = new UserData(userData.username(), hashedPassword, userData.email());
        var statement = "INSERT INTO users (username, userData) VALUES (?, ?)";
        var userJSON = new Gson().toJson(newData);
        executeUpdate(statement, userData.username(), userJSON);
    }

    @Override
    public boolean isEmpty() throws DataAccessException {
        String query = "SELECT COUNT(*) FROM users";
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
    public boolean verifyPassword(UserData userData) throws DataAccessException {
        var hashedPassword = readHashedPasswordFromDatabase(userData.username());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(userData.password(), hashedPassword);
    }
    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS users (
              `username` varchar(255) NOT NULL,
              `userData` TEXT NOT NULL,
              PRIMARY KEY (`username`)
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
    private UserData readUser(ResultSet rs) throws SQLException {
        if(rs.next()) {
            var userJSON = rs.getString("userData");
            return new Gson().fromJson(userJSON, UserData.class);
        }
        else{
            return null;
        }
    }
    private String readHashedPasswordFromDatabase(String username) throws DataAccessException {
        try(var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT username, userData FROM users WHERE username=?";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(1, username);
                try(var rs = ps.executeQuery()){
                    rs.next();
                    var userJSON = rs.getString("userData");
                    var userData = new Gson().fromJson(userJSON, UserData.class);
                    return userData.password();


                }
            }
        }
        catch(SQLException e){
            throw new DataAccessException(500, String.format("unable to get AUTH: %s", e.getMessage()));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
