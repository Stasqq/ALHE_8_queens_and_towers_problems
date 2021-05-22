package chessBoardManager;

import algorithms.PieceType;
import chessComponents.Chessboard;
import chessComponents.Coordinates;
import chessComponents.Field;

public class FieldsAttackersNumberUpdater {
    public final int SET_PIECE = 1;
    public final int REMOVE_PIECE = -1;

    private Chessboard chessboard;

    public FieldsAttackersNumberUpdater(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void updateAttackersNumber(Coordinates coordinates, int update, PieceType pieceType) {
        updateColumnAndRow(coordinates, update);
        if (pieceType == PieceType.QUEEN)
            updateDiagonals(coordinates, update);
    }


    private void updateColumnAndRow(Coordinates coordinates, int update) {
        updateRow(coordinates, update);
        updateColumn(coordinates, update);
    }

    private void updateRow(Coordinates coordinates, int update) {
        for (int i = 0; i < chessboard.getSize(); i++) {
            if (i != coordinates.getColumn())
                updateField(coordinates.getRow(), i, update);
        }
    }

    private void updateColumn(Coordinates coordinates, int update) {
        for (int i = 0; i < chessboard.getSize(); i++) {
            if (i != coordinates.getRow())
                updateField(i, coordinates.getColumn(), update);
        }
    }

    private void updateDiagonals(Coordinates coordinates, int update) {
        // left down
        for (int x = coordinates.getRow() - 1, y = coordinates.getColumn() - 1; Coordinates.inBounds(x, y, chessboard.getSize()); x--, y--) {
            updateField(x, y, update);
        }
        // right down
        for (int x = coordinates.getRow() - 1, y = coordinates.getColumn() + 1; Coordinates.inBounds(x, y, chessboard.getSize()); x--, y++) {
            updateField(x, y, update);
        }
        // left up
        for (int x = coordinates.getRow() + 1, y = coordinates.getColumn() - 1; Coordinates.inBounds(x, y, chessboard.getSize()); x++, y--) {
            updateField(x, y, update);
        }
        //  right up
        for (int x = coordinates.getRow() + 1, y = coordinates.getColumn() + 1; Coordinates.inBounds(x, y, chessboard.getSize()); x++, y++) {
            updateField(x, y, update);
        }
    }

    private void updateField(int x, int y, int update) {
        Field field = chessboard.getBoard()[x][y];
        field.updateAttackersNumber(field.getAttackersNumber() + update);
    }
}
