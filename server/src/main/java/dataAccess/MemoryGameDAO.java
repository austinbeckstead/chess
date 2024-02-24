package dataAccess;

import model.GameData;

import java.util.HashMap;

public class MemoryGameDAO{
    public HashMap<String, GameData> data;

    public MemoryGameDAO() {
        data = new HashMap<String, GameData>();
    }
    public void clear(){
        data.clear();
    }
    public GameData getGame(String id){
        return data.get(id);
    }
}
