import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simon on 2016-11-22.
 */
public class SimonHeuristic2 implements HeuristicCalculator{

    /*
    * This heuristic will try to maximize the number of moves the player would be able to do
    * versus the number of moves the opponent will be able to do
    * The smaller the number of moves by the opponent the better*/

    private SquareState playerColor;
    private SquareState opponentColor;
    private int currentHeuristicValue;

    @Override
    public int calculateHeuristic(Board board, Turn turn) {
        HashMap<Point,SquareState> currentBoardState = board.getBoard();
        setColors(turn);
        currentHeuristicValue = 0;

        //Gets the number of possible moves for the player from this board
        Board boardCopy = board.clone();
        boardCopy.setTurn(turn);
        ArrayList<Board> nextPossibleMoves = NextMoveGenerator.generateNextStates(boardCopy);
        currentHeuristicValue += nextPossibleMoves.size();

        //Gets the number of possible moves for the opponent
        Turn tempTurn;
        if (turn == Turn.BLACK){
            tempTurn = Turn.WHITE;
        }else{
            tempTurn = Turn.BLACK;
        }
        boardCopy.setTurn(tempTurn);
        nextPossibleMoves = null;
        nextPossibleMoves = NextMoveGenerator.generateNextStates(boardCopy);
        currentHeuristicValue -= nextPossibleMoves.size();

        return currentHeuristicValue;
    }

    public void setColors(Turn turn){
        if(turn == Turn.BLACK){
            playerColor = SquareState.BLACK;
            opponentColor = SquareState.WHITE;
        }else{
            playerColor = SquareState.WHITE;
            opponentColor = SquareState.BLACK;
        }
    }


    @Override
    public int calculateHeuristic(HashMap<Point, SquareState> board) {
        return 0;
    }
}
