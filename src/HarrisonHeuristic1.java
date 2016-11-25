import java.awt.*;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-24.
 */
public class HarrisonHeuristic1 implements HeuristicCalculator {
    private final int MIDDLE = 1;
    private final int MIDDLE_SHELL = 10;
    private final int OUTER_SHELL = 100;
    private final int BORDER_SHELL = 1000;
    private final int CORNER = 2000;
    private SquareState currentPlayerColour;
    private SquareState opponentPlayerColour;
    private int heuristicScore = 0;

    @Override
    public int calculateHeuristic(Board board, Turn turn) {
        heuristicScore = 0;

        // calculate
        for (Point whitePiece : board.getWhitePieces()) {
            if (isMiddlePiece(whitePiece))
                updateHeuristic(-MIDDLE);
            else if (isMiddleShellPiece(whitePiece))
                updateHeuristic(-MIDDLE_SHELL);
            else if (isOuterShellPiece(whitePiece))
                updateHeuristic(-OUTER_SHELL);
            else if (isBorderShellPiece(whitePiece))
                updateHeuristic(-BORDER_SHELL);
            else if (isCornerPiece(whitePiece))
                updateHeuristic(-CORNER);
        }

        for (Point blackPiece : board.getBlackPieces()) {
            if (isMiddlePiece(blackPiece))
                updateHeuristic(MIDDLE);
            else if (isMiddleShellPiece(blackPiece))
                updateHeuristic(MIDDLE_SHELL);
            else if (isOuterShellPiece(blackPiece))
                updateHeuristic(OUTER_SHELL);
            else if (isBorderShellPiece(blackPiece))
                updateHeuristic(BORDER_SHELL);
            else if (isCornerPiece(blackPiece))
                updateHeuristic(CORNER);
        }
        return heuristicScore;
    }

    private boolean isCornerPiece(Point piece) {
        return (piece.getX() == 0 && piece.getY() == 0) ||
                (piece.getX() == 0 && piece.getY() + 1 == Board.BOARD_WIDTH) ||
                (piece.getX() + 1 == Board.BOARD_HEIGHT && piece.getY() == 0) ||
                (piece.getX() + 1 == Board.BOARD_HEIGHT && piece.getY() + 1 == Board.BOARD_WIDTH);
    }

    private boolean isBorderShellPiece(Point piece) {
        return (piece.getX() == 0 && piece.getY() + 1 != Board.BOARD_WIDTH) ||
                (piece.getX() != 0 && piece.getY() + 1 == Board.BOARD_WIDTH) ||
                (piece.getX() + 1 == Board.BOARD_HEIGHT && piece.getY() != 0) ||
                (piece.getX() + 1 != Board.BOARD_HEIGHT && piece.getY() == 0);
    }

    private boolean isOuterShellPiece(Point piece) {
        return piece.getX() - 1 == 0 ||
                piece.getX() + 2 == Board.BOARD_HEIGHT ||
                piece.getY() - 1 == 0 ||
                piece.getY() + 2 == Board.BOARD_WIDTH;
    }

    private boolean isMiddleShellPiece(Point piece) {
       return piece.getX() - 2 == 0 ||
               piece.getX() + 3 == Board.BOARD_HEIGHT ||
               piece.getY() - 2 == 0 ||
               piece.getY() + 3 == Board.BOARD_WIDTH;
    }

    private boolean isMiddlePiece(Point piece) {
        return piece.getX() - 3 == 0 ||
                piece.getX() + 4 == Board.BOARD_HEIGHT ||
                piece.getY() - 3 == 0 ||
                piece.getY() + 4 == Board.BOARD_WIDTH;
    }

    private void updateHeuristic(int points) {
        heuristicScore += points;
    }

    @Override
    public int calculateHeuristic(HashMap<Point, SquareState> board) {
        return 0;
    }
}
