package algorithmManager;

import algorithms.AlgorithmOutput;
import algorithms.AlgorithmType;
import algorithms.PieceType;
import lombok.Getter;

import java.util.ArrayList;

public class AlgorithmOutputCollector {
    @Getter
    private AlgorithmType algorithmType;
    @Getter
    private PieceType pieceType;
    private ArrayList<AlgorithmOutput> algorithmOutputsList;

    public AlgorithmOutputCollector(AlgorithmType algorithmType, PieceType pieceType){
        this.algorithmType = algorithmType;
        this.pieceType = pieceType;
        algorithmOutputsList = new ArrayList<>();
    }

    public void addOutput(AlgorithmOutput algorithmOutput) {
        algorithmOutputsList.add(algorithmOutput);
    }

    public void computePerformance() {
        //TODO:: na podstawie zebranych outputow wyliczyc sredni czas, % udanych, itp (dodac je jako zmienne)
    }

    public void printPerformance() {
        //TODO:: wyswietlenei tego co obliczone w computePerformance
    }
}
