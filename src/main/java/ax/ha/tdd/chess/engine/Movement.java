package ax.ha.tdd.chess.engine;


import ax.ha.tdd.chess.engine.pieces.ChessPieceBase;

public class Movement {
    public static boolean diagonalMovement(Chessboard chessboard, Square destination, ChessPieceBase piece){
        int currentX = piece.getLocation().getX();
        int currentY = piece.getLocation().getY();
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
                if (piece.cantPass(chessboard, new Square(currentX+ i *stepX, currentY + i * stepY))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean parallelMovement(Chessboard chessboard, Square destination, ChessPieceBase piece){
        int currentX = piece.getLocation().getX();
        int currentY = piece.getLocation().getY();
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
                if (piece.cantPass(chessboard, new Square(currentX + i * stepX, currentY))) {
                    return false;
                }
            }
        }

        // Vertical movement
        if (currentY != destY) {
            int stepY = Integer.compare(destY, currentY);
            for (int i = 1; i < Math.abs(destY - currentY); i++) {
                if (piece.cantPass(chessboard, new Square(currentX, currentY + i * stepY))) {
                    return false;
                }
            }
        }

        return true;
    }
}
