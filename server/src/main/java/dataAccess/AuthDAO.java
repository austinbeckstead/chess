package dataAccess;

import model.AuthData;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.UUID;

public interface AuthDAO {

    public void clear() throws DataAccessException;
    public AuthData createAuth(String username) throws DataAccessException;
    public boolean isEmpty();
    public AuthData getAuth(String authToken) throws DataAccessException;
    public void removeAuth(String authToken) throws DataAccessException;
}
