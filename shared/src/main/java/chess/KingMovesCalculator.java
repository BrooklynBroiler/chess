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

        }
        return moves;
    }
}
