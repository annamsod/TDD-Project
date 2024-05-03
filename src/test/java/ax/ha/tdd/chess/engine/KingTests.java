package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.King;
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

public class KingTests {

    private Chessboard chessboard;

    @BeforeEach
    public void setUp() {
        chessboard = new ChessboardImpl();
    }

    @ParameterizedTest
    @MethodSource("allDirectionsSquareProvider")
    //destination = {"c6", "d6", "e6", "e5", "e4", "d4", "c4", "c5"}
    public void testKingCanMoveOneStepAnyDirection(Square destination){
        //Arrange
        King king = new King(Color.WHITE, new Square("d5"));
        chessboard.addPiece(king);

        //Assert
        assertTrue(king.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allRemainingSquaresProvider")
    public void testKingCantMoveMoreThanOneStep(Square destination){
        //Arrange
        King king = new King(Color.WHITE, new Square("d5"));
        chessboard.addPiece(king);

        //Assert
        assertFalse(king.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsSquareProvider")
    //destination = {"c6", "d6", "e6", "e5", "e4", "d4", "c4", "c5"}
    public void testKingCantEatOwn(Square destination){
        //Arrange
        King king = new King(Color.WHITE, new Square("d5"));
        chessboard.addPiece(king);
        Pawn pawn = new Pawn(Color.WHITE,destination);
        chessboard.addPiece(pawn);

        //Assert
        assertFalse(king.canMove(chessboard, destination));
    }

    @ParameterizedTest
    @MethodSource("allDirectionsSquareProvider")
    //destination = {"c6", "d6", "e6", "e5", "e4", "d4", "c4", "c5"}
    public void testKingCanEatOpponent(Square destination){
        //Arrange
        King king = new King(Color.WHITE, new Square("d5"));
        chessboard.addPiece(king);
        Pawn pawn = new Pawn(Color.BLACK,destination);
        chessboard.addPiece(pawn);

        //Assert
        assertTrue(king.canMove(chessboard, destination));
    }

    // Method to provide parameters for all movements
    private static Stream<Square> allDirectionsSquareProvider() {
        String[] positions = {"c6", "d6", "e6", "e5", "e4", "d4", "c4", "c5"};
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

        String[] excludedPositions = {"c6", "d6", "e6", "e5", "e4", "d4", "c4", "c5"};
        positions.removeAll(Arrays.asList(excludedPositions));

        return squareProvider(positions.toArray(new String[0]));
    }

    // Method to provide parameters
    private static Stream<Square> squareProvider(String[] algebraicPositions) {
        return Stream.of(algebraicPositions)
                .map(Square::new);
    }
}
