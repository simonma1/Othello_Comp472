import java.awt.*;

/**
 * Created by Harrison on 2016-11-12.
 */
public class ColourChangingService {
    public static void changeColours(Board board, Point theNewMove) {

        if (NextMoveGenerator.checkValidConditionsRight(board, theNewMove)) {
            for (int i = (int)theNewMove.getX() + 1; i < Board.BOARD_WIDTH; i++) {
                Point currentPos = new Point(i, (int)theNewMove.getY());
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
        if (NextMoveGenerator.checkValidConditionsLeft(board, theNewMove)) {
            for (int i = (int)theNewMove.getX() - 1; i > 0; i--) {
                Point currentPos = new Point(i, (int)theNewMove.getY());
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
        if (NextMoveGenerator.checkValidConditionsUnder(board, theNewMove)) {
            for (int i = (int)theNewMove.getY() - 1; i > 0; i--) {
                Point currentPos = new Point((int)theNewMove.getX(), i);
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
        if (NextMoveGenerator.checkValidConditionsOver(board, theNewMove)) {
            for (int i = (int)theNewMove.getY() + 1; i < Board.BOARD_HEIGHT; i++) {
                Point currentPos = new Point((int)theNewMove.getX(), i);
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
        if (NextMoveGenerator.checkValidConditionsTopLeftDiagonal(board, theNewMove)) {
            for (int i = (int)theNewMove.getX() - 1, j = (int)theNewMove.getY() + 1; i > 0 && j < Board.BOARD_HEIGHT; i--, j++) {
                Point currentPos = new Point(i, j);
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
        if (NextMoveGenerator.checkValidConditionsTopRightDiagonal(board, theNewMove)) {
            for (int i = (int)theNewMove.getX() + 1, j = (int)theNewMove.getY() + 1; i < Board.BOARD_WIDTH && j < Board.BOARD_HEIGHT; i++, j++) {
                Point currentPos = new Point(i, j);
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
        if (NextMoveGenerator.checkValidConditionsBottomLeftDiagonal(board, theNewMove)) {
            for (int i = (int)theNewMove.getX() - 1, j = (int)theNewMove.getY() - 1; i > 0 && j > 0; i--, j--) {
                Point currentPos = new Point(i, j);
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
        if (NextMoveGenerator.checkValidConditionsBottomRightDiagonal(board, theNewMove)) {
            for (int i = (int)theNewMove.getX() + 1, j = (int)theNewMove.getY() - 1; i < Board.BOARD_WIDTH && j > 0; i++, j--) {
                Point currentPos = new Point(i, j);
                if (changeCurrentPositionColour(board, currentPos, theNewMove))
                    break;
            }
        }
    }

    private static boolean changeCurrentPositionColour(Board board, Point currentPos, Point theNewMove) {
        if (board.getBoard().get(theNewMove) == SquareState.BLACK) {
            if (board.getBoard().get(currentPos) == SquareState.WHITE)
                board.getBoard().put(currentPos, SquareState.BLACK);
            else if (board.getBoard().get(currentPos) == SquareState.BLACK)
                return true;
        }
        else if (board.getBoard().get(theNewMove) == SquareState.WHITE) {
            if (board.getBoard().get(currentPos) == SquareState.BLACK)
                board.getBoard().put(currentPos, SquareState.WHITE);
            else if (board.getBoard().get(currentPos) == SquareState.WHITE)
                return true;
        }
        return true;
    }
}
