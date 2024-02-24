package dataAccess;
import model.GameData;
import model.UserData;
import service.Result;

import java.util.HashMap;

public class MemoryUserDAO {
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
}