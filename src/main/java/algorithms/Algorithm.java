package algorithms;

import chessBoardManager.ChessboardManager;
import chessComponents.Chessboard;

public abstract class Algorithm {
    protected Chessboard chessboard;
    protected ChessboardManager chessboardManager;

    Algorithm(int n, PieceType pieceType) {
        this.chessboard = new Chessboard(n);
        this.chessboardManager = new ChessboardManager(pieceType, chessboard);
    }

    public abstract AlgorithmOutput run();
}
