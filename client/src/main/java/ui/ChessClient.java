package ui;

public class ChessClient {
    ChessClient(){

    }
    public String eval(String input){
        switch(input){
            case "1":
                login();
                return "login";
            case "2":
                register();
                return "register";
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
    private void login(){
        System.out.println("Logging in");
    }
    private void register(){
        System.out.println("Registering");
    }
    private void help(){
        System.out.println("Get help");
    }
    private void quit(){
        System.out.println("Goodbye");
    }
}
