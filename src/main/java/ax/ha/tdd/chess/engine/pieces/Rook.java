package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Rook extends ChessPieceBase implements ChessPiece{
    public Rook(Color player, Square location) {
        super(PieceType.ROOK, player, location);
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

        // Can't move both vertically and horizontally
        if (currentX != destX && currentY != destY) {
            return false;
        }

        // Horizontal movement
        if (currentX != destX) {
            int stepX = Integer.compare(destX, currentX);
            for (int i = 1; i < Math.abs(destX - currentX); i++) {
                if (cantPass(chessboard, new Square(currentX + i * stepX, currentY))) {
                    return false;
                }
            }
        }

        // Vertical movement
        if (currentY != destY) {
            int stepY = Integer.compare(destY, currentY);
            for (int i = 1; i < Math.abs(destY - currentY); i++) {
                if (cantPass(chessboard, new Square(currentX, currentY + i * stepY))) {
                    return false;
                }
            }
        }

        return true;
    }
}
