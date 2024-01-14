package chess.Moves;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import java.util.Collection;

public class PawnMoves extends PieceMoves {
    private Collection<ChessMove> moves;
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;


    public PawnMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor){
        super(board, myPosition, teamColor);
    }

    public Collection<ChessMove> getMoves() {
        if (teamColor == ChessGame.TeamColor.WHITE) {
            ChessPosition forward = new ChessPosition(myPosition.getColumn(), myPosition.getRow() + 1);
            if(myPosition.getRow() < 7) {
                if (board.getPiece(forward) == null) {
                    moves.add(new ChessMove(myPosition, forward, null));
                }

                if (myPosition.getColumn() > 0) {
                    ChessPosition diag_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() + 1);
                    if (board.getPiece(diag_left) == null) {
                        moves.add(new ChessMove(myPosition, diag_left, null));
                    }
                }
                if (myPosition.getColumn() < 7) {
                    ChessPosition diag_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() + 1);
                    if (board.getPiece(diag_right) == null) {
                        moves.add(new ChessMove(myPosition, diag_right, null));
                    }
                }
            }

        }
        return moves;
    }

}
