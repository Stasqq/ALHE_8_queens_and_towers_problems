import algorithms.PieceType;
import chessBoardManager.ChessboardManager;
import chessBoardManager.FieldsAttackersNumberUpdater;
import chessComponents.Chessboard;
import chessComponents.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessboardManagerTest {

    @Test
    void onePieceEachRowAndColumn() {
        ChessboardManager chessboardManager = new ChessboardManager(PieceType.ROOK, new Chessboard(8));
        chessboardManager.fillBoardOnePiecePerColumnAndRow();

        Field[][] board = chessboardManager.getChessboard().getBoard();

        for(Field[] column : board){
            for(Field field : column){
                //ROOK pieces so in every free field should be attacked by 2 rooks(from column and from row)
                if(field.isFree())
                    assertEquals(2, field.getAttackersNumber());
            }
        }
    }
}
