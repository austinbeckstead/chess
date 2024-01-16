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
        for (int i = 0; i < Math.min(7 - myPosition.getRow(), 7 - myPosition.getColumn()); i++) {

        }
        // Up Left
        for (int i = 0; i < Math.min(7 - myPosition.getRow(), myPosition.getColumn()); i++) {

        }
        // Down Left
        for (int i = 0; i < Math.min(myPosition.getRow(), myPosition.getColumn()); i++) {

        }

        // Down Right
        for (int i = 0; i < Math.min(myPosition.getRow(), 7 - myPosition.getColumn()); i++) {

        }

        return moves;
    }

}
