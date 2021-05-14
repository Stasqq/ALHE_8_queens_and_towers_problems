package algorithms;

import chessComponents.Chessboard;

public abstract class Algorithm {
    protected int n;
    protected Chessboard chessboard;

    public abstract AlgorithmOutput run();
}
