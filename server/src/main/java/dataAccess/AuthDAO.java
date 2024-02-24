package dataAccess;

import model.AuthData;

import java.util.HashMap;
import java.util.UUID;

public interface AuthDAO {

    public void clear();
    public AuthData createAuth(String username);
    public boolean isEmpty();
    public AuthData getAuth(String authToken);
    public void removeAuth(String authToken);
}
