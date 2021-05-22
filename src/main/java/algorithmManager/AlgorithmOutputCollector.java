package algorithmManager;

import algorithms.AlgorithmOutput;
import algorithms.AlgorithmType;
import algorithms.PieceType;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
@Getter
public class AlgorithmOutputCollector {
    private AlgorithmType algorithmType;
    private PieceType pieceType;
    private ArrayList<AlgorithmOutput> algorithmOutputsList;

    private double successRate;
    private long averageRunTime;

    public AlgorithmOutputCollector(AlgorithmType algorithmType, PieceType pieceType){
        this.algorithmType = algorithmType;
        this.pieceType = pieceType;
        algorithmOutputsList = new ArrayList<>();
    }

    public void addOutput(AlgorithmOutput algorithmOutput) {
        algorithmOutputsList.add(algorithmOutput);
    }

    public void computePerformance() {
        double successes = 0;
        long summaryRunTime = 0;
        for(AlgorithmOutput algorithmOutput : algorithmOutputsList){
            if(algorithmOutput.isSuccess())
                successes++;
            summaryRunTime += algorithmOutput.getElapsedNanoSeconds();
        }
        successRate = successes/algorithmOutputsList.size();
        averageRunTime = summaryRunTime/algorithmOutputsList.size();
    }

    public void printPerformance() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Algorithm: ");
        switch (algorithmType) {
            case MIN_ATTACKS_ALGORITHM -> stringBuilder.append("Min attacks algorithm\n");
            case MOST_FREE_FIELDS_ALGORITHM -> stringBuilder.append("Most free fields algorithm\n");
        }
        stringBuilder.append("Problem size: ");
        stringBuilder.append(algorithmOutputsList.get(0).getChessboard().getSize());
        stringBuilder.append("\n");
        stringBuilder.append("Algorithm repeats: ");
        stringBuilder.append(algorithmOutputsList.size());
        stringBuilder.append("\n");
        stringBuilder.append("Success rate: ");
        BigDecimal successRateBD = new BigDecimal(successRate);
        successRateBD = successRateBD.setScale(2, RoundingMode.HALF_UP);
        stringBuilder.append((int) (successRateBD.doubleValue()*100));
        stringBuilder.append("%\n");
        stringBuilder.append("Average run time: ");
        stringBuilder.append(averageRunTime);
        stringBuilder.append("ns\n");

        System.out.println(stringBuilder);
    }
}
