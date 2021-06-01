import algorithms.MostFreeFieldsAlgorithm;
import algorithms.PieceType;
import chessComponents.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostFreeFieldsAlgorithmTest {

    @Test
    void heuristicValueTest() {
        MostFreeFieldsAlgorithm algorithm = new MostFreeFieldsAlgorithm(8, PieceType.QUEEN);

        assertEquals(6, algorithm.getHeuristicValueForPosition(new Coordinates(0, 0)));
        assertEquals(5, algorithm.getHeuristicValueForPosition(new Coordinates(1, 3)));
        assertEquals(6, algorithm.getHeuristicValueForPosition(new Coordinates(5, 0)));
        assertEquals(5, algorithm.getHeuristicValueForPosition(new Coordinates(4, 4)));


    }
}
