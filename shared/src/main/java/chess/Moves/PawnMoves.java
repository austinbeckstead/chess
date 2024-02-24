package chess.Moves;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoves extends PieceMoves {
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessGame.TeamColor teamColor;


    public PawnMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor){
        super(board, myPosition, teamColor);
        this.myPosition = myPosition;
        this.board = board;
        this.teamColor = teamColor;
    }

    public Collection<ChessMove> getMoves() {
        Collection<ChessMove> moves = new ArrayList<ChessMove>();
        if (teamColor == ChessGame.TeamColor.WHITE) {
            ChessPosition up = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            if(myPosition.getRow() == 7) {
                if (board.getPiece(up) == null) {
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(myPosition, up, chess.ChessPiece.PieceType.ROOK));
                }
                ChessPosition diagLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
                if (board.getPiece(diagLeft) != null) {
                    if (board.getPiece(diagLeft).getTeamColor() == ChessGame.TeamColor.BLACK) {
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.ROOK));
                    }
                }
                ChessPosition diagRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
                if (board.getPiece(diagRight) != null) {
                    if (board.getPiece(diagRight).getTeamColor() == ChessGame.TeamColor.BLACK) {
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.ROOK));
                    }
                }
            }

            if(myPosition.getRow() < 7) {
                if (board.getPiece(up) == null) {
                    moves.add(new ChessMove(myPosition, up, null));
                }
                if (myPosition.getRow() == 2) {
                    ChessPosition upup = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                    if (board.getPiece(upup) == null && board.getPiece(up) == null) {
                        moves.add(new ChessMove(myPosition, upup, null));
                    }
                }


                if (myPosition.getColumn() > 1) {
                    ChessPosition diagLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
                    if (board.getPiece(diagLeft) != null) {
                        if (board.getPiece(diagLeft).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            moves.add(new ChessMove(myPosition, diagLeft, null));
                        }
                    }
                }
                if (myPosition.getColumn() < 8) {
                    ChessPosition diagRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
                    if (board.getPiece(diagRight) != null) {
                        if (board.getPiece(diagRight).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            moves.add(new ChessMove(myPosition, diagRight, null));
                        }
                    }
                }
            }

        }
        else if (teamColor == ChessGame.TeamColor.BLACK) {
            if(myPosition.getRow() == 2) {
                ChessPosition down = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
                if (board.getPiece(down) == null) {
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(myPosition, down, chess.ChessPiece.PieceType.ROOK));
                }
                ChessPosition diagLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
                if (board.getPiece(diagLeft) != null) {
                    if (board.getPiece(diagLeft).getTeamColor() == ChessGame.TeamColor.WHITE) {
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diagLeft, chess.ChessPiece.PieceType.ROOK));
                    }
                }
                ChessPosition diagRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
                if (board.getPiece(diagRight) != null) {
                    if (board.getPiece(diagRight).getTeamColor() == ChessGame.TeamColor.WHITE) {
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, diagRight, chess.ChessPiece.PieceType.ROOK));
                    }
                }
            }
            ChessPosition down = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
            if(myPosition.getRow() > 2) {
                if (board.getPiece(down) == null) {
                    moves.add(new ChessMove(myPosition, down, null));
                }
                if (myPosition.getRow() == 7) {
                    ChessPosition downdown = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                    if (board.getPiece(downdown) == null && board.getPiece(down) == null) {
                        moves.add(new ChessMove(myPosition, downdown, null));
                    }
                }
                if (myPosition.getColumn() > 1) {
                    ChessPosition diagLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
                    if (board.getPiece(diagLeft) != null) {
                        if (board.getPiece(diagLeft).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            moves.add(new ChessMove(myPosition, diagLeft, null));
                        }
                    }
                }
                if (myPosition.getColumn() < 8) {
                    ChessPosition diagRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
                    if (board.getPiece(diagRight) != null) {
                        if (board.getPiece(diagRight).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            moves.add(new ChessMove(myPosition, diagRight, null));
                        }
                    }
                }
            }

        }
        return moves;
    }

}
