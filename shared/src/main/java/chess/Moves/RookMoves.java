package chess.Moves;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public class RookMoves extends PieceMoves{
    private Collection<ChessMove> moves;
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;

    public RookMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        super(board, myPosition, teamColor);
    }

    public Collection<ChessMove> getMoves() {
        for(int i = 1; i <= 8 - myPosition.getRow(); i++){
            ChessPosition up = new ChessPosition(myPosition.getColumn(), myPosition.getRow() + i);
            chess.ChessPiece piece = board.getPiece(up);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, up, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, up, null));
                break;
            }
            else{
                break;
            }
        }
        for(int i = 1; i < myPosition.getRow(); i++){
            ChessPosition down = new ChessPosition(myPosition.getColumn(), myPosition.getRow() - i);
            chess.ChessPiece piece = board.getPiece(down);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, down, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, down, null));
                break;
            }
            else{
                break;
            }
        }
        for(int i = 1; i <= 8 - myPosition.getColumn(); i++){
            ChessPosition right = new ChessPosition(myPosition.getColumn() + i, myPosition.getRow());
            chess.ChessPiece piece = board.getPiece(right);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, right, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, right, null));
                break;
            }
            else{
                break;
            }
        }
        for(int i = 1; i < myPosition.getColumn(); i++){
            ChessPosition left = new ChessPosition(myPosition.getColumn() - i, myPosition.getRow());
            chess.ChessPiece piece = board.getPiece(left);
            if (piece == null) {
                moves.add(new ChessMove(myPosition, left, null));
            }
            else if (piece.getTeamColor() != teamColor){
                moves.add(new ChessMove(myPosition, left, null));
                break;
            }
            else{
                break;
            }
        }
        return moves;
    }

}

