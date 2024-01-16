package chess.Moves;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import java.util.Collection;

public class KingMoves extends PieceMoves {


    private Collection<ChessMove> moves;
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;

    public KingMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        super(board, myPosition, teamColor);
    }

    public Collection<ChessMove> getMoves() {
        boolean u = false;
        boolean d = false;
        boolean l = false;
        boolean r = false;
        if (myPosition.getColumn() > 0) {l = true;}
        if (myPosition.getColumn() < 7) {r = true;}
        if (myPosition.getRow() < 7) {u = true;}
        if (myPosition.getRow() > 0) {d = true;}

        if (u) {
            ChessPosition up = new ChessPosition(myPosition.getColumn(), myPosition.getRow() + 1);
            if (board.getPiece(up).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, up, null));
            }
        }
        if (d) {
            ChessPosition down = new ChessPosition(myPosition.getColumn(), myPosition.getRow() - 1);
            if (board.getPiece(down).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, down, null));
            }

        }
        if (l) {
            ChessPosition left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() + 1);
            if (board.getPiece(left).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, left, null));
            }

        }
        if (r) {
            ChessPosition right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() + 1);
            if (board.getPiece(right).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, right, null));
            }
        }
        if (u && r) {
            ChessPosition up_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() + 1);
            if (board.getPiece(up_right).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, up_right, null));
            }

        }
        if(u && l) {
            ChessPosition up_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() + 1);
            if (board.getPiece(up_left).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, up_left, null));
            }
        }
        if (d && r) {
            ChessPosition down_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() - 1);
            if (board.getPiece(down_right).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, down_right, null));
            }
        }
        if (d && l) {
            ChessPosition down_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() - 1);
            if (board.getPiece(down_left).getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, down_left, null));
            }
        }
        return moves;
    }
}