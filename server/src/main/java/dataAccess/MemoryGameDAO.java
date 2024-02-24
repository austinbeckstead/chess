package dataAccess;

import model.GameData;

import java.util.HashMap;
import java.util.Random;

public class MemoryGameDAO{
    private HashMap<String, GameData> data;
    private Random random;

    public MemoryGameDAO() {
        data = new HashMap<String, GameData>();
        random = new Random();

    }
    public void clear(){
        data.clear();
    }
    public GameData getGame(String id){
        return data.get(id);
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    public GameData createGame(String gameName){
        int gameID = random.nextInt(9000) + 1000;
        GameData gameData = new GameData(gameID, null, null, gameName, null);
        data.put(String.valueOf(gameID), gameData);
        return gameData;
    }
}
