package chessBoardManager;

import algorithms.PieceType;
import chessComponents.*;
import lombok.Getter;

import java.util.Random;

public class ChessboardManager {
    @Getter
    private Chessboard chessboard;
    private PieceType pieceType;
    private FieldsAttackersNumberUpdater fieldsAttackersNumberUpdater;


    public ChessboardManager(PieceType pieceType, Chessboard chessboard) {
        this.pieceType = pieceType;
        this.chessboard = chessboard;
        fieldsAttackersNumberUpdater = new FieldsAttackersNumberUpdater(chessboard);
    }

    public void fillBoardRandomlyEachRow() {
        Random rand = new Random();

        for (int i = 0; i < chessboard.getSize(); i++) {

            int columnIndex = rand.nextInt(chessboard.getSize());
            chessboard.getBoard()[i][columnIndex].setPiece(createPiece(pieceType));
        }
    }

    public void move(Coordinates from, Coordinates to) {
        chessboard.getFieldByCoordinates(from).removePiece();
        fieldsAttackersNumberUpdater.updateAttackersNumber(from, -1, pieceType);
        chessboard.getFieldByCoordinates(to).setPiece(createPiece(pieceType));
        fieldsAttackersNumberUpdater.updateAttackersNumber(to, 1, pieceType);
    }

    private Piece createPiece(PieceType pieceType) {
        if (pieceType == PieceType.QUEEN)
            return new Queen();
        else
            return new Rook();
    }


}
