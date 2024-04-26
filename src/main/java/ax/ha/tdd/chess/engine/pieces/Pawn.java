package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

public class Pawn extends ChessPieceBase implements ChessPiece{

    public Pawn(Color player, Square location) {
        super(PieceType.PAWN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        //WHITE
        if (this.color.equals(Color.WHITE)) {
            //Not backward or sideways
            if (destination.getY() >= this.location.getY()) {
                return false;
            }
            //Forward movement
            if(destination.getX()==this.location.getX()) {
                //Not forward to occupied square
                if (chessboard.getPieceAt(destination) != null) {
                    return false;
                }
                //No more than 2 steps first time
                else if (this.location.getY() == 6) {
                    return destination.getY() >= 4;
                }
                //No more than 1 step the rest of the time
                else return (this.location.getY() - destination.getY() == 1);
            }
            //Diagonal movement
            else{
                //Only to occupied square
                if(chessboard.getPieceAt(destination)==null){
                    return false;
                }
                //Only one step
                else return (Math.abs(this.location.getX() - destination.getX()) == 1);
            }
        }

        //BLACK
        else{
            //Not backward or sideways
            if (destination.getY() <= this.location.getY()) {
                return false;
            }
            //Forward movement
            if(destination.getX()==this.location.getX()) {
                //Not forward to occupied square
                if (chessboard.getPieceAt(destination) != null) {
                    return false;
                }
                //No more than 2 steps first time
                else if (this.location.getY() == 1) {
                    return destination.getY() <= 3;
                }
                //No more than 1 step the rest of the time
                else return (this.location.getY() - destination.getY() == -1);
            }
            //Diagonal movement
            else{
                //Only to occupied square
                if(chessboard.getPieceAt(destination)==null){
                    return false;
                }
                //Only one step
                else return (Math.abs(this.location.getX() - destination.getX()) == 1);
            }
        }
    }
}
