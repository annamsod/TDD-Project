package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Knight;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTests {
    private Chessboard chessboard;

    @BeforeEach
    public void setUp() {
        chessboard = new ChessboardImpl();
    }

    @ParameterizedTest
    @MethodSource("validSquareProvider")
    //destination = "b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"
    public void testKnightCanMoveToValidSquares(Square destination){
        //Arrange
        Knight knight = new Knight(Color.WHITE,new Square("d5"));
        chessboard.addPiece(knight);

        //Assert
        assertTrue(knight.canMove(chessboard,destination));
    }

    @ParameterizedTest
    @MethodSource("allRemainingSquaresProvider")
    public void testKnightCantMoveToAnyOtherSquare(Square destination){
        //Arrange
        Knight knight = new Knight(Color.WHITE,new Square("d5"));
        chessboard.addPiece(knight);

        //Assert
        assertFalse(knight.canMove(chessboard,destination));
    }

    @ParameterizedTest
    @MethodSource("validSquareProvider")
    //destination = "b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"
    public void testKnightCantEatOwn(Square destination){
        //Arrange
        Knight knight = new Knight(Color.WHITE,new Square("d5"));
        chessboard.addPiece(knight);
        Pawn pawn = new Pawn(Color.WHITE, destination);
        chessboard.addPiece(pawn);

        //Assert
        assertFalse(knight.canMove(chessboard,destination));
    }

    @ParameterizedTest
    @MethodSource("validSquareProvider")
    //destination = "b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"
    public void testKnightCanEatOpponent(Square destination){
        //Arrange
        Knight knight = new Knight(Color.WHITE,new Square("d5"));
        chessboard.addPiece(knight);
        Pawn pawn = new Pawn(Color.BLACK, destination);
        chessboard.addPiece(pawn);

        //Assert
        assertTrue(knight.canMove(chessboard,destination));
    }

    @ParameterizedTest
    @MethodSource("validSquareProvider")
    //destination = "b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"
    public void testKnightCanJumpOverOpponent(Square destination){
        //Arrange
        Knight knight = new Knight(Color.WHITE,new Square("d5"));
        chessboard.addPiece(knight);

        Pawn pawn1 = new Pawn(Color.BLACK, new Square("d6"));
        chessboard.addPiece(pawn1);
        Pawn pawn2 = new Pawn(Color.BLACK, new Square("e5"));
        chessboard.addPiece(pawn2);
        Pawn pawn3 = new Pawn(Color.BLACK, new Square("d4"));
        chessboard.addPiece(pawn3);
        Pawn pawn4 = new Pawn(Color.BLACK, new Square("c5"));
        chessboard.addPiece(pawn4);
        //Assert
        assertTrue(knight.canMove(chessboard,destination));
    }

    @ParameterizedTest
    @MethodSource("validSquareProvider")
    //destination = "b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"
    public void testKnightCanJumpOverOwn(Square destination){
        //Arrange
        Knight knight = new Knight(Color.WHITE,new Square("d5"));
        chessboard.addPiece(knight);

        Pawn pawn1 = new Pawn(Color.WHITE, new Square("d6"));
        chessboard.addPiece(pawn1);
        Pawn pawn2 = new Pawn(Color.WHITE, new Square("e5"));
        chessboard.addPiece(pawn2);
        Pawn pawn3 = new Pawn(Color.WHITE, new Square("d4"));
        chessboard.addPiece(pawn3);
        Pawn pawn4 = new Pawn(Color.WHITE, new Square("c5"));
        chessboard.addPiece(pawn4);
        //Assert
        assertTrue(knight.canMove(chessboard,destination));
    }


    // Method to provide parameters for all movements
    private static Stream<Square> validSquareProvider() {
        String[] positions = {"b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"};
        return squareProvider(positions);
    }

    //Method to provide the rest of the places on the board
    private static Stream<Square> allRemainingSquaresProvider() {
        List<String> positions = new ArrayList<>();

        for (char letter = 'a'; letter <= 'h'; letter++) {
            for (int number = 1; number <= 8; number++) {
                String position = "" + letter + number;
                positions.add(position);
            }
        }

        String[] excludedPositions = {"b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"};
        positions.removeAll(Arrays.asList(excludedPositions));

        return squareProvider(positions.toArray(new String[0]));
    }

    // Method to provide parameters
    private static Stream<Square> squareProvider(String[] algebraicPositions) {
        return Stream.of(algebraicPositions)
                .map(Square::new);
    }
}
