package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GameTests {

    @Test
    public void testParseFunction(){
        //Arrange
        String move = "e7-e6";

        //Act
        String [] parts = GameImpl.parse(move);
        Square currentPos = new Square(parts[0]);
        Square destination = new Square(parts[1]);

        //Assert
        assertEquals(new Square("e7"),currentPos);
        assertEquals(new Square("e6"),destination);

    }

    @Test
    public void testChangePlayerAfterLegalMove(){
        //Arrange
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        //Act
        game.move("d2-d4");

        //Assert
        assertEquals(Color.BLACK,game.getPlayerToMove());
    }

    @Test
    public void testDontChangePlayerAfterIllegalMove(){
        //Arrange
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        //Act
        game.move("d2-e5");

        //Assert
        assertEquals(Color.WHITE,game.getPlayerToMove());
    }

    @Test
    public void testMoveOpponentsPieceShouldBeIllegal() {
        //Arrange
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        //Act
        game.move("e7-e6");

        //Assert
        assertEquals(Color.WHITE, game.getPlayerToMove());
        ChessPiece piece = game.getBoard().getPieceAt(new Square("e7"));
        assertEquals(Color.BLACK, piece.getColor());
        assertEquals(PieceType.PAWN, piece.getType());


        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(game.getBoard()));
    }

    @Test
    public void testCantMoveFromEmptySquare() {
        //Arrange
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        //Act
        game.move("e6-e5");

        //Assert
        assertEquals(Color.WHITE, game.getPlayerToMove());
        assertNull(game.getBoard().getPieceAt(new Square("e6")));
        assertNull(game.getBoard().getPieceAt(new Square("e5")));
    }

    @Test
    public void testMovedPieceIndeedMoved() {
        //Arrange
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        //Act
        game.move("d2-d4");

        //Assert
        assertEquals(Color.BLACK, game.getPlayerToMove());
        ChessPiece piece = game.getBoard().getPieceAt(new Square("d4"));
        assertEquals(Color.WHITE, piece.getColor());
        assertEquals(PieceType.PAWN, piece.getType());
        assertNull(game.getBoard().getPieceAt(new Square("d2")));
    }

    @Test
    public void testEatenPieceGone() {
        //Arrange
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        //Act
        game.move("d2-d4");
        game.move("e7-e5");
        game.move("d4-e5");

        //Assert
        assertEquals(Color.BLACK, game.getPlayerToMove());
        ChessPiece piece = game.getBoard().getPieceAt(new Square("e5"));
        assertEquals(Color.WHITE, piece.getColor());
        assertEquals(PieceType.PAWN, piece.getType());
    }
}
