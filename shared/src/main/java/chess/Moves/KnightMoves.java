package chess.Moves;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;


public class KnightMoves extends PieceMoves {
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;

    public KnightMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        super(board, myPosition, teamColor);
        this.myPosition = myPosition;
        this.board = board;
        this.teamColor = teamColor;
    }

    public Collection<ChessMove> getMoves() {
        Collection<ChessMove> moves = new ArrayList<ChessMove>();

        boolean u = myPosition.getRow() < 8;
        boolean uu = (myPosition.getRow() < 7);
        boolean d = (myPosition.getRow() > 1);
        boolean dd = (myPosition.getRow() > 2);
        boolean l = (myPosition.getColumn() > 1);
        boolean ll = (myPosition.getColumn() > 2);
        boolean r = (myPosition.getColumn() < 8);
        boolean rr = (myPosition.getColumn() < 7);

        if (u && rr) {
            ChessPosition urr = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2);
            chess.ChessPiece piece = board.getPiece(urr);
            if (piece == null){
                moves.add(new ChessMove(myPosition, urr, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, urr, null));
            }
        }
        if (uu && r) {
            ChessPosition uur = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1);
            chess.ChessPiece piece = board.getPiece(uur);
            if (piece == null){
                moves.add(new ChessMove(myPosition, uur, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, uur, null));
            }
        }
        if (uu && l) {
            ChessPosition uul = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1);
            chess.ChessPiece piece = board.getPiece(uul);
            if (piece == null){
                moves.add(new ChessMove(myPosition, uul, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, uul, null));
            }
        }
        if (u && ll) {
            ChessPosition ull = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2);
            chess.ChessPiece piece = board.getPiece(ull);
            if (piece == null){
                moves.add(new ChessMove(myPosition, ull, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, ull, null));
            }
        }
        if (d && rr) {
            ChessPosition drr = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2);
            chess.ChessPiece piece = board.getPiece(drr);
            if (piece == null){
                moves.add(new ChessMove(myPosition, drr, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, drr, null));
            }
        }
        if (dd && r) {
            ChessPosition ddr = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn()  + 1);
            chess.ChessPiece piece = board.getPiece(ddr);
            if (piece == null){
                moves.add(new ChessMove(myPosition, ddr, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, ddr, null));
            }
        }
        if (d && ll) {
            ChessPosition dll = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2);
            chess.ChessPiece piece = board.getPiece(dll);
            if (piece == null){
                moves.add(new ChessMove(myPosition, dll, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, dll, null));
            }
        }
        if (dd && l) {
            ChessPosition ddl = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1);
            chess.ChessPiece piece = board.getPiece(ddl);
            if (piece == null){
                moves.add(new ChessMove(myPosition, ddl, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, ddl, null));
            }
        }

        return moves;
    }
}
