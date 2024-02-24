package chess.Moves;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMoves extends PieceMoves{
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;

    public BishopMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        super(board, myPosition, teamColor);
        this.myPosition = myPosition;
        this.board = board;
        this.teamColor = teamColor;
    }

    public Collection<ChessMove> getMoves() {
        Collection<ChessMove> moves = new ArrayList<ChessMove>();
        // Up Right
        for (int i = 1; i < Math.min(9 - myPosition.getRow(), 9 - myPosition.getColumn()); i++) {
            ChessPosition move = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            if (diagonal(moves, move)) break;

        }
        // Up Left
        for (int i = 1; i < Math.min(9 - myPosition.getRow(), myPosition.getColumn()); i++) {
            ChessPosition move = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
            if (diagonal(moves, move)) break;
        }
        // Down Left
        for (int i = 1; i < Math.min(myPosition.getRow(), myPosition.getColumn()); i++) {
            ChessPosition move = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
            if (diagonal(moves, move)) break;

        }

        // Down Right
        for (int i = 1; i < Math.min(myPosition.getRow(), 9 - myPosition.getColumn()); i++) {
            ChessPosition move = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
            if (diagonal(moves, move)) break;
        }

        return moves;
    }

    private boolean diagonal(Collection<ChessMove> moves, ChessPosition move) {
        ChessPiece piece = board.getPiece(move);
        if (piece == null) {
            moves.add(new ChessMove(myPosition, move, null));
        }
        else if (piece.getTeamColor() != teamColor){
            moves.add(new ChessMove(myPosition, move, null));
            return true;
        }
        else{
            return true;
        }
        return false;
    }
}
