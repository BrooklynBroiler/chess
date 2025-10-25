package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] pwnMoves = {};

        if (board.getPiece(position).pieceColor.equals(ChessGame.TeamColor.WHITE)) {
            if (position.getRow() == 2) {
                ChessPosition plusOne = new ChessPosition(position.getRow() + 1, position.getColumn());
                ChessPosition plusTwo = new ChessPosition(position.getRow() + 2, position.getColumn());

                if (board.getPiece(plusTwo) == null && board.getPiece(plusOne) == null) {
                    pwnMoves = new int[][]{
                            {1, 0}, {2, 0}
                    };
                } else if (board.getPiece(plusTwo) != null && board.getPiece(plusOne) == null) {
                    pwnMoves = new int[][]{{1, 0}};
                }
            } else {
                pwnMoves = new int[][]{{1, 0}};
            }
            for (int[] move : pwnMoves) {
                int nRow = position.getRow() + move[0];
                int nCol = position.getColumn() + move[1];
                ChessPosition newPos = new ChessPosition(nRow, nCol);
                ChessMove possibleMove = new ChessMove(position, newPos, null);
                if (board.getPiece(possibleMove.endPosition) == null && !isOutOfBounds(possibleMove)) {
                    if (possibleMove.endPosition.getRow() == 8) {
                        addPromotionMoves(possibleMove, moves);
                    } else {
                        moves.add(possibleMove);
                    }
                }
            }
            ChessPosition topLeft = new ChessPosition(position.getRow() + 1, position.getColumn() - 1);
            ChessPosition topRight = new ChessPosition(position.getRow() + 1, position.getColumn() + 1);
            ChessMove moveTopLeft = new ChessMove(position, topLeft, null);
            if (!isOutOfBounds(moveTopLeft) && board.getPiece(topLeft) != null) {
                if (isEnemyPiece(board, moveTopLeft)) {
                    if (moveTopLeft.endPosition.getRow() == 8) {
                        addPromotionMoves(moveTopLeft, moves);
                    } else {
                        moves.add(moveTopLeft);
                    }
                }
            }

            ChessMove moveTopRight = new ChessMove(position, topRight, null);
            if (!isOutOfBounds(moveTopRight) && board.getPiece(topRight) != null) {
                if (isEnemyPiece(board, moveTopRight)) {
                    if (moveTopRight.endPosition.getRow() == 8) {
                        addPromotionMoves(moveTopRight, moves);
                    } else {
                        moves.add(moveTopRight);
                    }
                }
            }

        }

         else if (board.getPiece(position).pieceColor.equals(ChessGame.TeamColor.BLACK)) {
            if (position.getRow() == 7) {
                ChessPosition plusOne = new ChessPosition(position.getRow() - 1, position.getColumn());
                ChessPosition plusTwo = new ChessPosition(position.getRow() - 2, position.getColumn());

                if (board.getPiece(plusTwo) == null && board.getPiece(plusOne) == null) {
                    pwnMoves = new int[][]{
                            {-1, 0}, {-2, 0}
                    };
                } else {
                    pwnMoves = new int[][]{{-1, 0}};
                }
            } else {
                pwnMoves = new int[][]{{-1, 0}};
            }

            for (int[] move : pwnMoves) {
                int nRow = position.getRow() + move[0];
                int nCol = position.getColumn() + move[1];
                ChessPosition newPos = new ChessPosition(nRow, nCol);
                ChessMove possibleMove = new ChessMove(position, newPos, null);
                if (board.getPiece(newPos) == null && !isOutOfBounds(possibleMove)) {
                    if (possibleMove.endPosition.getRow() == 1) {
                        addPromotionMoves(possibleMove, moves);
                    } else {
                        moves.add(possibleMove);
                    }
                }
            }
            ChessPosition bottomLeft = new ChessPosition(position.getRow() - 1, position.getColumn() - 1);
            ChessPosition bottomRight = new ChessPosition(position.getRow() - 1, position.getColumn() + 1);
            ChessMove moveBottomLeft = new ChessMove(position, bottomLeft, null);
            if (!isOutOfBounds(moveBottomLeft) && board.getPiece(bottomLeft) != null) {
                if (board.getPiece(moveBottomLeft.endPosition).pieceColor.equals(chess.ChessGame.TeamColor.WHITE)) {
                    if (moveBottomLeft.endPosition.getRow() == 1) {
                        addPromotionMoves(moveBottomLeft, moves);
                    } else {
                        moves.add(moveBottomLeft);
                    }
                }
            }

            ChessMove moveBottomRight = new ChessMove(position, bottomRight, null);
            if (!isOutOfBounds(moveBottomRight) && board.getPiece(bottomRight) != null) {
                if (board.getPiece(moveBottomRight.endPosition).pieceColor.equals(chess.ChessGame.TeamColor.WHITE)) {
                    if (moveBottomRight.endPosition.getRow() == 1) {
                        addPromotionMoves(moveBottomRight, moves);
                    } else {
                        moves.add(moveBottomRight);
                    }
                }
            }
        }
        return moves;
    }

    private boolean isOutOfBounds(ChessMove move) {
        return move.endPosition.getRow() < 1 || move.endPosition.getColumn() < 1 || move.endPosition.getRow() > 8 || move.endPosition.getColumn() > 8;
    }

    private boolean isEnemyPiece(ChessBoard board, ChessMove move) {
        return !board.getPiece(move.endPosition).pieceColor.equals(board.getPiece(move.startPosition).pieceColor);
    }

    private void addPromotionMoves(ChessMove move, Collection<ChessMove> moves){
        ChessPosition startPosition = move.startPosition;
        ChessPosition endPosition = move.endPosition;
        ChessMove promoteQueen = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.QUEEN);
        ChessMove promoteRook = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.ROOK);
        ChessMove promoteBishop = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.BISHOP);
        ChessMove promoteKnight = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.KNIGHT);

        moves.add(promoteQueen);
        moves.add(promoteRook);
        moves.add(promoteKnight);
        moves.add(promoteBishop);

    }
}