//In client, have a Server Facade and Client Communicator class. In Server Facade, have a method for each endpoint.
// Server Facade takes in a req and returns a result, calls Client Communicator to carry out the functions for each endpoint.
// Convert to/from JSON in client communicator. Client communicator actually makes the request over the internet.


package ui;

import java.util.Objects;
import java.util.Scanner;

public class Repl {
    private final ChessClient client;


    public Repl(String serverUrl) {
        this.client = new ChessClient(serverUrl);
    }

    public void run() {
        System.out.println("Welcome to Chess.");
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        var result = "";
        while (!result.equals("quit")) {
            if(loggedIn){
                postLoginMenu();
                String input = scanner.nextLine();
                try{
                    result = client.postEval(input);
                    if(result.equals("logout")){
                        loggedIn = false;
                    }
                }
                catch (Exception e) {
                    System.out.println("Error");
                }
            }
            else{
            loginMenu();
            String input = scanner.nextLine();
            try {
                result = client.eval(input);
                if(result.equals("login")){
                    loggedIn = true;
                }
            } catch (Exception e) {
                System.out.println("Error");
            }
            }
        }
    }

    private void loginMenu() {
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("3: Help");
        System.out.println("4: Quit");
    }
    private void postLoginMenu(){
        System.out.println("1: Help");
        System.out.println("2: Logout");
        System.out.println("3: Create Game");
        System.out.println("4: List Games");
        System.out.println("5: Join Game");
        System.out.println("6: Join Observer");
    }
}

