package ui;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessBoard;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static ui.EscapeSequences.*;

public class ChessBoardUI {
    private static final String[] COLUMNS = {"a", "b", "c", "d", "e", "f", "g", "h"};
    private static final String[] ROWS = {"1", "2", "3", "4", "5", "6", "7", "8"};
    private static final int SQUARESIZE = 3;

    private static final int prefix = SQUARESIZE/2 ;
    private static final int suffix = SQUARESIZE - prefix - 1;
    private static final String BOARDCOLOR = SET_BG_COLOR_LIGHT_GREY;
    public static final String BACKGROUND = RESET_BG_COLOR;
    private static final String TEXT = SET_TEXT_COLOR_DARK_GREY;
    private static final String RESET = "\u001B[0m";

    private static final String WHITEPIECE = SET_TEXT_COLOR_RED;

    private static final String WHITEBG = SET_BG_COLOR_WHITE;
    private static final String BLACKBG = SET_BG_COLOR_BLACK;
    private static final String BLACKPIECE = SET_TEXT_COLOR_BLUE;
    public static void drawBoard(ChessPiece[][] pieces){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        out.print(BACKGROUND);
        printWhite(out, pieces);
        printNewLine(out);
        printBlack(out, pieces);
        out.print(ERASE_SCREEN);
        out.print(RESET);
    }
    private static void printBlack(PrintStream out, ChessPiece[][] pieces){
        printHeader(out, COLUMNS);
        printRows(out, pieces, reverseArray(ROWS));
        printHeader(out, COLUMNS);

    }

    private static void printWhite(PrintStream out, ChessPiece[][] pieces){
        printHeader(out, reverseArray(COLUMNS));
        printRows(out, reversePieces(pieces), ROWS);
        printHeader(out, reverseArray(COLUMNS));
    }
    private static void printHeader(PrintStream out, String[] header){
            printSquare(out, " ", BOARDCOLOR, TEXT);
            for (String s : header) {
                printSquare(out, s, BOARDCOLOR, TEXT);
            }
            printSquare(out, " ", BOARDCOLOR, TEXT);
            printNewLine(out);

    }
    private static void printRows(PrintStream out, ChessPiece[][] pieces, String[] header) {
        String color;
        String pieceColor;
        for (int i = 0; i < pieces.length; i++) {
            printSquare(out, header[i], BOARDCOLOR, TEXT);
            for (int j = 0; j < pieces[0].length; j++) {
                if((j + i)%2 == 0) color = WHITEBG;
                else color = BLACKBG;
                printSquare(out, pieceString(pieces[i][j]), color, colorString(pieces[i][j]));

            }
            printSquare(out, header[i], BOARDCOLOR, TEXT);
            printNewLine(out);
        }
    }

    private static void printSquare(PrintStream out, String value, String backgroundColor, String textColor){
        out.print(backgroundColor);
        out.print(" ".repeat(prefix));
        out.print(textColor);
        out.print(value);
        out.print(backgroundColor);
        out.print(" ".repeat(suffix));
    }
    private static String[] reverseArray(String[] array){
        int length = array.length;
        String[] newArray = new String[length];
        for(int i = length; i > 0; i--){
            newArray[length - i] = array[i - 1];
        }
        return newArray;
    }
    private static ChessPiece[][] reversePieces(ChessPiece[][] array){
        ChessPiece[][] newArray = new ChessPiece[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                newArray[7- i][7 - j] = array[i][j];
            }
        }
        return newArray;
    }

    private static void printNewLine(PrintStream out){
        out.print(BACKGROUND);
        out.println();
    }
    private static String pieceString(ChessPiece piece){
        if(piece == null){
            return " ";
        }
        switch(piece.getPieceType()){
            case PAWN:
                return "P";
            case ROOK:
                return "R";
            case KNIGHT:
                return "N";
            case BISHOP:
                return "B";
            case KING:
                return "K";
            case QUEEN:
                return "Q";
            default:
                return " ";
        }

    }
    private static String colorString(ChessPiece piece){
        if(piece == null){
            return TEXT;
        }
        else if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
            return WHITEPIECE;
        }
        else{
            return BLACKPIECE;
        }
    }

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.resetBoard();
        ChessPiece[][] pieces = board.getPieces();
        drawBoard(pieces);
    }



}
