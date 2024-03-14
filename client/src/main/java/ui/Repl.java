package ui;

import java.util.Objects;
import java.util.Scanner;

public class Repl {
    private final ChessClient client;

    public Repl() {
        this.client = new ChessClient();
    }

    public void run() {
        System.out.println("Welcome to Chess.");
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        var result = "";
        while (!result.equals("quit")) {
            loginMenu();
            String input = scanner.nextLine();
            try {
                result = client.eval(input);
            } catch (Exception e) {
                System.out.println("Error");
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
        System.out.println("2: Join Observer");
    }
}

