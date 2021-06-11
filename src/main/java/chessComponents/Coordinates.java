package chessComponents;

public class Coordinates {
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Boolean inBounds(int row, int column, int size) {
        return row >= 0 && row < size && column >= 0 && column < size;
    }

    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
