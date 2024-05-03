package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Movement;
import ax.ha.tdd.chess.engine.Square;


public class Queen extends ChessPieceBase implements ChessPiece{
    public Queen(Color player, Square location) {
        super(PieceType.QUEEN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        if(moveIsIllegal(chessboard,destination)){
            return false;
        }
        return Movement.parallelMovement(chessboard,destination,this)||Movement.diagonalMovement(chessboard,destination,this);
    }
}
