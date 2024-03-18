package ui;

import dataAccess.DataAccessException;
import model.UserData;
import service.result.LoginResult;
import service.result.Result;

import java.util.Scanner;

public class ChessClient {
    private final String serverUrl;
    private final ServerFacade facade;
    private String authToken;
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
                help();
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
        System.out.println(result);
        if (result instanceof LoginResult) {
            authToken = ((LoginResult) result).authToken();
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
    private String logout(){
        System.out.println("Logging out");
        return ("logout");
    }
    private String createGame(){
        System.out.println("Creating Game");
        return ("create");
    }
    private String listGames(){
        System.out.println("Listing Games");
        return ("list");
    }
    private String joinGame(){
        System.out.println("Joining Game");
        return ("join");
    }
    private String observeGame(){
        System.out.println("Joining Game as Observer");
        return ("observe");
    }
}
