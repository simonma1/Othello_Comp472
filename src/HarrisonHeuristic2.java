import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-27.
 */
public class HarrisonHeuristic2 implements HeuristicCalculator {
    private final int REGULAR_SQUARE = 50;
    private final int THIRD_MOST_IMPORTANT_SQUARE = 100;
    private final int SECOND_MOST_IMPORTANT_SQUARE = 100;
    private final int FIRST_MOST_IMPORTANT_SQUARE = 150;
    private int heuristicScore = 0;

    @Override
    public int calculateHeuristic(Board board, Turn turn) {
        heuristicScore = 0;

        if (turn == Turn.BLACK) {
            evaluateHeuristicBlackTurn(board);

        }
        else {
            evaluateHeuristicWhiteTurn(board);
        }

        return heuristicScore;
    }

    private void evaluateHeuristicWhiteTurn(Board board) {
        for (Point blackPiece : board.getBlackPieces()) {
            if (isThirdMostImportantSquare(blackPiece))
                updateHeuristic(-THIRD_MOST_IMPORTANT_SQUARE);
            else if (isSecondMostImportantSquare(blackPiece))
                updateHeuristic(-SECOND_MOST_IMPORTANT_SQUARE);
            else if (isFirstMostImportantSquare(blackPiece))
                updateHeuristic(-FIRST_MOST_IMPORTANT_SQUARE);
            else
                updateHeuristic(-REGULAR_SQUARE);
        }

        for (Point whitePiece : board.getWhitePieces()) {
            if (isThirdMostImportantSquare(whitePiece))
                updateHeuristic(THIRD_MOST_IMPORTANT_SQUARE);
            else if (isSecondMostImportantSquare(whitePiece))
                updateHeuristic(SECOND_MOST_IMPORTANT_SQUARE);
            else if (isFirstMostImportantSquare(whitePiece))
                updateHeuristic(FIRST_MOST_IMPORTANT_SQUARE);
            else
                updateHeuristic(REGULAR_SQUARE);
        }
    }

    private void evaluateHeuristicBlackTurn(Board board) {
        for (Point blackPiece : board.getBlackPieces()) {
            if (isThirdMostImportantSquare(blackPiece))
                updateHeuristic(THIRD_MOST_IMPORTANT_SQUARE);
            else if (isSecondMostImportantSquare(blackPiece))
                updateHeuristic(SECOND_MOST_IMPORTANT_SQUARE);
            else if (isFirstMostImportantSquare(blackPiece))
                updateHeuristic(FIRST_MOST_IMPORTANT_SQUARE);
            else
                updateHeuristic(REGULAR_SQUARE);
        }

        for (Point whitePiece : board.getWhitePieces()) {
            if (isThirdMostImportantSquare(whitePiece))
                updateHeuristic(-THIRD_MOST_IMPORTANT_SQUARE);
            else if (isSecondMostImportantSquare(whitePiece))
                updateHeuristic(-SECOND_MOST_IMPORTANT_SQUARE);
            else if (isFirstMostImportantSquare(whitePiece))
                updateHeuristic(-FIRST_MOST_IMPORTANT_SQUARE);
            else
                updateHeuristic(-REGULAR_SQUARE);
        }
    }

    private boolean isFirstMostImportantSquare(Point piece) {
        return (piece.getX() == 0 && piece.getY() == 0) ||
                (piece.getX() == 0 && piece.getY() + 1 == Board.BOARD_WIDTH) ||
                (piece.getX() + 1 == Board.BOARD_HEIGHT && piece.getY() == 0) ||
                (piece.getX() + 1 == Board.BOARD_HEIGHT && piece.getY() + 1 == Board.BOARD_WIDTH);
    }

    private boolean isSecondMostImportantSquare(Point piece) {
        return (piece.getX() - 2 == 0 && piece.getY() == 0) ||
                (piece.getX() + 3 == Board.BOARD_HEIGHT && piece.getY() == 0) ||
                (piece.getX() == 0 && piece.getY() - 2 == 0) ||
                (piece.getX() + 1 == Board.BOARD_HEIGHT && piece.getY() - 2 == 0) ||
                (piece.getX() == 0 && piece.getY() + 3 == Board.BOARD_WIDTH) ||
                (piece.getX() + 1 == Board.BOARD_HEIGHT && piece.getY() + 3 == Board.BOARD_WIDTH) ||
                (piece.getX() - 2 == 0 && piece.getY() + 1 == Board.BOARD_HEIGHT) ||
                (piece.getX() + 3 == Board.BOARD_HEIGHT && piece.getY() + 1 == Board.BOARD_HEIGHT);
    }

    private boolean isThirdMostImportantSquare(Point piece) {
        return (piece.getX() - 2 == 0 && piece.getY() - 2 == 0) ||
                (piece.getX() + 3 == Board.BOARD_HEIGHT && piece.getY() - 2 == 0) ||
                (piece.getX() - 2 == 0 && piece.getY() + 3 == Board.BOARD_WIDTH) ||
                (piece.getX() + 3 == Board.BOARD_WIDTH && piece.getY() + 3 == Board.BOARD_WIDTH);
    }

    private void updateHeuristic(int points) {
        heuristicScore += points;
    }

    @Override
    public int calculateHeuristic(HashMap<Point, SquareState> board) {
        return 0;
    }
}
