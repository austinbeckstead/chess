package dataAccess;
import model.AuthData;
import model.GameData;
import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDAO {
    private HashMap<String, AuthData> data;
    public MemoryAuthDAO(){
        data = new HashMap<String, AuthData>();
    }
    public void clear() {
        data.clear();
    }
    public AuthData createAuth(String username){
        AuthData authToken = new AuthData(username, UUID.randomUUID().toString());
        data.put(username, authToken);
        return authToken;
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
}
