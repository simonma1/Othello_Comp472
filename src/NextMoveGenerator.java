import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Harrison on 2016-11-07.
 */
public class NextMoveGenerator {


    public static ArrayList<Board> generateNextStates(Board board) {
        ArrayList<Board> generatedChildrenStates = new ArrayList<>();
        for (Point possibleMove :
                board.getPossibleMoves()) {
            if (isValidMove(board, possibleMove)) {

            }

        }

        return generatedChildrenStates;
    }

    private static boolean isValidMove(Board board, Point possibleMove) {
        boolean isValidMove = false;

        ArrayList<Point> adjacentSquares = getAdjacentSquares(possibleMove);
        for (Point adjacentSquare :
                adjacentSquares) {

        }
        board.getBoard().get(possibleMove);

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
