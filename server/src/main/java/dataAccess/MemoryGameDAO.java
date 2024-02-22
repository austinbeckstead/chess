package dataAccess;

import model.GameData;

import java.util.HashMap;

public class MemoryGameDAO{
    private HashMap<String, GameData> data;
    public void clear() {
        data = new HashMap<String, GameData>();
    }
    public GameData getGame(String id) throws DataAccessException{
        return data.get(id);
    }
}
