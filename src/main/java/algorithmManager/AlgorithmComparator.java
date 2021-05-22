package algorithmManager;

import algorithms.AlgorithmType;
import algorithms.PieceType;

import java.util.ArrayList;

public class AlgorithmComparator {
    private AlgorithmRunner algorithmRunner;
    private int n;

    public AlgorithmComparator(int n) {
        this.n = n;
        algorithmRunner = new AlgorithmRunner();
    }

    public ArrayList<AlgorithmOutputCollector> compare(){
        ArrayList<AlgorithmOutputCollector> outputCollectors = new ArrayList<>();

        outputCollectors.add(algorithmRunner.testAlgorithm(n, AlgorithmType.MIN_ATTACKS_ALGORITHM, PieceType.QUEEN,1000));

        return outputCollectors;
    }


}
