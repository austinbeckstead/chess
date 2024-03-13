package ui;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static ui.EscapeSequences.*;
import java.util.Arrays;

public class ChessBoard {
    private static final String[] COLUMNS = {"a", "b", "c", "d", "e", "f", "g", "h"};
    private static final String[] ROWS = {"1", "2", "3", "4", "5", "6", "7", "8"};
    private static final int SQUARESIZE = 1;

    private static final int prefix = SQUARESIZE/2 + 1;
    private static final int suffix = SQUARESIZE - prefix;
    private static final String BOARDCOLOR = SET_BG_COLOR_LIGHT_GREY;
    public static final String BACKGROUND = RESET_BG_COLOR;
    private static final String TEXT = SET_TEXT_COLOR_DARK_GREY;

    private static final String WHITEPIECE = SET_TEXT_COLOR_BLUE;
    private static final String BLACKPIECE = SET_TEXT_COLOR_RED;
    public static void drawBoard(char[][] pieces){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        out.print(BACKGROUND);
        printWhite(out, pieces);
        printBlack(out, pieces);

    }
    private static void printWhite(PrintStream out, char[][] pieces){
        printHeader(out, COLUMNS);
    }

    private static void printBlack(PrintStream out, char[][] pieces){
        printHeader(out, reverseArray(COLUMNS));
    }
    private static void printHeader(PrintStream out, String[] header){
        for(int i = 1; i <= SQUARESIZE; i++){
            for (String s : header) {
                printSquare(out, " ", BOARDCOLOR, TEXT);
                if(i == prefix) {
                    printSquare(out, s, BOARDCOLOR, TEXT);
                }
                else{
                    printSquare(out, " ", BOARDCOLOR, TEXT);
                }
                printSquare(out, " ", BOARDCOLOR, TEXT);

            }
            printNewLine(out);

        }

        printNewLine(out);
    }

    private static void printSquare(PrintStream out, String value, String backgroundColor, String textColor){
        out.print(backgroundColor);
        out.print(EMPTY.repeat(prefix));
        out.print(textColor);
        out.print(value);
        out.print(backgroundColor);
        out.print(EMPTY.repeat(suffix));
    }
    private static String[] reverseArray(String[] array){
        int length = array.length;
        String[] newArray = new String[length];
        for(int i = length; i > 0; i--){
            newArray[length - i] = array[i - 1];
        }
        return newArray;
    }

    private static void printNewLine(PrintStream out){
        out.print(BACKGROUND);
        out.println();
    }

    public static void main(String[] args) {
        drawBoard(new char[8][8]);
    }



}
