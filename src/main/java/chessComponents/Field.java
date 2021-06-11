package chessComponents;


public class Field {

    private int attackersNumber;

    private Piece piece;

    public void removePiece() {
        piece = null;
    }

    public void updateAttackersNumber(int count) {
        attackersNumber = Math.max(count, 0);
    }

    public boolean isFree() { return piece == null; }

    public int getAttackersNumber() {
        return attackersNumber;
    }

    public void setAttackersNumber(int attackersNumber) {
        this.attackersNumber = attackersNumber;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
