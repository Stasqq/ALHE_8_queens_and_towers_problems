package chessBoardManager;

import algorithms.PieceType;
import chessComponents.*;
import lombok.Getter;
import randomSeed.RandomSeed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ChessboardManager {
    @Getter
    private Chessboard chessboard;
    @Getter
    private PieceType pieceType;
    private FieldsAttackersNumberUpdater fieldsAttackersNumberUpdater;
    private PreviousColumnsAttacksChecker previousColumnsAttacksChecker;


    public ChessboardManager(PieceType pieceType, Chessboard chessboard) {
        this.pieceType = pieceType;
        this.chessboard = chessboard;
        fieldsAttackersNumberUpdater = new FieldsAttackersNumberUpdater(chessboard);
        previousColumnsAttacksChecker = new PreviousColumnsAttacksChecker(chessboard);
    }


    public void fillBoardOnePiecePerColumnAndRow() {
        Random rand = new Random(RandomSeed.RANDOM_SEED);

        LinkedList<Integer> columnNumbers = new LinkedList<>();
        for (int i = 0; i < chessboard.getSize(); i++)
            columnNumbers.add(i);

        for (int i = 0; i < chessboard.getSize(); i++) {
            int columnIndex = columnNumbers.get(rand.nextInt(columnNumbers.size()));
            columnNumbers.remove(Integer.valueOf(columnIndex));
            chessboard.getBoard()[i][columnIndex].setPiece(createPiece(pieceType));
            fieldsAttackersNumberUpdater.updateAttackersNumber(new Coordinates(i, columnIndex), fieldsAttackersNumberUpdater.SET_PIECE, pieceType);
        }
    }

    public void fillBoardOnePiecePerColumn() {
        Random rand = new Random(RandomSeed.RANDOM_SEED);
        for(int i=0; i< chessboard.getSize(); i++) {
            chessboard.getBoard()[i][rand.nextInt(chessboard.getSize())].setPiece(createPiece(pieceType));
        }
    }

    public void setPiece(Coordinates coords) {
        chessboard.getFieldByCoordinates(coords).setPiece(createPiece(pieceType));
        fieldsAttackersNumberUpdater.updateAttackersNumber(coords, fieldsAttackersNumberUpdater.SET_PIECE, pieceType);
    }

    public void removePiece(Coordinates coords) {
        chessboard.getFieldByCoordinates(coords).removePiece();
        fieldsAttackersNumberUpdater.updateAttackersNumber(coords, fieldsAttackersNumberUpdater.REMOVE_PIECE
                , pieceType);
    }

    private Piece createPiece(PieceType pieceType) {
        if (pieceType == PieceType.QUEEN)
            return new Queen();
        else
            return new Rook();
    }

    public void clearChessboard() {
        for (int i = 0; i < chessboard.getSize(); i++) {
            for (int j = 0; j < chessboard.getSize(); j++) {
                Field currentField = chessboard.getBoard()[i][j];
                if (!currentField.isFree())
                    currentField.removePiece();
                currentField.updateAttackersNumber(0);
            }
        }
    }

    public boolean arePreviousColumnsAttacked(int columnNumber) {
        return previousColumnsAttacksChecker.arePreviousColumnsAttacked(columnNumber, pieceType);
    }

    public boolean arePreviousColumnsAttackedAfterReplace(int oldColumnNumber, int testColumnNumber) {
        return previousColumnsAttacksChecker.arePreviousColumnsAttackedAfterReplace(oldColumnNumber, testColumnNumber, pieceType);
    }

    public int getPiecesAttacksNumber() {
        ArrayList<Piece> pieces = createPiecesList();
        int attacks = 0;

        for (int i = 0; i < chessboard.getSize(); i++) {
            for (int j = 0; j < chessboard.getSize(); j++) {
                if (i==j) continue;
                if(pieceType == PieceType.QUEEN) {
                    if (pieces.get(i).getX() == pieces.get(j).getX() // same row
                            || pieces.get(i).getY() == pieces.get(j).getY() //same column
                            || (pieces.get(i).getX() - pieces.get(j).getX() == pieces.get(i).getY() - pieces.get(j).getY()) // same diagonal
                            || (pieces.get(i).getX() - pieces.get(j).getX() == pieces.get(j).getY() - pieces.get(i).getY())) { //same counter diagonal
                        attacks++;
                    }
                }
                else if(pieceType == PieceType.ROOK) {
                    if (pieces.get(i).getX() == pieces.get(j).getX() // same row
                            || pieces.get(i).getY() == pieces.get(j).getY()) { //same column)
                        attacks++;
                    }
                }
            }
        }
        //this is due to double counting
        return attacks / 2;
    }

    public ArrayList<Piece> createPiecesList() {
        ArrayList<Piece> pieces = new ArrayList<>();
        for(int i=0; i<chessboard.getSize(); i++) {
            for(int j=0; j<chessboard.getSize(); j++) {
                if(!chessboard.getBoard()[i][j].isFree()){
                    chessboard.getBoard()[i][j].getPiece().setX(i);
                    chessboard.getBoard()[i][j].getPiece().setY(j);
                    pieces.add(chessboard.getBoard()[i][j].getPiece());
                }
            }
        }
        return pieces;
    }
}
