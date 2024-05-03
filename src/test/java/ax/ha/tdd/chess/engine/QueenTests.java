package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTests {

    private Chessboard chessboard;

    @BeforeEach
    public void setUp() {
        chessboard = new ChessboardImpl();
    }

    @ParameterizedTest
    @MethodSource("horizontalSquareProvider1")
    //destination = {"a5","b5", "c5", "e5", "f5", "g5", "h5"}
    public void testQueenCanMoveHorizontally(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);

        //Assert
        assertTrue(queen.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("verticalSquareProvider1")
    //destination = {"d1", "d2", "d3", "d4", "d6", "d7", "d8"}
    public void testQueenCanMoveVertically(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);

        //Assert
        assertTrue(queen.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider1")
    //destination = {"a2", "b3", "c4", "e6", "f7", "g8"}
    public void testQueenCanMoveDiagonally1(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);

        //Assert
        assertTrue(queen.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider2")
    //destination = {"a8", "b7", "c6", "e4", "f3", "g2", "h1"}
    public void testQueenCanMoveDiagonally2(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);

        //Assert
        assertTrue(queen.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("randomSquareProvider")
    //destination = {"a7", "b4", "c8", "e2", "f6", "g1", "h3"}
    public void testQueenCantMoveRandomly(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);

        //Assert
        assertFalse(queen.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsSquareProvider")
    //destination = {"a8", "b7", "f3", "g2", "h1", "a2", "b3", "f7", "g8", "a5", "b5", "f5", "g5", "h5",
    //                "d8", "d7", "d3", "d2", "d1"}
    public void testQueenCantMovePastPiece(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);

        Queen queen1 = new Queen(Color.WHITE, new Square("c6"));
        chessboard.addPiece(queen1);
        Queen queen2 = new Queen(Color.WHITE, new Square("d6"));
        chessboard.addPiece(queen2);
        Queen queen3 = new Queen(Color.WHITE, new Square("e6"));
        chessboard.addPiece(queen3);
        Queen queen4 = new Queen(Color.WHITE, new Square("e5"));
        chessboard.addPiece(queen4);

        Queen queen5 = new Queen(Color.BLACK, new Square("c5"));
        chessboard.addPiece(queen5);
        Queen queen6 = new Queen(Color.BLACK, new Square("c4"));
        chessboard.addPiece(queen6);
        Queen queen7 = new Queen(Color.BLACK, new Square("d4"));
        chessboard.addPiece(queen7);
        Queen queen8 = new Queen(Color.BLACK, new Square("e4"));
        chessboard.addPiece(queen8);

        //Assert
        assertFalse(queen.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsSquareProvider2")
    //destination = {"b7", "d7", "f7", "f5", "f3", "d3", "b3", "b5"}
    public void testQueenCantEatOwn(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);
        Queen queen2 = new Queen(Color.WHITE, destination);
        chessboard.addPiece(queen2);

        //Assert
        assertFalse(queen.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsSquareProvider2")
    //destination = {"b7", "d7", "f7", "f5", "f3", "d3", "b3", "b5"}
    public void testQueenCanEatOpponent(Square destination){
        //Arrange
        Queen queen = new Queen(Color.WHITE, new Square("d5"));
        chessboard.addPiece(queen);
        Queen queen2 = new Queen(Color.BLACK, destination);
        chessboard.addPiece(queen2);

        //Assert
        assertTrue(queen.canMove(chessboard, destination));
    }



    // Method to provide parameters for horizontal movements
    private static Stream<Square> horizontalSquareProvider1() {
        String[] forwardAlgebraicPositions = {"a5","b5", "c5", "e5", "f5", "g5", "h5"};
        return squareProvider(forwardAlgebraicPositions);
    }

    // Method to provide parameters for forward movements
    private static Stream<Square> verticalSquareProvider1() {
        String[] forwardAlgebraicPositions = {"d1", "d2", "d3", "d4", "d6", "d7", "d8"};
        return squareProvider(forwardAlgebraicPositions);
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

    // Method to provide parameters for random movements
    private static Stream<Square> randomSquareProvider() {
        String[] positions = {"a7", "b4", "c8", "e2", "f6", "g1", "h3"};
        return squareProvider(positions);
    }

    // Method to provide parameters for all movements
    private static Stream<Square> allDirectionsSquareProvider() {
        String[] positions = {"a8", "b7", "f3", "g2", "h1", "a2", "b3", "f7", "g8", "a5", "b5", "f5", "g5", "h5",
                "d8", "d7", "d3", "d2", "d1"};
        return squareProvider(positions);
    }

    // Method to provide parameters for all movements
    private static Stream<Square> allDirectionsSquareProvider2() {
        String[] positions = {"b7", "d7", "f7", "f5", "f3", "d3", "b3", "b5"};
        return squareProvider(positions);
    }

    // Method to provide parameters
    private static Stream<Square> squareProvider(String[] algebraicPositions) {
        return Stream.of(algebraicPositions)
                .map(Square::new);
    }
}
