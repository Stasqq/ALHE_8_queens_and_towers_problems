package algorithms;

import chessBoardManager.ChessboardManager;
import chessComponents.Chessboard;

public class MostFreeFieldsAlgorithm extends Algorithm {

    public MostFreeFieldsAlgorithm(int n, PieceType pieceType) {
        super(n, pieceType);
    }

    @Override
    public AlgorithmOutput run() {
        long startTime = System.nanoTime();

        //TODO:: dzialanie algorytmu

        long elapsedNanoSeconds = System.nanoTime() - startTime;
        return new AlgorithmOutput(chessboard, elapsedNanoSeconds);
    }
}
