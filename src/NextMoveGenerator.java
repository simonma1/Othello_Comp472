import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Harrison on 2016-11-07.
 */
public class NextMoveGenerator {

    private static int encounteredOppositeColors = 0;

    public static ArrayList<Board> generateNextStates(Board board, Turn turn) {
        ArrayList<Board> generatedChildrenStates = new ArrayList<>();
        for (Point possibleMove :
                board.getPossibleMoves()) {
            if (isValidMove(board, possibleMove, turn)) {

            }

        }

        return generatedChildrenStates;
    }

    public static boolean isValidMove(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;

        isValidMove = checkValidConditionsRight(board, possibleMove, turn) ||
                checkValidConditionsLeft(board, possibleMove, turn) ||
                checkValidConditionsUnder(board, possibleMove, turn) ||
                checkValidConditionsOver(board, possibleMove, turn) ||
                checkValidConditionsTopLeftDiagonal(board, possibleMove, turn) ||
                checkValidConditionsTopRightDiagonal(board, possibleMove, turn) ||
                checkValidConditionsBottomLeftDiagonal(board, possibleMove, turn) ||
                checkValidConditionsBottomRightDiagonal(board, possibleMove, turn);

        ArrayList<Point> adjacentSquares = getAdjacentSquares(possibleMove);
        for (Point adjacentSquare :
                adjacentSquares) {

        }
        board.getBoard().get(possibleMove);

        return isValidMove;
    }

    private static boolean checkValidConditionsRight(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions to the right of this possible move
        for (int i = (int)possibleMove.getX(); i < Board.BOARD_WIDTH; i++) {
            Point currentPos = new Point(i, (int)possibleMove.getY()); // define the current iterated point

        }
        return isValidMove;
    }

    private static boolean checkValidConditionsLeft(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions to the left of this possible move
        for (int i = (int)possibleMove.getX(); i > 0; i--) {
            Point currentPos = new Point(i, (int)possibleMove.getY()); // define the current iterated point

           //Should stop as soon as a valid move is found
            isValidMove = generalValidityCheck(board, currentPos, turn);
        }
        return isValidMove;
    }

    private static boolean checkValidConditionsUnder(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions under of this possible move
        for (int i = (int)possibleMove.getY(); i < Board.BOARD_HEIGHT; i++) {
            Point currentPos = new Point((int)possibleMove.getX(), i); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos, turn);
        }
        return isValidMove;
    }

    private static boolean checkValidConditionsOver(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions over of this possible move
        for (int i = (int)possibleMove.getY(); i > 0; i--) {
            Point currentPos = new Point((int)possibleMove.getX(), i); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos, turn);
        }
        return isValidMove;
    }

    private static boolean checkValidConditionsTopLeftDiagonal(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions in the top left diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i > 0 && j < Board.BOARD_HEIGHT; i--, j++) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos, turn);
        }
        return isValidMove;
    }

    private static boolean checkValidConditionsTopRightDiagonal(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions in the top right diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i < Board.BOARD_WIDTH && j < Board.BOARD_HEIGHT; i++, j++) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos, turn);
        }
        return isValidMove;
    }

    private static boolean checkValidConditionsBottomLeftDiagonal(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions in the bottom left diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i > 0 && j > 0; i--, j--) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos, turn);
        }
        return isValidMove;
    }

    private static boolean checkValidConditionsBottomRightDiagonal(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;
        encounteredOppositeColors = 0;
        // check for valid move conditions in the bottom right diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i < Board.BOARD_WIDTH && j > 0; i++, j--) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos, turn);
        }
        return isValidMove;
    }

    private  static boolean generalValidityCheck(Board board, Point currentPos, Turn turn){
        boolean isValidMove = false;
        if (turn == Turn.BLACK) {
            if (board.getBoard().get(currentPos) == SquareState.WHITE) { // check for encountered whites
                encounteredOppositeColors++;
            }
            if (board.getBoard().get(currentPos) == SquareState.BLACK && encounteredOppositeColors > 0) {
                isValidMove = true;
            }
        }
        else {
            if (board.getBoard().get(currentPos) == SquareState.BLACK) { // check for encountered whites
                encounteredOppositeColors++;
            }
            if (board.getBoard().get(currentPos) == SquareState.WHITE && encounteredOppositeColors > 0) {
                isValidMove = true;
            }
        }
        return isValidMove;
    }

    private static ArrayList<Point> getAdjacentSquares(Point possibleMove) {
        final int BOARD_LENGTH = 8;
        final int BOARD_HEIGHT = 8;
        ArrayList<Point> adjacentSquares = new ArrayList<>();

        if (possibleMove.getX() - 1 > 0) {
            if (possibleMove.getY() - 1 > 0) {
                adjacentSquares.add(new Point(
                        (int) possibleMove.getX() - 1,
                        (int) possibleMove.getY() - 1
                ));
            }

            adjacentSquares.add(new Point(
                    (int) possibleMove.getX() - 1,
                    (int) possibleMove.getY()
            ));

            if (possibleMove.getY() + 1 < BOARD_HEIGHT) {
                adjacentSquares.add(new Point(
                        (int) possibleMove.getX() - 1,
                        (int) possibleMove.getY() + 1
                ));
            }
        }
        if (possibleMove.getX() + 1 < BOARD_LENGTH) {
            if (possibleMove.getY() - 1 > 0) {
                adjacentSquares.add(new Point(
                        (int) possibleMove.getX() + 1,
                        (int) possibleMove.getY() - 1
                ));
            }

            adjacentSquares.add(new Point(
                    (int) possibleMove.getX() + 1,
                    (int) possibleMove.getY()
            ));

            if (possibleMove.getY() + 1 < BOARD_HEIGHT) {
                adjacentSquares.add(new Point(
                        (int) possibleMove.getX() + 1,
                        (int) possibleMove.getY() + 1
                ));
            }
        }
        if (possibleMove.getY() - 1 > 0) {
            adjacentSquares.add(new Point(
                    (int) possibleMove.getX(),
                    (int) possibleMove.getY() - 1
            ));
        }
        if (possibleMove.getY() + 1 < BOARD_HEIGHT) {
            adjacentSquares.add(new Point(
                    (int) possibleMove.getX(),
                    (int) possibleMove.getY() + 1
            ));
        }

        return adjacentSquares;
    }
}
