package chessBoardManager;

import algorithms.PieceType;
import chessComponents.Chessboard;
import chessComponents.Coordinates;
import chessComponents.Field;

public class PreviousColumnsAttacksChecker {
    private Chessboard chessboard;

    public PreviousColumnsAttacksChecker(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public boolean arePreviousColumnsAttacked(int columnNumber, PieceType pieceType) {
        int lineNumber = getPieceLineNumber(columnNumber);
        if(pieceType == PieceType.QUEEN)
            return areDiagonalsUnderAttack(columnNumber, lineNumber);
        return false;
    }

    private int getPieceLineNumber(int columnNumber) {
        int counter=0;
        for(Field field : chessboard.getBoard()[columnNumber]) {
            if(!field.isFree())
                return counter;
            counter++;
        }
        return -1;
    }

    private boolean areDiagonalsUnderAttack(int columnNumber, int lineNumber) {
        int upDiagonalLineNumber = lineNumber + 1;
        int downDiagonalLineNumber = lineNumber - 1;
        columnNumber--;
        while(columnNumber >= 0) {
            if(isTherePiece(columnNumber, upDiagonalLineNumber) || isTherePiece(columnNumber, downDiagonalLineNumber))
                return true;
            upDiagonalLineNumber++;
            downDiagonalLineNumber--;
            columnNumber--;
        }
        return false;
    }

    private boolean isTherePiece(int columnNumber, int lineNumber) {
        if(Coordinates.inBounds(columnNumber,lineNumber, chessboard.getSize()))
            return !chessboard.getBoard()[columnNumber][lineNumber].isFree();
        return false;
    }

    public boolean arePreviousColumnsAttackedAfterReplace(int oldColumnNumber, int testColumnNumber, PieceType pieceType) {
        int lineNumber = getPieceLineNumber(testColumnNumber);
        if(pieceType == PieceType.QUEEN)
            return areDiagonalsUnderAttack(oldColumnNumber, lineNumber);
        return false;
    }
}
