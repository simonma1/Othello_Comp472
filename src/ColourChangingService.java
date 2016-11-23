import java.awt.*;

/**
 * Created by Harrison on 2016-11-12.
 */
public class ColourChangingService {

    private static int encounteredOppositeColors = 0;
    private static boolean emptyEncountered = false;
    private static boolean emptyOrSameColourEncountered = false;
    public static void changeColours(Board board, Point theNewMove) {

        if (checkIsValidUnder(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (i < Board.BOARD_WIDTH && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                i++;
            }
        }
        if (checkIsValidOver(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (i >= 0 && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                i--;
            }
        }
        if (checkIsValidLeft(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (j >= 0 && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                j--;
            }
        }
        if (checkIsValidRight(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (j < Board.BOARD_WIDTH && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                j++;
            }
        }
        if (checkIsValidTopRightDiagonal(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (i >= 0 && j < Board.BOARD_WIDTH && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                i--;
                j++;
            }
        }
        if (checkIsValidBottomRightDiagonal(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (i < Board.BOARD_HEIGHT && j < Board.BOARD_WIDTH && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                i++;
                j++;
            }
        }
        if (checkIsValidTopLeftDiagonal(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (i >= 0 && j >= 0 && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                i--;
                j--;
            }
        }
        if (checkIsValidBottomLeftDiagonal(board, theNewMove)) {
            int i = (int)theNewMove.getX();
            int j = (int)theNewMove.getY();
            emptyOrSameColourEncountered = false;

            while (i < Board.BOARD_HEIGHT && j >= 0 && !emptyOrSameColourEncountered) {
                Point currentPos = new Point(i, j);
                changeCurrentPositionColour(board, currentPos, theNewMove);
                i++;
                j--;
            }
        }
    }

    public static boolean checkMoveIsValidAllDirections(Board board, Point theNewMove) {
        return checkIsValidLeft(board, theNewMove) ||
                checkIsValidRight(board, theNewMove) ||
                checkIsValidOver(board, theNewMove) ||
                checkIsValidUnder(board, theNewMove) ||
                checkIsValidTopLeftDiagonal(board, theNewMove) ||
                checkIsValidTopRightDiagonal(board, theNewMove) ||
                checkIsValidBottomLeftDiagonal(board, theNewMove) ||
                checkIsValidBottomRightDiagonal(board, theNewMove);

    }

    private static boolean checkIsValidBottomLeftDiagonal(Board board, Point theNewMove) {
        if (theNewMove.getX() + 1 >= Board.BOARD_HEIGHT || theNewMove.getY() - 1 < 0) // no point in checking bottom right if on rightmost column or bottom row
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && i < Board.BOARD_HEIGHT && j < Board.BOARD_WIDTH) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            i++;
            j--;
        }

        return isValidMove;
    }

    private static boolean checkIsValidTopLeftDiagonal(Board board, Point theNewMove) {
        if (theNewMove.getX() - 1 < 0 || theNewMove.getY() - 1 < 0) // no point in checking top left if on leftmost column or top row
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && i >= 0 && j >= 0) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            i--;
            j--;
        }

        return isValidMove;
    }

    private static boolean checkIsValidBottomRightDiagonal(Board board, Point theNewMove) {
        if (theNewMove.getX() + 1 >= Board.BOARD_HEIGHT || theNewMove.getY() + 1 >= Board.BOARD_WIDTH) // no point in checking bottom right if on rightmost column or bottom row
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && i < Board.BOARD_HEIGHT && j < Board.BOARD_WIDTH) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            i++;
            j++;
        }

        return isValidMove;
    }

    private static boolean checkIsValidTopRightDiagonal(Board board, Point theNewMove) {
        if (theNewMove.getX() - 1 < 0 || theNewMove.getY() + 1 >= Board.BOARD_WIDTH) // no point in checking top right if on rightmost column or top row
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && i >= 0 && j < Board.BOARD_WIDTH) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            i--;
            j++;
        }

        return isValidMove;
    }

    private static boolean checkIsValidRight(Board board, Point theNewMove) {
        if (theNewMove.getY() + 1 >= Board.BOARD_WIDTH) // no point in checking right if on rightmost column
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && j < Board.BOARD_WIDTH) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            j++;
        }

        return isValidMove;
    }

    private static boolean checkIsValidLeft(Board board, Point theNewMove) {
        if (theNewMove.getY() - 1 < 0) // no point in checking left if on leftmost column
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && j >= 0) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            j--;
        }

        return isValidMove;
    }

    private static boolean checkIsValidOver(Board board, Point theNewMove) {
        if (theNewMove.getX() - 1 < 0) // no point in checking over if on top row
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && i >= 0) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            i--;
        }

        return isValidMove;
    }

    private static boolean checkIsValidUnder(Board board, Point theNewMove) {
        if (theNewMove.getX() + 1 >= Board.BOARD_HEIGHT) // no point in checking under if on bottom row
            return false;

        encounteredOppositeColors = 0;
        boolean isValidMove = false;
        emptyEncountered = false;

        int i = (int)theNewMove.getX();
        int j = (int)theNewMove.getY();
        while(!emptyEncountered && !isValidMove && i < Board.BOARD_HEIGHT) {
            Point currentPos = new Point(i, j);
            isValidMove = generalValidityCheck(board, currentPos, theNewMove);
            i++;
        }

        return isValidMove;
    }

    private static boolean generalValidityCheck(Board board, Point currentPos, Point theNewMove) {
        boolean isValidMove = false;
        if (board.getTurn() == Turn.BLACK) {
            if (board.getBoard().get(currentPos) == SquareState.WHITE) {
                encounteredOppositeColors++;
            }
            if (board.getBoard().get(currentPos) == SquareState.EMPTY) {
                emptyEncountered = true;
                return isValidMove;
            }
            if (board.getBoard().get(currentPos) == SquareState.BLACK && encounteredOppositeColors > 0) {
                isValidMove = true;
            }
        }
        else {
            if (board.getBoard().get(currentPos) == SquareState.BLACK) {
                encounteredOppositeColors++;
            }
            if (board.getBoard().get(currentPos) == SquareState.EMPTY) {
                emptyEncountered = true;
                return isValidMove;
            }
            if (board.getBoard().get(currentPos) == SquareState.WHITE && encounteredOppositeColors > 0) {
                isValidMove = true;
            }
        }
        return isValidMove;
    }

    private static boolean changeCurrentPositionColour(Board board, Point currentPos, Point theNewMove) {
        if (board.getBoard().get(theNewMove) == SquareState.BLACK) {
            if (board.getBoard().get(currentPos) == SquareState.WHITE) {
                board.getBoard().put(currentPos, SquareState.BLACK);
                board.removeWhitePiece(currentPos);
                board.addBlackPiece(currentPos);
            }
            else if (board.getBoard().get(currentPos) == SquareState.BLACK && !currentPos.equals(theNewMove)) {
                emptyOrSameColourEncountered = true;
                return true;
            }
            else if (board.getBoard().get(currentPos) == SquareState.EMPTY) {
                emptyOrSameColourEncountered = true;
                return false;
            }
        }
        else if (board.getBoard().get(theNewMove) == SquareState.WHITE) {
            if (board.getBoard().get(currentPos) == SquareState.BLACK) {
                board.getBoard().put(currentPos, SquareState.WHITE);
                board.removeBlackPiece(currentPos);
                board.addWhitePiece(currentPos);
            }
            else if (board.getBoard().get(currentPos) == SquareState.WHITE && !currentPos.equals(theNewMove)) {
                emptyOrSameColourEncountered = true;
                return true;
            }
            else if (board.getBoard().get(currentPos) == SquareState.EMPTY) {
                emptyOrSameColourEncountered = true;
                return false;
            }
        }
        return true;
    }
}
