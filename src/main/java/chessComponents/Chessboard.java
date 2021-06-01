package chessComponents;

import lombok.Getter;

public class Chessboard {
    @Getter
    private Field[][] board;


    public Chessboard(int n) {
        board = new Field[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Field();
            }
        }
    }

    public Field getFieldByCoordinates(Coordinates coordinates) {
        return board[coordinates.getRow()][coordinates.getColumn()];
    }

    public int getSize() {
        return board.length;
    }

    public void print() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (board[i][j].isFree())
                    System.out.print(" - ");
                else
                    System.out.print(" X ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();


    }
}
