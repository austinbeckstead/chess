package dataAccess;

import model.GameData;
import service.request.JoinRequest;

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
    public GameData[] listGames(){
        GameData[] gameList = new GameData[data.size()];
        int i = 0;
        for(HashMap.Entry<String, GameData> entry : data.entrySet()){
            gameList[i] = entry.getValue();
            i += 1;
        }
        return gameList;
    }
    public String joinGame(JoinRequest request, String username){
        GameData game = data.get(String.valueOf(request.gameID()));

        if(game != null){
            if(request.playerColor() == null){
                return null;
            }
            else if(request.playerColor().equals("WHITE")){
                if(game.whiteUsername() == null){
                    data.put(String.valueOf(game.gameID()), new GameData(game.gameID(), username, game.blackUsername(), game.gameName(), game.game()));
                    return null;
                }
                else {
                    return "Error: already taken";
                }
            }
            else{
                if (game.blackUsername() == null) {
                    data.put(String.valueOf(game.gameID()), new GameData(game.gameID(), game.whiteUsername(), username, game.gameName(), game.game()));
                    return null;
                } else {
                    return "Error: already taken";
                }
            }

        }
        return "Error: bad request";
    }
}
