package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTests {
    private Chessboard chessboard;

    @BeforeEach
    public void setUp() {
        chessboard = new ChessboardImpl();
    }

    @ParameterizedTest
    @MethodSource("allDirectionsProvider")
    public void testCantMoveBothHorizontalAndVertical(Square destination){
        Rook rook = new Rook(Color.WHITE, new Square("b6"));
        chessboard.addPiece(rook);

        assertFalse(rook.canMove(chessboard,destination));
    }

    @ParameterizedTest
    @MethodSource("horizontalSquareProvider1")
    public void testMoveRookHorizontally1(Square destination) {
        //Arrange
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);

        //Assert
        assertTrue(rook.canMove(chessboard, destination));

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        //System.out.println(new ChessboardWriter().print(chessboard));
    }

    @ParameterizedTest
    @MethodSource("verticalSquareProvider1")
    public void testMoveRookVertically1(Square destination) {
        //Arrange
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);

        //Assert
        assertTrue(rook.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("horizontalSquareProvider2")
    public void testMoveRookHorizontally2(Square destination) {
        //Arrange
        Rook rook = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook);

        //Assert
        assertTrue(rook.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("verticalSquareProvider2")
    public void testMoveRookVertically2(Square destination) {
        //Arrange
        Rook rook = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook);

        //Assert
        assertTrue(rook.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsProvider")
    public void testRookCantMovePastOwnPiece(Square destination){
        //Arrange
        Rook rook1 = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook1);
        Square[] pieces ={new Square("b4"),new Square("d2"),new Square("f4"),new Square("d6")};
        for(Square place:pieces) {
            Rook rook = new Rook(Color.WHITE, place);
            chessboard.addPiece(rook);
        }

        //Assert
        assertFalse(rook1.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsProvider")
    public void testRookCantMovePastOpponentPiece(Square destination){
        //Arrange
        Rook rook1 = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook1);
        Square[] pieces ={new Square("b4"),new Square("d2"),new Square("f4"),new Square("d6")};
        for(Square place:pieces) {
            Rook rook = new Rook(Color.BLACK, place);
            chessboard.addPiece(rook);
        }

        //Assert
        assertFalse(rook1.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsProvider")
    public void testRookCantEatOwnPiece(Square destination){
        //Arrange
        Rook rook1 = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook1);
        Rook rook2 = new Rook(Color.WHITE, destination);
        chessboard.addPiece(rook2);

        //Assert
        assertFalse(rook1.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsProvider")
    public void testRookCanEatOpponentPiece(Square destination){
        //Arrange
        Rook rook1 = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook1);
        Rook rook2 = new Rook(Color.BLACK, destination);
        chessboard.addPiece(rook2);

        //Assert
        assertTrue(rook1.canMove(chessboard, destination));
    }

    // Method to provide parameters for forward movements
    private static Stream<Square> horizontalSquareProvider1() {
        String[] forwardAlgebraicPositions = {"b1", "c1", "d1", "e1", "f1", "g1", "h1"};
        return squareProvider(forwardAlgebraicPositions);
    }

    // Method to provide parameters for forward movements
    private static Stream<Square> horizontalSquareProvider2() {
        String[] forwardAlgebraicPositions = {"a4","b4", "c4", "e4", "f4", "g4", "h4"};
        return squareProvider(forwardAlgebraicPositions);
    }

    // Method to provide parameters for forward movements
    private static Stream<Square> verticalSquareProvider1() {
        String[] forwardAlgebraicPositions = {"a2", "a3", "a4", "a5", "a6", "a7", "a8"};
        return squareProvider(forwardAlgebraicPositions);
    }

    // Method to provide parameters for forward movements
    private static Stream<Square> verticalSquareProvider2() {
        String[] forwardAlgebraicPositions = {"d1", "d2", "d3", "d5", "d6", "d7", "d8"};
        return squareProvider(forwardAlgebraicPositions);
    }

    // Method to provide parameters for forward movements
    private static Stream<Square> allDirectionsProvider() {
        String[] forwardAlgebraicPositions = {"a4", "d1", "g4", "d7"};
        return squareProvider(forwardAlgebraicPositions);
    }

    // Method to provide parameters
    private static Stream<Square> squareProvider(String[] algebraicPositions) {
        return Stream.of(algebraicPositions)
                .map(Square::new);
    }
}
