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

        //outputCollectors.add(algorithmRunner.testAlgorithm(n, AlgorithmType.MIN_ATTACKS_ALGORITHM, PieceType.QUEEN,50));
        //outputCollectors.add(algorithmRunner.testAlgorithm(n, AlgorithmType.MOST_FREE_FIELDS_ALGORITHM, PieceType.QUEEN, 1000));
        outputCollectors.add(algorithmRunner.testAlgorithm(n, AlgorithmType.SIMULATED_ANNEALING, PieceType.QUEEN, 1000, 100));
        //outputCollectors.add(algorithmRunner.testAlgorithm(n, AlgorithmType.MIN_ATTACKS_ALGORITHM, PieceType.QUEEN, 100));
        return outputCollectors;
    }


}
