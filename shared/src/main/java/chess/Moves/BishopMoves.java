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
            ChessPosition u_r = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            if (diagonal(moves, u_r)) break;

        }
        // Up Left
        for (int i = 1; i < Math.min(9 - myPosition.getRow(), myPosition.getColumn()); i++) {
            ChessPosition u_l = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
            if (diagonal(moves, u_l)) break;
        }
        // Down Left
        for (int i = 1; i < Math.min(myPosition.getRow(), myPosition.getColumn()); i++) {
            ChessPosition d_l = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
            if (diagonal(moves, d_l)) break;

        }

        // Down Right
        for (int i = 1; i < Math.min(myPosition.getRow(), 9 - myPosition.getColumn()); i++) {
            ChessPosition d_r = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
            if (diagonal(moves, d_r)) break;
        }

        return moves;
    }

    private boolean diagonal(Collection<ChessMove> moves, ChessPosition u_r) {
        ChessPiece piece = board.getPiece(u_r);
        if (piece == null) {
            moves.add(new ChessMove(myPosition, u_r, null));
        }
        else if (piece.getTeamColor() != teamColor){
            moves.add(new ChessMove(myPosition, u_r, null));
            return true;
        }
        else{
            return true;
        }
        return false;
    }

}
