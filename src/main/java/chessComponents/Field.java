package chessComponents;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class Field {

    private int attackersNumber;

    private Piece piece;

    public void removePiece() {
        piece = null;
    }

    public void updateAttackersNumber(int count) {
        attackersNumber = Math.max(count, 0);
    }
}
