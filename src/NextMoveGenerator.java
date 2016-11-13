import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Harrison on 2016-11-07.
 */
public class NextMoveGenerator {

    private static int encounteredOppositeColors = 0;

    public static ArrayList<Board> generateNextStates(Board board) {
        ArrayList<Board> generatedChildrenStates = new ArrayList<>();
        updatePossibleMoves(board);

        for (int i = 0; i < Board.BOARD_WIDTH; i++) {
            for (int j = 0; j < Board.BOARD_HEIGHT; j++) {
                Point currentSquare = new Point(i,j);
                if (isValidMove(board, currentSquare, board.getTurn())) {
                    // Clone the board
                    Board generatedChildState = board.clone();

                    // Add the point to the board
                    if (board.getTurn() == Turn.BLACK) {
                        generatedChildState.getBoard().put(currentSquare, SquareState.BLACK);
                    }
                    else {
                        generatedChildState.getBoard().put(currentSquare, SquareState.WHITE);
                    }
                    // TODO: 2016-11-13 change the colors of the pieces of those affected by the added move
                    // TODO: 2016-11-13 add the changed state to the list of generated states
                }
            }
        }

        return generatedChildrenStates;
    }

    public static boolean isValidMove(Board board, Point possibleMove, Turn turn) {
        boolean isValidMove = false;

        // Checks if the square chosen is a square that's already taken
        if (board.getBoard().get(possibleMove) == SquareState.BLACK &&
                board.getBoard().get(possibleMove) == SquareState.WHITE) {
            return isValidMove;
        }

        // Checks if the square chosen is an empty square not on the fringe
        boolean isIsolatedEmptySquare = true;
        ArrayList<Point> adjacentSquares = getAdjacentSquares(possibleMove);
        for (Point adjacentSquare :
                    adjacentSquares) {
                if (board.getBoard().get(adjacentSquare) != SquareState.EMPTY &&
                        board.getBoard().get(adjacentSquare) != SquareState.POSSIBLE)
                    isIsolatedEmptySquare = false;
        }
        if (isIsolatedEmptySquare)
            return isValidMove;


        encounteredOppositeColors = 0;
        isValidMove = checkValidConditionsRight(board, possibleMove) ||
                checkValidConditionsLeft(board, possibleMove) ||
                checkValidConditionsUnder(board, possibleMove) ||
                checkValidConditionsOver(board, possibleMove) ||
                checkValidConditionsTopLeftDiagonal(board, possibleMove) ||
                checkValidConditionsTopRightDiagonal(board, possibleMove) ||
                checkValidConditionsBottomLeftDiagonal(board, possibleMove) ||
                checkValidConditionsBottomRightDiagonal(board, possibleMove);

//            ArrayList<Point> adjacentSquares = getAdjacentSquares(possibleMove);
//            for (Point adjacentSquare :
//                    adjacentSquares) {
//
//            }
//            board.getBoard().get(possibleMove);


        return isValidMove;
    }

    public static boolean checkValidConditionsOver(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions to the right of this possible move
        for (int i = (int)possibleMove.getX(); i < Board.BOARD_WIDTH; i++) {
            Point currentPos = new Point(i, (int)possibleMove.getY()); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean checkValidConditionsUnder(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions to the left of this possible move
        for (int i = (int)possibleMove.getX(); i > 0; i--) {
            Point currentPos = new Point(i, (int)possibleMove.getY()); // define the current iterated point

           //Should stop as soon as a valid move is found
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean checkValidConditionsLeft(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions under of this possible move
        for (int i = (int)possibleMove.getY(); i < Board.BOARD_HEIGHT; i++) {
            Point currentPos = new Point((int)possibleMove.getX(), i); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean checkValidConditionsRight(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions over of this possible move
        for (int i = (int)possibleMove.getY(); i > 0; i--) {
            Point currentPos = new Point((int)possibleMove.getX(), i); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean checkValidConditionsTopLeftDiagonal(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions in the top left diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i > 0 && j < Board.BOARD_HEIGHT; i--, j++) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean checkValidConditionsTopRightDiagonal(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions in the top right diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i < Board.BOARD_WIDTH && j < Board.BOARD_HEIGHT; i++, j++) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean checkValidConditionsBottomLeftDiagonal(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions in the bottom left diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i > 0 && j > 0; i--, j--) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean checkValidConditionsBottomRightDiagonal(Board board, Point possibleMove) {
        boolean isValidMove = false;
        // check for valid move conditions in the bottom right diagonal of this possible move
        for (int i = (int)possibleMove.getX(), j = (int)possibleMove.getY(); i < Board.BOARD_WIDTH && j > 0; i++, j--) {
            Point currentPos = new Point(i, j); // define the current iterated point
            isValidMove = generalValidityCheck(board, currentPos);
            if(isValidMove){
                return isValidMove;
            }
        }
        return isValidMove;
    }

    public static boolean generalValidityCheck(Board board, Point currentPos){
        boolean isValidMove = false;
        if (board.getTurn() == Turn.BLACK) {
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

    private static ArrayList<Point> getAdjacentSquares(Point square) {
        final int BOARD_LENGTH = 8;
        final int BOARD_HEIGHT = 8;
        ArrayList<Point> adjacentSquares = new ArrayList<>();

        if (square.getX() - 1 > 0) {
            if (square.getY() - 1 > 0) {
                adjacentSquares.add(new Point(
                        (int) square.getX() - 1,
                        (int) square.getY() - 1
                ));
            }

            adjacentSquares.add(new Point(
                    (int) square.getX() - 1,
                    (int) square.getY()
            ));

            if (square.getY() + 1 < BOARD_HEIGHT) {
                adjacentSquares.add(new Point(
                        (int) square.getX() - 1,
                        (int) square.getY() + 1
                ));
            }
        }
        if (square.getX() + 1 < BOARD_LENGTH) {
            if (square.getY() - 1 > 0) {
                adjacentSquares.add(new Point(
                        (int) square.getX() + 1,
                        (int) square.getY() - 1
                ));
            }

            adjacentSquares.add(new Point(
                    (int) square.getX() + 1,
                    (int) square.getY()
            ));

            if (square.getY() + 1 < BOARD_HEIGHT) {
                adjacentSquares.add(new Point(
                        (int) square.getX() + 1,
                        (int) square.getY() + 1
                ));
            }
        }
        if (square.getY() - 1 > 0) {
            adjacentSquares.add(new Point(
                    (int) square.getX(),
                    (int) square.getY() - 1
            ));
        }
        if (square.getY() + 1 < BOARD_HEIGHT) {
            adjacentSquares.add(new Point(
                    (int) square.getX(),
                    (int) square.getY() + 1
            ));
        }

        return adjacentSquares;
    }

    public static void updatePossibleMoves(Board board) {
        for (int i = 0; i < Board.BOARD_WIDTH; i++) {
            for (int j = 0; j < Board.BOARD_HEIGHT; j++) {
                Point thisPoint = new Point(i,j);
                ArrayList<Point> adjacentSquares = getAdjacentSquares(thisPoint);
                for (Point adjacentSquare :
                        adjacentSquares) {
                    if (board.squareIsNonEmpty(adjacentSquare)) {
                        // TODO: 2016-11-12  This method is not complete yet.
                    }
                }
            }
        }
    }
}
