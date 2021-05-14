package algorithmManager;

import algorithms.*;

public class AlgorithmRunner {
    private Algorithm algorithm;

    public AlgorithmOutputCollector testAlgorithm(int n, AlgorithmType algorithmType, PieceType pieceType, int iterations) {
        AlgorithmOutputCollector algorithmOutputCollector = new AlgorithmOutputCollector(algorithmType, pieceType);
        setUpAlgorithm(n, algorithmType, pieceType);
        while(iterations > 0) {
            algorithmOutputCollector.addOutput(algorithm.run());
            iterations--;
        }
        algorithmOutputCollector.computePerformance();
        return algorithmOutputCollector;
    }

    private void setUpAlgorithm(int n, AlgorithmType algorithmType, PieceType pieceType){
        if(algorithmType == AlgorithmType.MIN_ATTACKS_ALGORITHM){
            algorithm = new MinAttacksAlgorithm(n,pieceType);
        }
        else {
            algorithm = new MostFreeFieldsAlgorithm(n,pieceType);
        }
    }
}
