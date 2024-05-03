package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class King extends ChessPieceBase implements ChessPiece{
    public King(Color player, Square location) {
        super(PieceType.KING, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        if(moveIsIllegal(chessboard,destination)){
            return false;
        }
        int currentX = this.getLocation().getX();
        int currentY = this.getLocation().getY();
        int destX = destination.getX();
        int destY = destination.getY();

        return Math.abs(currentX - destX) <= 1 && Math.abs(currentY - destY) <= 1;
    }
}
