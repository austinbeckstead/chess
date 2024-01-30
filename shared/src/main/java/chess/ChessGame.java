package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor teamTurn;
    private ChessBoard board;


    public ChessGame() {
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        if (piece == null) return null;
        Collection<ChessMove> finalMoves = new ArrayList<ChessMove>();
        Collection<ChessMove> pieceMoves = board.getPiece(startPosition).pieceMoves(board, startPosition);
        for(ChessMove move: pieceMoves){
            ChessBoard newBoard = board.copyBoard();
            newBoard.movePiece(startPosition, move.getEndPosition());
            ChessGame newGame = new ChessGame();
            newGame.setBoard(newBoard);
            if(!(newGame.isInCheck(piece.getTeamColor()))){
                finalMoves.add(move);
            }
        }
        return finalMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if(board.getPiece(move.getStartPosition()).getTeamColor() != teamTurn) throw new InvalidMoveException();
        for(ChessMove validMove: validMoves(move.getStartPosition())){
            if(move.equals(validMove)){
                board.movePiece(move.getStartPosition(), move.getEndPosition());
                if(teamTurn == TeamColor.BLACK){
                    teamTurn = TeamColor.WHITE;
                }
                else{
                    teamTurn = TeamColor.BLACK;
                }
                return;
            }
        }
        throw new InvalidMoveException();
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        for (int row = 1; row < 9; row++){
            for(int col = 1; col < 9; col++){
                ChessPosition startPosition = new ChessPosition(row, col);
                ChessPiece piece = board.getPiece(startPosition);
                if(piece != null && piece.getTeamColor() != teamColor){
                    Collection<ChessMove> moves = piece.pieceMoves(board, startPosition);
                    for(ChessMove move: moves){
                        ChessPiece endPiece = board.getPiece(move.getEndPosition());
                        if(endPiece != null){
                            if(endPiece.getPieceType() == ChessPiece.PieceType.KING){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        return isInStalemate(teamColor) && isInCheck(teamColor);
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                ChessPosition startPosition = new ChessPosition(row, col);
                ChessPiece piece = board.getPiece(startPosition);
                if (piece != null && piece.getTeamColor() == teamColor) {
                    if(!validMoves(startPosition).isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
