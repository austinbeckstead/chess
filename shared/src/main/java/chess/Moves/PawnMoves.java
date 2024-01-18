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
            ChessPosition up = new ChessPosition(myPosition.getColumn(), myPosition.getRow() + 1);
            if(myPosition.getRow() == 7) {
                if (board.getPiece(up) == null) {
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.ROOK));
                }
                ChessPosition diag_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() + 1);
                if (board.getPiece(diag_left) != null) {
                    if (board.getPiece(diag_left).getTeamColor() == ChessGame.TeamColor.BLACK) {
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.ROOK));
                    }
                }
                ChessPosition diag_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() + 1);
                if (board.getPiece(diag_right) != null) {
                    if (board.getPiece(diag_right).getTeamColor() == ChessGame.TeamColor.BLACK) {
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.ROOK));
                    }
                }
            }

            if(myPosition.getRow() < 7) {
                if (board.getPiece(up) == null) {
                    moves.add(new ChessMove(myPosition, up, null));
                }

                if (myPosition.getColumn() > 1) {
                    ChessPosition diag_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() + 1);
                    if (board.getPiece(diag_left) != null) {
                        if (board.getPiece(diag_left).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            moves.add(new ChessMove(myPosition, diag_left, null));
                        }
                    }
                }
                if (myPosition.getColumn() < 8) {
                    ChessPosition diag_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() + 1);
                    if (board.getPiece(diag_right) != null) {
                        if (board.getPiece(diag_right).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            moves.add(new ChessMove(myPosition, diag_right, null));
                        }
                    }
                }
            }

        }
        else if (teamColor == ChessGame.TeamColor.BLACK) {
            if(myPosition.getRow() == 2) {
                ChessPosition down = new ChessPosition(myPosition.getColumn(), myPosition.getRow() - 1);
                if (board.getPiece(down) == null) {
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.ROOK));
                }
                ChessPosition diag_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() - 1);
                if (board.getPiece(diag_left) != null) {
                    if (board.getPiece(diag_left).getTeamColor() == ChessGame.TeamColor.BLACK) {
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diag_left, chess.ChessPiece.PieceType.ROOK));
                    }
                }
                ChessPosition diag_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() - 1);
                if (board.getPiece(diag_right) != null) {
                    if (board.getPiece(diag_right).getTeamColor() == ChessGame.TeamColor.BLACK) {
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diag_right, chess.ChessPiece.PieceType.ROOK));
                    }
                }
            }
            ChessPosition down = new ChessPosition(myPosition.getColumn(), myPosition.getRow() - 1);
            if(myPosition.getRow() > 1) {
                if (board.getPiece(down) == null) {
                    moves.add(new ChessMove(myPosition, down, null));
                }

                if (myPosition.getColumn() > 1) {
                    ChessPosition diag_left = new ChessPosition(myPosition.getColumn() - 1, myPosition.getRow() - 1);
                    if (board.getPiece(diag_left) != null) {
                        if (board.getPiece(diag_left).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            moves.add(new ChessMove(myPosition, diag_left, null));
                        }
                    }
                }
                if (myPosition.getColumn() < 8) {
                    ChessPosition diag_right = new ChessPosition(myPosition.getColumn() + 1, myPosition.getRow() - 1);
                    if (board.getPiece(diag_right) != null) {
                        if (board.getPiece(diag_right).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            moves.add(new ChessMove(myPosition, diag_right, null));
                        }
                    }
                }
            }

        }
        return moves;
    }

}
