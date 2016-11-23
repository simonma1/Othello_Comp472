import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Harrison on 2016-11-07.
 */
public class NextMoveGenerator {

    private static int encounteredOppositeColors = 0;
    private static boolean encounteredSameColourOtherThanFirst = false;

    public static ArrayList<Board> generateNextStates(Board board) {
        ArrayList<Board> generatedChildrenStates = new ArrayList<>();
        ArrayList<Point> validMoves = new ArrayList<>();

        if (board.getTurn() == Turn.BLACK) {
            for (Point blackPiece : board.getBlackPieces()) { // find all the valid moves and put them in a list
                findValidMovesInAllDirections(board, blackPiece, validMoves);
            }
            for (Point validMove : validMoves) { // for each of the valid moves found
                Board generatedChildState = board.clone();

                generatedChildState.getBoard().put(validMove, SquareState.BLACK); // add a piece
                generatedChildState.addBlackPiece(validMove); // keep track of the added piece
                ColourChangingService.changeColours(generatedChildState, validMove); // change the colours on the board
                generatedChildrenStates.add(generatedChildState); // add child to list of generated children
            }
        }
        else {
            for (Point whitePiece : board.getWhitePieces()) { // find all the valid moves and put them in a list
                findValidMovesInAllDirections(board, whitePiece, validMoves);
            }
            for (Point validMove : validMoves) { // for each of the valid moves found
                Board generatedChildState = board.clone();

                generatedChildState.getBoard().put(validMove, SquareState.WHITE); // add a piece
                generatedChildState.addWhitePiece(validMove); // keep track of the added piece
                ColourChangingService.changeColours(generatedChildState, validMove); // change the colours on the board
                generatedChildrenStates.add(generatedChildState); // add child to list of generated children
            }
        }

        return generatedChildrenStates;
    }

    private static void findValidMovesInAllDirections(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        findValidMoveLeft(board, colourPiece, validMoves);
        findValidMoveRight(board, colourPiece, validMoves);
        findValidMoveOver(board, colourPiece, validMoves);
        findValidMoveUnder(board, colourPiece, validMoves);
        findValidMoveTopLeft(board, colourPiece, validMoves);
        findValidMoveTopRight(board, colourPiece, validMoves);
        findValidMoveBottomLeft(board, colourPiece, validMoves);
        findValidMoveBottomRight(board, colourPiece, validMoves);
    }

    private static void findValidMoveBottomRight(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getX() + 1 >= Board.BOARD_HEIGHT || colourPiece.getY() + 1 >= Board.BOARD_WIDTH) // no point in checking bottom right if on bottom row or rightmost column
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && i < Board.BOARD_HEIGHT && j < Board.BOARD_WIDTH) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            i++;
            j++;
        }
    }

    private static void findValidMoveBottomLeft(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getX() + 1 >= Board.BOARD_HEIGHT || colourPiece.getY() - 1 < 0) // no point in checking bottom left if on bottom row or leftmost column
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && i < Board.BOARD_HEIGHT && j >= 0) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            i++;
            j--;
        }
    }

    private static void findValidMoveTopRight(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getX() - 1 < 0 || colourPiece.getY() + 1 >= Board.BOARD_WIDTH) // no point in checking top right if on top row or rightmost column
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && i >= 0 && j < Board.BOARD_WIDTH) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            i--;
            j++;
        }
    }

    private static void findValidMoveTopLeft(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getX() - 1 < 0 || colourPiece.getY() - 1 < 0) // no point in checking top left if on top row or leftmost column
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && i >= 0 && j >= 0) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            i--;
            j--;
        }
    }

    private static void findValidMoveUnder(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getX() + 1 >= Board.BOARD_HEIGHT) // no point in checking under if on bottom row
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && i < Board.BOARD_HEIGHT) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            i++;
        }
    }

    private static void findValidMoveOver(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getX() - 1 < 0) // no point in checking over if on top row
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && i >= 0) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            i--;
        }
    }

    private static void findValidMoveRight(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getY() + 1 >= Board.BOARD_WIDTH) // no point in checking right if on rightmost column
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && j < Board.BOARD_WIDTH) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            j++;
        }
    }

    private static void findValidMoveLeft(Board board, Point colourPiece, ArrayList<Point> validMoves) {
        if (colourPiece.getY() - 1 < 0) // no point in checking left if on leftmost column
            return;

        encounteredOppositeColors = 0;
        encounteredSameColourOtherThanFirst = false;
        boolean isValidMoveStartingFromColour = false;

        int i = (int)colourPiece.getX();
        int j = (int)colourPiece.getY();

        while(!isValidMoveStartingFromColour && !encounteredSameColourOtherThanFirst && j >= 0) {
            Point currentPos = new Point(i, j);
            isValidMoveStartingFromColour = generalValidityCheck(board, currentPos);
            if (isValidMoveStartingFromColour) {
                if (!validMoveIsAlreadyFound(validMoves, currentPos)) {
                    validMoves.add(currentPos);
                }
            }
            j--;
        }
    }

    private static boolean generalValidityCheck(Board board, Point currentPos) {
        boolean isValidMoveStartingFromColour = false;
        if (board.getTurn() == Turn.BLACK) {
            if (board.getBoard().get(currentPos) == SquareState.WHITE) {
                encounteredOppositeColors++;
            }
            if ((board.getBoard().get(currentPos) == SquareState.EMPTY && encounteredOppositeColors == 0) ||
                    (board.getBoard().get(currentPos) == SquareState.BLACK && encounteredOppositeColors > 0)) {
                encounteredSameColourOtherThanFirst = true;
                return isValidMoveStartingFromColour;
            }
            if (board.getBoard().get(currentPos) == SquareState.EMPTY && encounteredOppositeColors > 0) {
                isValidMoveStartingFromColour = true;
            }
        }
        else {
            if (board.getBoard().get(currentPos) == SquareState.BLACK) {
                encounteredOppositeColors++;
            }
            if ((board.getBoard().get(currentPos) == SquareState.EMPTY && encounteredOppositeColors == 0) ||
                    (board.getBoard().get(currentPos) == SquareState.WHITE && encounteredOppositeColors > 0)) {
                encounteredSameColourOtherThanFirst = true;
                return isValidMoveStartingFromColour;
            }
            if (board.getBoard().get(currentPos) == SquareState.EMPTY && encounteredOppositeColors > 0) {
                isValidMoveStartingFromColour = true;
            }
        }

        return isValidMoveStartingFromColour;

    }

    private static boolean validMoveIsAlreadyFound(ArrayList<Point> validMoves, Point theNewMove) {
        for (Point validMove : validMoves) {
            if (validMove.getX() == theNewMove.getX() && validMove.getY() == theNewMove.getY()) {
                return true;
            }
        }
        return false;
    }

}
