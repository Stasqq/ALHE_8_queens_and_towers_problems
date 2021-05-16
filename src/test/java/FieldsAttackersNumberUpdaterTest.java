
import algorithms.PieceType;
import chessBoardManager.FieldsAttackersNumberUpdater;
import chessComponents.Chessboard;
import chessComponents.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldsAttackersNumberUpdaterTest {
    private FieldsAttackersNumberUpdater fieldsAttackersNumberUpdater;
    private int boardSize = 10;
    private Chessboard chessboard;


    @BeforeEach
    void setUp() {
        chessboard = new Chessboard(boardSize);
        fieldsAttackersNumberUpdater = new FieldsAttackersNumberUpdater(chessboard);
    }

    @Test
    void increaseAttackersNumberRookCorner() {
        fieldsAttackersNumberUpdater.updateAttackersNumber(new Coordinates(0, 0), 1, PieceType.ROOK);

        for (int i = 0; i < boardSize; i++) {
            if (i != 0) {
                assertEquals(1, chessboard.getBoard()[0][i].getAttackersNumber());
                assertEquals(chessboard.getBoard()[i][0].getAttackersNumber(), 1);
            }
        }

    }

    @Test
    void increaseAttackersNumberRookCenter() {
        fieldsAttackersNumberUpdater.updateAttackersNumber(new Coordinates(5, 5), 1, PieceType.ROOK);

        for (int i = 0; i < boardSize; i++) {
            if (i != 5) {
                assertEquals(1, chessboard.getBoard()[5][i].getAttackersNumber());
                assertEquals(chessboard.getBoard()[i][5].getAttackersNumber(), 1);
            }
        }

    }

    @Test
    void increaseAttackersNumberQueenCorner() {
        fieldsAttackersNumberUpdater.updateAttackersNumber(new Coordinates(0, 0), 1, PieceType.QUEEN);

        for (int i = 1; i < boardSize; i++) {
            assertEquals(1, chessboard.getBoard()[0][i].getAttackersNumber());
            assertEquals(1, chessboard.getBoard()[i][0].getAttackersNumber());
        }
        for (int i = 1; i < boardSize; i++) {
            assertEquals(1, chessboard.getBoard()[i][i].getAttackersNumber());
        }

    }

    @Test
    void increaseAttackersNumberQueenCenter() {
        fieldsAttackersNumberUpdater.updateAttackersNumber(new Coordinates(5, 5), 1, PieceType.QUEEN);

        for (int i = 0; i < boardSize; i++) {
            if (i != 5) {
                assertEquals(1, chessboard.getBoard()[5][i].getAttackersNumber());
                assertEquals(1, chessboard.getBoard()[i][5].getAttackersNumber());
            }
        }
        for (int x = 5 - 1, y = 5 - 1; Coordinates.inBounds(x, y, chessboard.getSize()); x--, y--) {
            assertEquals(chessboard.getBoard()[x][y].getAttackersNumber(), 1);
        }
        // right down
        for (int x = 5 - 1, y = 5 + 1; Coordinates.inBounds(x, y, chessboard.getSize()); x--, y++) {
            assertEquals(chessboard.getBoard()[x][y].getAttackersNumber(), 1);
        }
        // left up
        for (int x = 5 + 1, y = 5 - 1; Coordinates.inBounds(x, y, chessboard.getSize()); x++, y--) {
            assertEquals(chessboard.getBoard()[x][y].getAttackersNumber(), 1);
        }
        //  right up
        for (int x = 5 + 1, y = 5 + 1; Coordinates.inBounds(x, y, chessboard.getSize()); x++, y++) {
            assertEquals(chessboard.getBoard()[x][y].getAttackersNumber(), 1);
        }
    }
}
