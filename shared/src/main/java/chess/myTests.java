package chess;

public class myTests {

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        ChessPosition pos = new ChessPosition(3, 6);
        board.addPiece(pos,new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        ChessPiece myPiece = board.getPiece(pos);
        System.out.println(pos.getRow());
        System.out.println(pos.getColumn());
        System.out.println(myPiece.pieceMoves(board, pos));

    }
}
