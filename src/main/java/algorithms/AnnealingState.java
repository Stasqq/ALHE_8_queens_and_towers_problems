package algorithms;

import chessBoardManager.ChessboardManager;
import chessComponents.Chessboard;
import chessComponents.Coordinates;
import chessComponents.Piece;

import java.util.ArrayList;
import java.util.Random;

public class AnnealingState {
    private Chessboard chessboard;
    private ChessboardManager chessboardManager;

    public AnnealingState(Chessboard chessboard, ChessboardManager chessboardManager) {
        this.chessboard = chessboard;
        this.chessboardManager = chessboardManager;
    }

    public int getCost() {
        return chessboardManager.getPiecesAttacksNumber();
    }

    public AnnealingState getNextState() {
        Random rand = new Random();
        int changedPieceIndex = rand.nextInt(chessboard.getSize());

        Chessboard newChessboard = new Chessboard(chessboard.getSize());
        ChessboardManager newChessboardManager = new ChessboardManager(chessboardManager.getPieceType(),newChessboard);
        ArrayList<Piece> piecesList = chessboardManager.createPiecesList();
        for(int i=0; i<chessboard.getSize(); i++) {
            if(i == changedPieceIndex) {
                int temp = rand.nextInt(chessboard.getSize());

                while(temp == piecesList.get(i).getY()) {
                    temp = rand.nextInt(chessboard.getSize());
                }
                newChessboardManager.setPiece(new Coordinates(piecesList.get(i).getX(), temp));
            }
            else {
                newChessboardManager.setPiece(new Coordinates(piecesList.get(i).getX(), piecesList.get(i).getY()));
            }
        }

        return new AnnealingState(newChessboard, newChessboardManager);
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public ChessboardManager getChessboardManager() {
        return chessboardManager;
    }
}
