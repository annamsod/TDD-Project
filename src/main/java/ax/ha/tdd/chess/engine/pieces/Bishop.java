package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Movement;

public class Bishop extends ChessPieceBase implements ChessPiece{
    public Bishop(Color player, Square location) {
        super(PieceType.BISHOP, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        if(moveIsIllegal(chessboard,destination)){
            return false;
        }

        return Movement.diagonalMovement(chessboard, destination, this);

    }
}
