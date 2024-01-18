package chess.Moves;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public class BishopMoves extends PieceMoves{

    private Collection<ChessMove> moves;
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;

    public BishopMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        super(board, myPosition, teamColor);
    }

    public Collection<ChessMove> getMoves() {
        // Up Right
        for (int i = 1; i < Math.min(7 - myPosition.getRow(), 7 - myPosition.getColumn()); i++) {
            ChessPosition u_r = new ChessPosition(myPosition.getColumn() + i, myPosition.getRow() + i);
            chess.ChessPiece piece = board.getPiece(u_r);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, u_r, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, u_r, null));
                break;
            }
            else{
                break;
            }

        }
        // Up Left
        for (int i = 1; i < Math.min(7 - myPosition.getRow(), myPosition.getColumn()); i++) {
            ChessPosition u_l = new ChessPosition(myPosition.getColumn() - i, myPosition.getRow() + i);
            chess.ChessPiece piece = board.getPiece(u_l);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, u_l, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, u_l, null));
                break;
            }
            else{
                break;
            }
        }
        // Down Left
        for (int i = 1; i < Math.min(myPosition.getRow(), myPosition.getColumn()); i++) {
            ChessPosition d_l = new ChessPosition(myPosition.getColumn() - i, myPosition.getRow() - i);
            chess.ChessPiece piece = board.getPiece(d_l);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, d_l, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, d_l, null));
                break;
            }
            else{
                break;
            }

        }

        // Down Right
        for (int i = 1; i < Math.min(myPosition.getRow(), 7 - myPosition.getColumn()); i++) {
            ChessPosition d_r = new ChessPosition(myPosition.getColumn() + i, myPosition.getRow() - i);
            chess.ChessPiece piece = board.getPiece(d_r);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, d_r, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, d_r, null));
                break;
            }
            else{
                break;
            }
        }

        return moves;
    }

}
