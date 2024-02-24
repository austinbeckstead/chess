package chess.Moves;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class KingMoves extends PieceMoves {


    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;

    public KingMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        super(board, myPosition, teamColor);
        this.myPosition = myPosition;
        this.board = board;
        this.teamColor = teamColor;
    }

    public Collection<ChessMove> getMoves() {
        Collection<ChessMove> moves = new ArrayList<ChessMove>();

        boolean u = (myPosition.getRow() < 8);
        boolean d = (myPosition.getRow() > 1);
        boolean l = (myPosition.getColumn() > 1);
        boolean r = (myPosition.getColumn() < 8);

        if (u) {
            ChessPosition up = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            chess.ChessPiece piece = board.getPiece(up);
            if (piece == null){
                moves.add(new ChessMove(myPosition, up, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, up, null));
            }
        }
        if (d) {
            ChessPosition down = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
            chess.ChessPiece piece = board.getPiece(down);
            if (piece == null){
                moves.add(new ChessMove(myPosition, down, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, down, null));
            }

        }
        if (l) {
            ChessPosition left = new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1);
            chess.ChessPiece piece = board.getPiece(left);
            if (piece == null){
                moves.add(new ChessMove(myPosition, left, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, left, null));
            }

        }
        if (r) {
            ChessPosition right = new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1);
            chess.ChessPiece piece = board.getPiece(right);
            if (piece == null){
                moves.add(new ChessMove(myPosition, right, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, right, null));
            }
        }
        if (u && r) {
            ChessPosition upRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
            chess.ChessPiece piece = board.getPiece(upRight);
            if (piece == null){
                moves.add(new ChessMove(myPosition, upRight, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, upRight, null));
            }

        }
        if(u && l) {
            ChessPosition upLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
            chess.ChessPiece piece = board.getPiece(upLeft);
            if (piece == null){
                moves.add(new ChessMove(myPosition, upLeft, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, upLeft, null));
            }
        }
        if (d && r) {
            ChessPosition downRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
            chess.ChessPiece piece = board.getPiece(downRight);
            if (piece == null){
                moves.add(new ChessMove(myPosition, downRight, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, downRight, null));
            }
        }
        if (d && l) {
            ChessPosition downLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
            chess.ChessPiece piece = board.getPiece(downLeft);
            if (piece == null){
                moves.add(new ChessMove(myPosition, downLeft, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, downLeft, null));
            }
        }
        return moves;
    }
}