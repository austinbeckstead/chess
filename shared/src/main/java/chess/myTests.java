package chess;

public class myTests {

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        ChessPosition pos = new ChessPosition(8, 1);
        board.addPiece(new ChessPosition(8, 1),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        ChessPiece myPiece = board.getPiece(pos);
        System.out.println(myPiece.pieceMoves(board, pos));

    }
}
