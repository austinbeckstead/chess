package dataAccess;
import model.UserData;

import java.util.HashMap;

public class MemoryUserDAO implements UserDAO{
    //Map takes username
    private HashMap<String, UserData> data;
    public MemoryUserDAO(){
        data = new HashMap<String, UserData>();
    }
    public void clear() {
        data.clear();
    }
    public UserData getUser(UserData userData) {
        return data.get(userData.username());
    }
    public void createUser(UserData userData) throws DataAccessException{
            data.put(userData.username(), userData);
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    public boolean verifyPassword(UserData userData){
        return (data.get(userData.username()).password().equals(userData.password()));
    }
}