package algorithmManager;

import algorithms.*;

public class AlgorithmRunner {
    private Algorithm algorithm;

    public AlgorithmOutputCollector testAlgorithm(int n, AlgorithmType algorithmType, PieceType pieceType, int iterations) {
        setUpAlgorithm(n, algorithmType, pieceType);
        return multipleAlgorithmRuns(iterations, algorithmType, pieceType);
    }

    public AlgorithmOutputCollector testAlgorithm(int n, AlgorithmType algorithmType, PieceType pieceType, int iterations,
                                                  double temperature) {
        if(algorithmType != AlgorithmType.SIMULATED_ANNEALING)
            throw new IllegalArgumentException("This method is only for Simulated Annealing algorithm!");
        setUpSimulatedAnnealingAlgorithm(n, pieceType, temperature);
        return multipleAlgorithmRuns(iterations, algorithmType, pieceType);
    }

    private void setUpAlgorithm(int n, AlgorithmType algorithmType, PieceType pieceType){
        if(algorithmType == AlgorithmType.MIN_ATTACKS_ALGORITHM){
            algorithm = new MinAttacksAlgorithm(n,pieceType);
        }
        else if(algorithmType == AlgorithmType.MOST_FREE_FIELDS_ALGORITHM) {
            algorithm = new MostFreeFieldsAlgorithm(n,pieceType);
        }
    }

    private void setUpSimulatedAnnealingAlgorithm(int n, PieceType pieceType,
                                                  double temperature) {
        algorithm = new SimulatedAnnealing(n, pieceType, temperature);
    }

    private AlgorithmOutputCollector multipleAlgorithmRuns(int iterations, AlgorithmType algorithmType, PieceType pieceType) {
        AlgorithmOutputCollector algorithmOutputCollector = new AlgorithmOutputCollector(algorithmType, pieceType);
        while(iterations > 0) {
            algorithmOutputCollector.addOutput(algorithm.run());
            iterations--;
        }
        algorithmOutputCollector.computePerformance();
        return algorithmOutputCollector;
    }
}
