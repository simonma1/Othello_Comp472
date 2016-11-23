import java.awt.*;
import java.util.HashMap;

/**
 * Created by Simon on 2016-11-22.
 */
public class SimonHeuristic1 implements HeuristicCalculator {

    private SquareState playerColor;
    private SquareState opponentColor;
    private int currentHeuristicValue;
    private final int cornerValue = 1000;
    private final int sideValue = 100;
    private int lowerBound = 1;
    private int higherBound = Board.BOARD_HEIGHT - 2;

    @Override
    public int calculateHeuristic(Board board, Turn turn) {
        HashMap<Point,SquareState> currentBoardState = board.getBoard();
        setColors(turn);
        currentHeuristicValue = 0;


        for(int i=0; i<Board.BOARD_HEIGHT; i++){
            for(int j=0; j<Board.BOARD_WIDTH; j++){
                Point p = new Point(i,j);

                if(isCornerPoint(p)){//Corners are worth the most
                    if(currentBoardState.get(p) == playerColor){
                        currentHeuristicValue += cornerValue;
                    }else if(currentBoardState.get(p) == opponentColor){
                        currentHeuristicValue -= cornerValue;
                    }

                }else if(isSidePoint(p)){//sides are worth less than corner but more than other spots
                    if(currentBoardState.get(p) == playerColor){
                        currentHeuristicValue += sideValue;
                    }else if(currentBoardState.get(p) == opponentColor){
                        currentHeuristicValue -= sideValue;
                    }

                }else{

                }


            }
        }
        return currentHeuristicValue;
    }

    @Override
    public int calculateHeuristic(HashMap<Point, SquareState> board) {
        return 0;
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

    private boolean isCornerPoint(Point point){
        if((point.getX() == 0 || point.getX() == Board.BOARD_HEIGHT-1)
                && (point.getY() == 0 || point.getY() == Board.BOARD_WIDTH-1)){
            return true;
        }else{
            return false;
        }
    }

    private boolean isSidePoint(Point p){
        if(((p.getX() == 0 || p.getX() == Board.BOARD_HEIGHT-1) && p.getY() > 0 && p.getY()<Board.BOARD_WIDTH-1)
                || ((p.getY() == 0 || p.getY() == Board.BOARD_WIDTH-1) && p.getX() > 0 && p.getX() < Board.BOARD_HEIGHT-1)){
            return true;
        } return false;
    }

}
