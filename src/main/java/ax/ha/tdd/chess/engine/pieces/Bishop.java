package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Bishop extends ChessPieceBase implements ChessPiece{
    public Bishop(Color player, Square location) {
        super(PieceType.BISHOP, player, location);
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

        //Can only move diagonally!
        if(Math.abs(currentX-destX)!=Math.abs(currentY-destY)){
            return false;
        }

        else{
            int stepY=Integer.compare(destY,currentY);
            int stepX=Integer.compare(destX,currentX);
            for(int i = 1; i<Math.abs(destX-currentX); i++){
                if (cantPass(chessboard, new Square(currentX+ i *stepX, currentY + i * stepY))) {
                    return false;
                }
            }
        }

        return true;
    }
}
