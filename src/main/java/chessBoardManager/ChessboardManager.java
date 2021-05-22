package chessBoardManager;

import algorithms.PieceType;
import chessComponents.*;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Random;

public class ChessboardManager {
    @Getter
    private Chessboard chessboard;
    private PieceType pieceType;
    private FieldsAttackersNumberUpdater fieldsAttackersNumberUpdater;
    private PreviousColumnsAttacksChecker previousColumnsAttacksChecker;


    public ChessboardManager(PieceType pieceType, Chessboard chessboard) {
        this.pieceType = pieceType;
        this.chessboard = chessboard;
        fieldsAttackersNumberUpdater = new FieldsAttackersNumberUpdater(chessboard);
        previousColumnsAttacksChecker = new PreviousColumnsAttacksChecker(chessboard);
    }

    public void fillBoardRandomlyEachRow() {
        Random rand = new Random();

        for (int i = 0; i < chessboard.getSize(); i++) {

            int columnIndex = rand.nextInt(chessboard.getSize());
            chessboard.getBoard()[i][columnIndex].setPiece(createPiece(pieceType));
        }
    }

    public void fillBoardOnePiecePerColumnAndRow() {
        Random rand = new Random();

        LinkedList<Integer> columnNumbers = new LinkedList<>();
        for(int i=0; i<chessboard.getSize();i++)
            columnNumbers.add(i);

        for(int i=0; i<chessboard.getSize();i++) {
            int columnIndex = columnNumbers.get(rand.nextInt(columnNumbers.size()));
            columnNumbers.remove(Integer.valueOf(columnIndex));
            chessboard.getBoard()[i][columnIndex].setPiece(createPiece(pieceType));
            fieldsAttackersNumberUpdater.updateAttackersNumber(new Coordinates(i,columnIndex), fieldsAttackersNumberUpdater.SET_PIECE, pieceType);
        }
    }

    public void move(Coordinates from, Coordinates to) {
        chessboard.getFieldByCoordinates(from).removePiece();
        fieldsAttackersNumberUpdater.updateAttackersNumber(from, fieldsAttackersNumberUpdater.REMOVE_PIECE, pieceType);
        chessboard.getFieldByCoordinates(to).setPiece(createPiece(pieceType));
        fieldsAttackersNumberUpdater.updateAttackersNumber(to, fieldsAttackersNumberUpdater.SET_PIECE, pieceType);
    }

    private Piece createPiece(PieceType pieceType) {
        if (pieceType == PieceType.QUEEN)
            return new Queen();
        else
            return new Rook();
    }

    public void clearChessboard() {
        for(int i=0; i<chessboard.getSize(); i++){
            for(int j=0; j< chessboard.getSize(); j++){
                Field currentField = chessboard.getBoard()[i][j];
                if(!currentField.isFree())
                    currentField.removePiece();
                currentField.updateAttackersNumber(0);
            }
        }
    }

    public boolean arePreviousColumnsAttacked(int columnNumber) {
        return previousColumnsAttacksChecker.arePreviousColumnsAttacked(columnNumber, pieceType);
    }

    public boolean arePreviousColumnsAttackedAfterReplace(int oldColumnNumber,int testColumnNumber){
        return previousColumnsAttacksChecker.arePreviousColumnsAttackedAfterReplace(oldColumnNumber, testColumnNumber, pieceType);
    }
}
