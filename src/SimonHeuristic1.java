import java.awt.*;
import java.util.HashMap;

/**
 * Created by Simon on 2016-11-22.
 */
public class SimonHeuristic1 implements HeuristicCalculator {

    /*
    * This heuristic will give a wait to each board position
    * Corners will be the most favorable followed by all position by the side of the board
    * The closer to the middle the smaller the value will be
    * */

    private SquareState playerColor;
    private SquareState opponentColor;
    private int currentHeuristicValue;
    private final int cornerValue = 1000;
    private final int sideValue = 100;
    private final int otherValue = 100;


    @Override
    public int calculateHeuristic(Board board, Turn turn) {
        HashMap<Point,SquareState> currentBoardState = board.getBoard();
        setColors(turn);
        currentHeuristicValue = 0;


        for(int i=0; i<Board.BOARD_HEIGHT; i++){
            for(int j=0; j<Board.BOARD_WIDTH; j++){
                Point p = new Point(i,j);
                int xCord = (int)p.getX();
                int yCord = (int)p.getY();

                if(isCornerPoint(p)){//Corners are worth the most
                    if(currentBoardState.get(p) == playerColor){
                        currentHeuristicValue += cornerValue;
                    }else if(currentBoardState.get(p) == opponentColor){
                        currentHeuristicValue -= cornerValue;
                    }

                }else if(isSidePoint(p)){//sides are worth less than corner but more than other spots
                    if(currentBoardState.get(p) == playerColor){
                        if(p.getY() == 1 && p.getY() ==6){
                            currentHeuristicValue +=1;
                        }else{
                            currentHeuristicValue += sideValue;
                        }
                    }else if(currentBoardState.get(p) == opponentColor){
                        if(p.getY() == 1 && p.getY() ==6){
                            currentHeuristicValue -= 1;
                        }else{
                            currentHeuristicValue -= sideValue;
                        }
                    }

                }else{
                    if(xCord == 1 || xCord == 6 || yCord == 1 || yCord == 6){
                        if(currentBoardState.get(p) == playerColor){
                            currentHeuristicValue += otherValue/3;
                        }else if(currentBoardState.get(p) == opponentColor){
                            currentHeuristicValue -=otherValue/3;
                        }
                    }else if(xCord == 2 || xCord == 5 || yCord == 2 || yCord ==5){
                        if(currentBoardState.get(p) == playerColor){
                            currentHeuristicValue += otherValue/10;
                        }else if(currentBoardState.get(p) == opponentColor){
                            currentHeuristicValue -=otherValue/10;
                        }
                    }else if(xCord == 3 || xCord == 4 || yCord == 3 || yCord ==4){
                        if(currentBoardState.get(p) == playerColor){
                            currentHeuristicValue += otherValue/50;
                        }else if(currentBoardState.get(p) == opponentColor){
                            currentHeuristicValue -=otherValue/50;
                        }
                    }
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
