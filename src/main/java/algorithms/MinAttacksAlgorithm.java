package algorithms;

import chessComponents.Field;

public class MinAttacksAlgorithm extends Algorithm {

    public MinAttacksAlgorithm(int n, PieceType pieceType) {
        super(n, pieceType);
    }

    @Override
    public AlgorithmOutput run() {
        long startTime = System.nanoTime();

        boolean success = repeatWithDifferentPermutations(50);

        long elapsedNanoSeconds = System.nanoTime() - startTime;
        return new AlgorithmOutput(chessboard, elapsedNanoSeconds, success);
    }

    private boolean repeatWithDifferentPermutations(int repeats) {
        while (repeats >= 0) {
            repeats--;
            chessboardManager.clearChessboard();
            chessboardManager.fillBoardOnePiecePerColumnAndRow();
            if (checkAndReplaceColumns())
                return true;
        }
        return false;
    }

    private boolean checkAndReplaceColumns() {
        // column 0 is always correct
        for (int i = 1; i < chessboard.getSize(); i++) {
            if (!isColumnCorrect(i)) {
                if (!tryReplaceAvailableColumns(i)) {
                    return false; // algorithm can't find right permutation for given input chessboard
                }
            }
        }
        return true;
    }

    private boolean isColumnCorrect(int columnNumber) {
        return !chessboardManager.arePreviousColumnsAttacked(columnNumber);
    }

    private boolean tryReplaceAvailableColumns(int columnNumber) {
        for (int currentColumn = columnNumber + 1; currentColumn < chessboard.getSize(); currentColumn++) {
            if (tryReplace(columnNumber, currentColumn)) {
                replaceColumns(columnNumber, currentColumn);
                return true;
            }
        }
        return false;
    }

    private boolean tryReplace(int oldColumnNumber, int testColumnNumber) {
        return !chessboardManager.arePreviousColumnsAttackedAfterReplace(oldColumnNumber, testColumnNumber);
    }

    private void replaceColumns(int oldColumnNumber, int newColumnNumber) {
        Field[] tempColumn = chessboard.getBoard()[oldColumnNumber];
        chessboard.getBoard()[oldColumnNumber] = chessboard.getBoard()[newColumnNumber];
        chessboard.getBoard()[newColumnNumber] = tempColumn;
    }
}
