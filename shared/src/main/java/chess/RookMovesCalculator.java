package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove>moves = new ArrayList<>();
        ChessPosition oneSpotLeft = new ChessPosition(position.getRow()- 1, position.getColumn());
        ChessMove oneMoveLeft = new ChessMove(position, oneSpotLeft, null);
        addMovesLeft(2, board, oneMoveLeft, moves);

        ChessPosition oneSpotRight = new ChessPosition(position.getRow() + 1, position.getColumn());
        ChessMove oneMoveRight = new ChessMove(position, oneSpotRight, null);
        addMovesRight(2, board, oneMoveRight, moves);

        ChessPosition oneSpotUp = new ChessPosition(position.getRow() , position.getColumn() + 1);
        ChessMove oneMoveUp = new ChessMove(position, oneSpotUp, null);
        addMovesUp(2, board, oneMoveUp, moves);

        ChessPosition oneSpotDown = new ChessPosition(position.getRow() , position.getColumn() - 1);
        ChessMove oneMoveDown = new ChessMove(position, oneSpotDown, null);
        addMovesDown(2, board, oneMoveDown, moves);
        return moves;
    }

// logic for checking moves to the left and adding them recursively
    private Collection<ChessMove> addMovesLeft(int counter, ChessBoard board, ChessMove move, Collection<ChessMove> moves){
        if (isOutOfBounds(move)){
            return moves;
        }
        else if( board.getPiece(move.endPosition)!= null){
            if (isEnemyPiece(board, move)) {
                moves.add(move);
                return moves;
            }
        }
        else{
            ChessPosition newPos = new ChessPosition(move.startPosition.getRow() - counter, move.startPosition.getColumn());
            ChessMove possibleMove = new ChessMove(move.startPosition, newPos, null);
            moves.add(move);
            return addMovesLeft(counter + 1, board, possibleMove, moves);
        }
        return moves;
    }


//    logic for checking moves to the right and adding them recursively
    private Collection<ChessMove> addMovesRight(int counter, ChessBoard board, ChessMove move, Collection<ChessMove> moves){
        if (isOutOfBounds(move)){
            return moves;
        }
        else if( board.getPiece(move.endPosition)!= null){
            if (isEnemyPiece(board, move)) {
                moves.add(move);
                return moves;
            }
        }
        else{
            ChessPosition newPos = new ChessPosition(move.startPosition.getRow() + counter, move.startPosition.getColumn());
            ChessMove possibleMove = new ChessMove(move.startPosition, newPos, null);
            moves.add(move);
            return addMovesRight(counter + 1, board, possibleMove, moves);
        }
        return moves;
    }

//     logic for checking moves to the up direction and adding them recursively
    private Collection<ChessMove> addMovesUp(int counter, ChessBoard board, ChessMove move, Collection<ChessMove> moves){
        if (isOutOfBounds(move)){
            return moves;
        }
        else if( board.getPiece(move.endPosition)!= null){
            if (isEnemyPiece(board, move)) {
                moves.add(move);
                return moves;
            }
        }
        else{
            ChessPosition newPos = new ChessPosition(move.startPosition.getRow() , move.startPosition.getColumn() + counter);
            ChessMove possibleMove = new ChessMove(move.startPosition, newPos, null);
            moves.add(move);
            return addMovesUp(counter + 1, board, possibleMove, moves);
        }
        return moves;
    }

//     logic for checking moves to the down direction and adding them recursively
    private Collection<ChessMove> addMovesDown(int counter, ChessBoard board, ChessMove move, Collection<ChessMove> moves){
        if (isOutOfBounds(move)){
            return moves;
        }
        else if( board.getPiece(move.endPosition)!= null){
            if (isEnemyPiece(board, move)) {
                moves.add(move);
                return moves;
            }
        }
        else{
            ChessPosition newPos = new ChessPosition(move.startPosition.getRow() , move.startPosition.getColumn() - counter);
            ChessMove possibleMove = new ChessMove(move.startPosition, newPos, null);
            moves.add(move);
            return addMovesDown(counter + 1, board, possibleMove, moves);
        }
        return moves;
    }
//  Checks to see if Moves are out of Bounds
    private boolean isOutOfBounds(ChessMove move){
        return move.endPosition.getRow() < 1 || move.endPosition.getColumn() < 1 || move.endPosition.getRow() > 8 || move.endPosition.getColumn() > 8;
    }
//    Checks to see if the piece is an enemy piece
    private boolean isEnemyPiece(ChessBoard board, ChessMove move){
        return !board.getPiece(move.endPosition).pieceColor.equals(board.getPiece(move.startPosition).pieceColor);
    }
//    checks if a move is valid or not
    private boolean isValidMove(ChessBoard board, ChessMove move){
        if (isOutOfBounds(move)){
            return false;
        } else if (board.getPiece(move.endPosition)!= null) {
            return isEnemyPiece(board, move);
        }
        return true;
    }
}
