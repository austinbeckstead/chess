package chess;

import chess.Moves.KingMoves;

import java.util.Iterator;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;
    private ChessPosition myPosition;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type && Objects.equals(myPosition, that.myPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type, myPosition);
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = null;
        Collection<ChessMove> moreMoves;

        switch (type){
            case PieceType.KING:
                moves = new chess.Moves.KingMoves(board, myPosition, pieceColor).getMoves();
                break;
            case PieceType.QUEEN:
                moves = new chess.Moves.RookMoves(board, myPosition, pieceColor).getMoves();
                moreMoves = new chess.Moves.BishopMoves(board, myPosition, pieceColor).getMoves();
                moves.addAll(moreMoves);

                break;
            case PieceType.BISHOP:
                moves = new chess.Moves.BishopMoves(board, myPosition, pieceColor).getMoves();
                break;
            case PieceType.KNIGHT:
                moves = new chess.Moves.KnightMoves(board, myPosition, pieceColor).getMoves();
                break;
            case PieceType.ROOK:
                moves = new chess.Moves.RookMoves(board, myPosition, pieceColor).getMoves();
                break;
            case PieceType.PAWN:
                moves = new chess.Moves.PawnMoves(board, myPosition, pieceColor).getMoves();
                break;
        }
        return moves;

    }
}
