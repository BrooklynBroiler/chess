package chess;

import java.util.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    ChessGame.TeamColor pieceColor;
    ChessPiece.PieceType pieceType;
    Map<PieceType, PieceMovesCalculator> calcMap;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;
        calcMap = new HashMap<>();
        calcMap.put(ChessPiece.PieceType.KING, new KingMovesCalculator());
        calcMap.put(PieceType.KNIGHT, new KnightMovesCalculator());
        calcMap.put(PieceType.ROOK, new RookMovesCalculator());
        calcMap.put(PieceType.BISHOP, new BishopMovesCalculator());

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
        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return calcMap.get(board.getPiece(myPosition).pieceType).pieceMoves(board, myPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChessPiece that)) {
            return false;
        }
        return pieceColor == that.pieceColor && pieceType == that.pieceType && Objects.equals(calcMap, that.calcMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType, calcMap);
    }
}
