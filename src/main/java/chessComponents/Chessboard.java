package chessComponents;

import algorithms.PieceType;

import java.util.ArrayList;

public class Chessboard {
    private Field[][] board;
    private ArrayList<Piece> freePieces;
    private ArrayList<Piece> usedPieces;

    public Chessboard(int n, PieceType pieceType) {
        board = new Field[n][n];
        usedPieces = new ArrayList<>();
        freePieces = new ArrayList<>();

        if(pieceType == PieceType.QUEEN){
            for(int i=0;i<n;i++){
                freePieces.add(new Queen());
            }
        }
        else {
            for(int i=0;i<n;i++){
                freePieces.add(new Rook());
            }
        }
    }

    public boolean arePiecesLeft() {
        return freePieces.size() != 0;
    }
}
