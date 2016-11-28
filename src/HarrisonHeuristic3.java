import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-28.
 */
public class HarrisonHeuristic3 implements HeuristicCalculator {
    private final int REGULAR_SQUARE = 500;
    private final int THIRD_MOST_IMPORTANT_SQUARE = 1000;
    private final int SECOND_MOST_IMPORTANT_SQUARE = 1000;
    private final int FIRST_MOST_IMPORTANT_SQUARE = 1500;
    private final double PERCENT = 100.0;
    private int heuristicScore = 0;

    @Override
    public int calculateHeuristic(Board board, Turn turn) {
        heuristicScore = 0;

        // Make a copy because will be used to get the children states, and will need to change the turn.
        Board boardCopy = board.clone();

        // find the heuristic of the board and set the turn of the board copy.
        if (turn == Turn.BLACK) {
            evaluateHeuristicBlackTurn(board);
            boardCopy.setTurn(Turn.WHITE);
        }
        else {
            evaluateHeuristicWhiteTurn(board);
            boardCopy.setTurn(Turn.BLACK);
        }

        // now that the turn is set for the copy, generate children states.
        ArrayList<Board> nextPossibleMoves = NextMoveGenerator.generateNextStates(boardCopy);

        /*
        the heuristic score is a percentage score of the number of moves available to the opponent. The more moves
        are available for the oppponent, the worse the heuristic score
        */
        heuristicScore *= 1 - (nextPossibleMoves.size()/PERCENT);
        return (int)heuristicScore;
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
