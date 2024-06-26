package ax.ha.tdd.chess.engine;

public class GameImpl implements Game{

    final ChessboardImpl board = ChessboardImpl.startingBoard();

    boolean isNewGame = true;
    Color playerInTurn = Color.WHITE;
    Boolean illegalMove = false;

    @Override
    public Color getPlayerToMove() {
        return playerInTurn;
    }

    @Override
    public Chessboard getBoard() {
        return board;
    }

    @Override
    public String getLastMoveResult() {
        //TODO this should be used to show the player what happened
        //Illegal move, correct move, e2 moved to e4 etc. up to you!
        if (isNewGame) {
            return "Game hasn't begun";
        }
        else if(illegalMove){
            return "Illegal move, try again";
        }
        return "Last move was successful, next players turn";
    }

    public static String[] parse(String move){
        return move.split("-");
    }

    private boolean isNotValidSquare(String square) {
        char letter = square.charAt(0);
        int number = Character.digit(square.charAt(1), 10);

        // Check if the letter is between 'a' and 'h' and the number is between 1 and 8
        return (letter < 'a' || letter > 'h' || number < 1 || number > 8);
    }

    @Override
    public void move(String move) {
        //TODO this should trigger your move logic.
        //1. Parse the source and destination of the input "move"
        String [] parts = parse(move);
        if (isNotValidSquare(parts[0]) || isNotValidSquare(parts[1])) {
            illegalMove = true;
            isNewGame = false;
            getLastMoveResult();
            return; // Exit the method if the move is illegal
        }
        Square currentPos = new Square(parts[0]);
        Square destination = new Square(parts[1]);

        //2. Check if the piece is allowed to move to the destination
        //3. If so, update board (and last move message), otherwise only update last move message to show that an illegal move was tried

        if(board.getPieceAt(currentPos)==null || board.getPieceAt(currentPos).getColor()!=playerInTurn ||
                !board.getPieceAt(currentPos).canMove(board,destination)){
            illegalMove=true;
            getLastMoveResult();
        }
        else{
            illegalMove=false;
            getLastMoveResult();

            //Remove eaten piece
            if(board.getPieceAt(destination)!=null){
                board.removePieceAt(destination);
            }

            board.movePiece(board.getPieceAt(currentPos),destination );


            System.out.println("Player tried to perform move: " + move);
            if(playerInTurn==Color.WHITE){
                playerInTurn=Color.BLACK;
            }
            else{
                playerInTurn=Color.WHITE;
            }
        }

    }
}
