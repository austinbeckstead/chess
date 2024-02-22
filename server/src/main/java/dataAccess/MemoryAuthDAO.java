package dataAccess;
import model.GameData;
import java.util.HashMap;

public class MemoryAuthDAO {
    private HashMap<String, GameData> data;
    public MemoryAuthDAO(){
        data = new HashMap<String, GameData>();
    }
    public void clear() {
        data.clear();
    }
}
