package dataAccess;
import model.GameData;
import java.util.HashMap;

public class MemoryUserDAO {
    private HashMap<String, GameData> data;
    public MemoryUserDAO(){
        data = new HashMap<String, GameData>();
    }
    public void clear() {
        data.clear();
    }
}