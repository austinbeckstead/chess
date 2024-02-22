package dataAccess;
import model.GameData;
import java.util.HashMap;

public class MemoryAuthDAO {
    private HashMap<String, GameData> data;
    public void clear() {
        data = new HashMap<String, GameData>();
    }
}
