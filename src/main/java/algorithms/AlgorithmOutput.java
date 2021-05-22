package algorithms;

import chessComponents.Chessboard;
import lombok.Getter;

@Getter
public class AlgorithmOutput {
    private final Chessboard chessboard;
    private final long elapsedNanoSeconds;
    private final boolean success;

    public AlgorithmOutput(Chessboard chessboard, long elapsedNanoSeconds){
        this.chessboard = chessboard;
        this.elapsedNanoSeconds = elapsedNanoSeconds;
        this.success = true;
    }

    public AlgorithmOutput(Chessboard chessboard, long elapsedNanoSeconds, boolean success){
        this.chessboard = chessboard;
        this.elapsedNanoSeconds = elapsedNanoSeconds;
        this.success = success;
    }

}
