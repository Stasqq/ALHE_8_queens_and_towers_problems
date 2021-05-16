package chessComponents;

import algorithms.PieceType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;


//todo move this to the algorithm class if you need this. It's not board think to know if you have used all pieces
//        private ArrayList<Piece> freePieces;
//        private ArrayList<Piece> usedPieces;
//        if (pieceType == PieceType.QUEEN) {
//            for (int i = 0; i < n; i++) {
//                freePieces.add(new Queen());
//            }
//        } else {
//            for (int i = 0; i < n; i++) {
//                freePieces.add(new Rook());
//            }
//        }

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


}
