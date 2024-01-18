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
        boolean u = (myPosition.getRow() < 8);
        boolean d = (myPosition.getRow() > 1);
        boolean l = (myPosition.getColumn() > 8);
        boolean r = (myPosition.getColumn() < 1);

        if (u) {
            ChessPosition up = new ChessPosition(myPosition.getColumn(), myPosition.getRow() + 1);
            chess.ChessPiece piece = board.getPiece(up);
            if (piece == null){
                moves.add(new ChessMove(myPosition, up, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, up, null));
            }
        }
        if (d) {
            ChessPosition down = new ChessPosition(myPosition.getColumn(), myPosition.getRow() - 1);
            chess.ChessPiece piece = board.getPiece(down);
            if (piece == null){
                moves.add(new ChessMove(myPosition, down, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, down, null));
            }

        }
        if (l) {
            ChessPosition left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() + 1);
            chess.ChessPiece piece = board.getPiece(left);
            if (piece == null){
                moves.add(new ChessMove(myPosition, left, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, left, null));
            }

        }
        if (r) {
            ChessPosition right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() + 1);
            chess.ChessPiece piece = board.getPiece(right);
            if (piece == null){
                moves.add(new ChessMove(myPosition, right, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, right, null));
            }
        }
        if (u && r) {
            ChessPosition up_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() + 1);
            chess.ChessPiece piece = board.getPiece(up_right);
            if (piece == null){
                moves.add(new ChessMove(myPosition, up_right, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, up_right, null));
            }

        }
        if(u && l) {
            ChessPosition up_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() + 1);
            chess.ChessPiece piece = board.getPiece(up_left);
            if (piece == null){
                moves.add(new ChessMove(myPosition, up_left, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, up_left, null));
            }
        }
        if (d && r) {
            ChessPosition down_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() - 1);
            chess.ChessPiece piece = board.getPiece(down_right);
            if (piece == null){
                moves.add(new ChessMove(myPosition, down_right, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, down_right, null));
            }
        }
        if (d && l) {
            ChessPosition down_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() - 1);
            chess.ChessPiece piece = board.getPiece(down_left);
            if (piece == null){
                moves.add(new ChessMove(myPosition, down_left, null));
            }
            else if (piece.getTeamColor() != teamColor) {
                moves.add(new ChessMove(myPosition, down_left, null));
            }
        }
        return moves;
    }
}