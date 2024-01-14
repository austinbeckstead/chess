package chess.Moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public class PieceMoves {
    private Collection<ChessMove> moves;
    private ChessBoard board;
    private ChessPosition myPosition;
    private chess.ChessGame.TeamColor teamColor;
    public PieceMoves(){};
    public PieceMoves(ChessBoard board, ChessPosition myPosition, chess.ChessGame.TeamColor teamColor){
        this.board = board;
        this.myPosition = myPosition;
        this.teamColor = teamColor;
    }
    public Collection<ChessMove> getMoves(){
        return moves;
    }
}
