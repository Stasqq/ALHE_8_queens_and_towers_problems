package algorithms;


import chessComponents.Coordinates;
import chessComponents.Field;

import java.util.*;

public class MostFreeFieldsAlgorithm extends Algorithm {


    public MostFreeFieldsAlgorithm(int n, PieceType pieceType) {
        super(n, pieceType);
    }

    @Override
    public AlgorithmOutput run() {
        long startTime = System.nanoTime();

        chessboardManager.clearChessboard();
        Boolean success;

        //fill rows except of last one
        for (int i = 0; i < chessboard.getSize() - 1; i++) {
            Map<Coordinates, Integer> heuristics = new HashMap(calculateHeuristicValuesForRow(i));
            if (isEndOfTry(heuristics)) {
                return finishAlgorithm(startTime, false);
            }

            setPosition(heuristics);
        }

        // try to fill last row
        success = trySetPieceInLastRow();


        return finishAlgorithm(startTime, success);
    }

    private AlgorithmOutput finishAlgorithm(long startTime, Boolean success) {
        long elapsedNanoSeconds = System.nanoTime() - startTime;
        return new AlgorithmOutput(chessboard, elapsedNanoSeconds, success);
    }

    private Boolean isEndOfTry(Map<Coordinates, Integer> heuristics) {
        return heuristics.isEmpty() || Collections.max(heuristics.values()) == 0;
    }

    private Boolean trySetPieceInLastRow() {
        for (int i = 0; i < chessboard.getSize(); i++) {
            Coordinates position = new Coordinates(chessboard.getSize() - 1, i);
            if (chessboard.getFieldByCoordinates(position).getAttackersNumber() == 0) {
                chessboardManager.setPiece(position);
                return true;
            }
        }

        return false;
    }


    private Map<Coordinates, Integer> calculateHeuristicValuesForRow(int row) {
        Map<Coordinates, Integer> heuristicValues = new HashMap<>();

        for (int i = 0; i < chessboard.getSize(); i++) {
            Coordinates position = new Coordinates(row, i);
            if (chessboard.getFieldByCoordinates(position).getAttackersNumber() == 0)
                heuristicValues.put(position, getHeuristicValueForPosition(position));
        }

        return heuristicValues;
    }


    private void setPosition(Map<Coordinates, Integer> heuristics) {
        HashMap values = new HashMap(heuristics);
        // filter map to have only min values
        Integer max = Collections.max(heuristics.values());
        for (Map.Entry<Coordinates, Integer> entry : heuristics.entrySet()) {
            if (entry.getValue() < max)
                values.remove(entry.getKey());
        }

        //get random value from min values and set position
        Set<Coordinates> keySet = values.keySet();
        List<Coordinates> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIndex = new Random().nextInt(size);

        Coordinates position = keyList.get(randIndex);

        chessboardManager.setPiece(position);

    }

    public int getHeuristicValueForPosition(Coordinates position) {
        List<Integer> freeFieldsPerRow = new ArrayList<>();
        chessboardManager.setPiece(position);

        for (int i = position.getRow() + 1; i < chessboard.getSize(); i++) {
            freeFieldsPerRow.add(getNotAttackedFieldsInRow(i));
        }

        chessboardManager.removePiece(position);

        int x = Collections.min(freeFieldsPerRow);
        return x;
    }

    private int getNotAttackedFieldsInRow(int row) {
        int value = 0;
        for (int i = 0; i < chessboard.getSize(); i++) {
            Field field = chessboard.getBoard()[row][i];
            if (field.getAttackersNumber() == 0)
                value++;
        }

        return value;
    }

}
