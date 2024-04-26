package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTests {

    private Chessboard chessboard;

    @BeforeEach
    public void setUp() {
        chessboard = new ChessboardImpl();
    }

    @Test
    public void testMovePawnBackwardsShouldBeIllegal() {
        //Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        //Assert
        assertFalse(pawn.canMove(chessboard, new Square("e1")));

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        //System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMovePawnSidewaysShouldBeIllegal() {
        //Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        //Assert
        assertFalse(pawn.canMove(chessboard, new Square("d2")));
    }

    @Test
    public void testMovePawnOneStepForward() {
        //Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        //Assert
        assertTrue(pawn.canMove(chessboard, new Square("e3")));
    }

    @Test
    public void testMovePawnTwoStepsForwardFirstTime() {
        //Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        //Assert
        assertTrue(pawn.canMove(chessboard, new Square("e4")));
    }

    @ParameterizedTest
    @MethodSource("forwardSquareProvider")
    public void testMovePawnMoreThanOneStepForwardIllegal(Square destination) {
        // Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e3"));
        chessboard.addPiece(pawn);

        // Assert
        assertFalse(pawn.canMove(chessboard, destination));
    }

    @Test
    public void testMovePawnForwardToOccupiedSquareIllegal() {
        //Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        Pawn opponentPawn = new Pawn(Color.BLACK, new Square("e3"));
        chessboard.addPiece(opponentPawn);

        //Assert
        assertFalse(pawn.canMove(chessboard, new Square("e3")));
    }

    @ParameterizedTest
    @MethodSource("diagonalSquareProvider")
    public void testMovePawnDiagonallyToEmptySquareIllegal(Square destination) {
        // Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        // Assert
        assertFalse(pawn.canMove(chessboard, destination));
    }

    @Test
    public void testMovePawnDiagonallyToOccupiedSquareOneStepLeft() {
        // Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        Pawn opponentPawn = new Pawn(Color.BLACK, new Square("d3"));
        chessboard.addPiece(opponentPawn);

        // Assert
        assertTrue(pawn.canMove(chessboard, opponentPawn.getLocation()));
    }

    @Test
    public void testMovePawnDiagonallyToOccupiedSquareOneStepRight() {
        // Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        Pawn opponentPawn = new Pawn(Color.BLACK, new Square("f3"));
        chessboard.addPiece(opponentPawn);

        // Assert
        assertTrue(pawn.canMove(chessboard, opponentPawn.getLocation()));
    }

    @Test
    public void testMovePawnDiagonallyToOccupiedSquareTwoStepsIllegal() {
        // Arrange
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        Pawn opponentPawn = new Pawn(Color.BLACK, new Square("c4"));
        chessboard.addPiece(opponentPawn);

        // Assert
        assertFalse(pawn.canMove(chessboard, opponentPawn.getLocation()));
    }







    // Method to provide parameters for forward movements
    private static Stream<Square> forwardSquareProvider() {
        String[] forwardAlgebraicPositions = {"e5", "e6", "e7", "e8"};
        return squareProvider(forwardAlgebraicPositions);
    }

    // Method to provide parameters for diagonal movements
    private static Stream<Square> diagonalSquareProvider() {
        String[] diagonalAlgebraicPositions = {"d3","c4","b5","a6","f3","g4","h5"};
        return squareProvider(diagonalAlgebraicPositions);
    }

    // Method to provide parameters
    private static Stream<Square> squareProvider(String[] algebraicPositions) {
        return Stream.of(algebraicPositions)
                .map(Square::new);
    }
}
