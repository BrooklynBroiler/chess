package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove>moves = new ArrayList<>();
        int[][]knightMoves = {
                {2,1}, {2,-1}, {1,2},{1,-2}, {-2,1}, {-2, -1}, {-1, 2}, {-1,-2}
        };
        for (int[] move : knightMoves){
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
        return move.endPosition.getRow() < 1 || move.endPosition.getColumn() < 1 || move.endPosition.getRow() > 8 || move.endPosition.getColumn() > 8;
    }
    private boolean isEnemyPiece(ChessBoard board, ChessMove move){
        return !board.getPiece(move.endPosition).pieceColor.equals(board.getPiece(move.startPosition).pieceColor);
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
