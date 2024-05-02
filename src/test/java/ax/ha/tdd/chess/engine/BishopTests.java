package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Bishop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTests {

    private Chessboard chessboard;

    @BeforeEach
    public void setUp() {
        chessboard = new ChessboardImpl();
    }


    @ParameterizedTest
    @MethodSource("horizontalSquareProvider1")
    //destination = {"a5", "b5", "c5", "e5", "f5", "g5", "h5"}
    public void testBishopCantMoveHorizontally(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);

        //Assert
        assertFalse(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("verticalSquareProvider1")
    //destination = {"d1", "d2", "d3", "d4", "d6", "d7", "d8"}
    public void testBishopCantMoveVertically(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);

        //Assert
        assertFalse(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider1")
    //destination = {"a2", "b3", "c4", "e6", "f7", "g8"}
    public void testBishopCanMoveDiagonally1(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);

        //Assert
        assertTrue(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider2")
    //destination = {"a8", "b7", "c6", "e4", "f3", "g2", "h1"}
    public void testBishopCanMoveDiagonally2(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);

        //Assert
        assertTrue(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("randomSquareProvider")
    //destination = {"a7", "b4", "c8", "e2", "f6", "g1", "h3"}
    public void testBishopCantMoveRandomly(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);

        //Assert
        assertFalse(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider3")
    //destination = {"a8", "b7", "a2", "b3", "f3", "g2", "h1", "f7", "g8"}
    public void testBishopCantMovePastOwn(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);
        Bishop bishop1 = new Bishop(Color.WHITE,new Square("c6"));
        chessboard.addPiece(bishop1);
        Bishop bishop2 = new Bishop(Color.WHITE,new Square("e6"));
        chessboard.addPiece(bishop2);
        Bishop bishop3 = new Bishop(Color.WHITE,new Square("c4"));
        chessboard.addPiece(bishop3);
        Bishop bishop4 = new Bishop(Color.WHITE,new Square("e4"));
        chessboard.addPiece(bishop4);

        //Assert
        assertFalse(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider3")
    //destination = {"a8", "b7", "a2", "b3", "f3", "g2", "h1", "f7", "g8"}
    public void testBishopCantMovePastOpponent(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);
        Bishop bishop1 = new Bishop(Color.BLACK,new Square("c6"));
        chessboard.addPiece(bishop1);
        Bishop bishop2 = new Bishop(Color.BLACK,new Square("e6"));
        chessboard.addPiece(bishop2);
        Bishop bishop3 = new Bishop(Color.BLACK,new Square("c4"));
        chessboard.addPiece(bishop3);
        Bishop bishop4 = new Bishop(Color.BLACK,new Square("e4"));
        chessboard.addPiece(bishop4);

        //Assert
        assertFalse(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider3")
    //destination = {"a8", "b7", "a2", "b3", "f3", "g2", "h1", "f7", "g8"}
    public void testBishopCantEatOwn(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);
        Bishop bishop1 = new Bishop(Color.WHITE, destination);
        chessboard.addPiece(bishop1);

        //Assert
        assertFalse(bishop.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider3")
    //destination = {"a8", "b7", "a2", "b3", "f3", "g2", "h1", "f7", "g8"}
    public void testBishopCanEatOpponent(Square destination){
        //Arrange
        Bishop bishop = new Bishop(Color.WHITE,new Square("d5"));
        chessboard.addPiece(bishop);
        Bishop bishop1 = new Bishop(Color.BLACK, destination);
        chessboard.addPiece(bishop1);

        //Assert
        assertTrue(bishop.canMove(chessboard, destination));
    }



    // Method to provide parameters for horizontal movements
    private static Stream<Square> horizontalSquareProvider1() {
        String[] positions = {"a5", "b5", "c5", "e5", "f5", "g5", "h5"};
        return squareProvider(positions);
    }

    // Method to provide parameters for vertical movements
    private static Stream<Square> verticalSquareProvider1() {
        String[] positions = {"d1", "d2", "d3", "d4", "d6", "d7", "d8"};
        return squareProvider(positions);
    }

    // Method to provide parameters for diagonal movements
    private static Stream<Square> diagonalSquareProvider1() {
        String[] positions = {"a2", "b3", "c4", "e6", "f7", "g8"};
        return squareProvider(positions);
    }

    // Method to provide parameters for diagonal movements
    private static Stream<Square> diagonalSquareProvider2() {
        String[] positions = {"a8", "b7", "c6", "e4", "f3", "g2", "h1"};
        return squareProvider(positions);
    }

    // Method to provide parameters for diagonal movements
    private static Stream<Square> diagonalSquareProvider3() {
        String[] positions = {"a8", "b7", "a2", "b3", "f3", "g2", "h1", "f7", "g8"};
        return squareProvider(positions);
    }

    // Method to provide parameters for random movements
    private static Stream<Square> randomSquareProvider() {
        String[] positions = {"a7", "b4", "c8", "e2", "f6", "g1", "h3"};
        return squareProvider(positions);
    }

    // Method to provide parameters
    private static Stream<Square> squareProvider(String[] algebraicPositions) {
        return Stream.of(algebraicPositions)
                .map(Square::new);
    }
}
