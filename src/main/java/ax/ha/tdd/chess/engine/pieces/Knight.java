package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Knight extends ChessPieceBase implements ChessPiece{
    public Knight(Color player, Square location) {
        super(PieceType.KNIGHT, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        if(this.moveIsIllegal(chessboard,destination)){
            return false;
        }
        int currentX = this.getLocation().getX();
        int currentY = this.getLocation().getY();
        int destX = destination.getX();
        int destY = destination.getY();
        return (Math.abs(destX-currentX)==1 && Math.abs(destY-currentY)==2) ||
                (Math.abs(destX-currentX)==2 && Math.abs(destY-currentY)==1);
    }
}
