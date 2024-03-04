package dataAccess;

import model.AuthData;

public class SqlAuthDAO implements AuthDAO{

    @Override
    public void clear() {

    }

    @Override
    public AuthData createAuth(String username) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public AuthData getAuth(String authToken) {
        return null;
    }

    @Override
    public void removeAuth(String authToken) {

    }
}
