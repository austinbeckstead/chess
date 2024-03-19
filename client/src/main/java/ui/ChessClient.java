package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import dataAccess.DataAccessException;
import model.GameData;
import model.UserData;
import service.request.JoinRequest;
import service.result.GameResult;
import service.result.ListGamesResult;
import service.result.LoginResult;
import service.result.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChessClient {
    private final String serverUrl;
    private final ServerFacade facade;
    private String authToken;
    private GameData[] gameList;
    ChessClient(String serverUrl){
        this.serverUrl = serverUrl;
        facade = new ServerFacade(serverUrl);
    }
    public String eval(String input) throws DataAccessException {
        switch(input){
            case "1":
                return login();
                // here we can return
            case "2":
                return register();
            case "3":
                help();
                return "help";
            case "4":
                quit();
                return "quit";
            default:
                return "";
        }
    }

    public String postEval(String input) throws DataAccessException {
        switch(input){
            case "1":
                return help();
            // here we can return
            case "2":
                return logout();
            case "3":
                return createGame();
            case "4":
                return listGames();
            case "5":
                return joinGame();
            case "6":
                return observeGame();
            default:
                return "";
        }
    }
    private String login() throws DataAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        UserData data = new UserData(username, password, null);
        Object result = facade.login(data);
        if (result instanceof LoginResult) {
            authToken = ((LoginResult) result).authToken();
            setGameList();
            return "login";
        } else {
            return "unauthorized";
        }
    }
    private String register() throws DataAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        UserData data = new UserData(username, password, email);
        Object result = facade.register(data);
        if (result instanceof LoginResult) {
            authToken = ((LoginResult) result).authToken();
            setGameList();
            System.out.println("Registering");
            return ("login");
        }
        else{
            return("error");
        }
    }
    private String help(){
        System.out.println("Get help");
        return ("help");
    }
    private void quit(){
        System.out.println("Goodbye");
    }
    private String logout() throws DataAccessException {
        Object result = facade.logout(authToken);
        return ("logout");
    }
    private String createGame() throws DataAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game Name:");
        String name = scanner.nextLine();
        GameData data = new GameData(0, null, null, name, null);
        Object result = facade.create(data, authToken);
        if (result instanceof GameResult) {
            System.out.println("Created game " + ((GameResult) result).gameID());
            return "create";
        }
        else{
            return("error");
        }
    }
    private String listGames() throws DataAccessException {
        Object result = facade.list(authToken);
        if (result instanceof ListGamesResult) {
            gameList = ((ListGamesResult) result).games();
            int i = 1;
            for(GameData game: gameList){
                System.out.println(i + ":");
                System.out.println("  Name: " + game.gameName());
                System.out.println("  White: " + game.whiteUsername());
                System.out.println("  Black: " + game.blackUsername());
                i++;
            }
            return "list";
        }
        else {
            return ("error");
        }
    }
    private String joinGame() throws DataAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Game Number (1-" + gameList.length + "): ");
        String gameNumber = scanner.nextLine();
        if(gameNumber.matches("\\d+") && Integer.parseInt(gameNumber) <= gameList.length && Integer.parseInt(gameNumber) > 0){
            int i = Integer.parseInt(gameNumber) - 1;
            String color;
            if(gameList[i].whiteUsername() == null && gameList[i].blackUsername() == null){
                System.out.println("Enter 1 for White and anything else for Black");
                String input = scanner.nextLine();
                if(input.equals("1")) color = "WHITE";
                else color = "BLACK";
            }
            else if(gameList[i].whiteUsername() == null){
                color = "WHITE";
            }
            else if(gameList[i].blackUsername() == null){
                color = "BLACK";
            }
            else{
                System.out.println("Game is full");
                return "error";
            }
            facade.join(new JoinRequest(color, gameList[i].gameID()), authToken);
            System.out.println("Joined " + gameList[i].gameName() + " as " + color);
            //ChessBoardUI.drawBoard(gameList[i].game().getBoard().getPieces());
            ChessBoard board = new ChessBoard();
            board.resetBoard();
            ChessPiece[][] pieces = board.getPieces();
            ChessBoardUI.drawBoard(pieces);
        }
        else{
            System.out.println("Invalid Game");
            return("Error");
        }
        return ("join");
    }
    private String observeGame() throws DataAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Game Number (1-" + gameList.length + "): ");
        String gameNumber = scanner.nextLine();
        if(gameNumber.matches("\\d+") && Integer.parseInt(gameNumber) <= gameList.length && Integer.parseInt(gameNumber) > 0) {
            int i = Integer.parseInt(gameNumber) - 1;
            facade.join(new JoinRequest(null, gameList[i].gameID()), authToken);
            System.out.println("Joined " + gameList[i].gameName() + " as " + "observer");
            ChessBoard board = new ChessBoard();
            board.resetBoard();
            ChessPiece[][] pieces = board.getPieces();
            ChessBoardUI.drawBoard(pieces);
        }
        return ("observe");
    }
    private void setGameList() throws DataAccessException {
        Object result = facade.list(authToken);
        if (result instanceof ListGamesResult) {
            gameList = ((ListGamesResult) result).games();
        }
    }
}
