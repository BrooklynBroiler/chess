package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove>moves = new ArrayList<>();
        int[][]kingMoves = {
                {1,1}, {1,0}, {0,1},{1,-1}, {-1,1}, {0,-1}, {-1,0}, {-1,-1}
        };
        for (int[] move : kingMoves){
            int nRow = position.getRow() + move[0];
            int nCol = position.getColumn() + move[1];
            ChessPosition newPos = new ChessPosition(nRow, nCol);
            ChessMove possibleMove = new ChessMove(position,newPos, null);
            if (isValidMove(board, possibleMove)){
                moves.add(possibleMove);
            }
        }

        return moves;
    }
    private boolean isOutOfBounds(ChessMove move){
        int row = move.endPosition.getRow();
        int col = move.endPosition.getColumn();
        return row < 1 || col < 1 || row > 8 || col > 8;
    }
    private boolean isEnemyPiece(ChessBoard board, ChessMove move){
        ChessPiece pieceOne= board.getPiece(move.endPosition);
        ChessPiece pieceTwo = board.getPiece(move.startPosition);
        return !pieceOne.pieceColor.equals(pieceTwo.pieceColor);
    }
    private boolean isValidMove(ChessBoard board, ChessMove move){
        if (isOutOfBounds(move)){
            return false;
        } else if (board.getPiece(move.endPosition)!= null) {
            return isEnemyPiece(board, move);
        }
        return true;
    }
}
