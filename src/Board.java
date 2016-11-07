import java.awt.*;
import java.util.HashMap;

/**
 * Created by Simon on 2016-11-07.
 */
public class Board {

    private Player playerOne;
    private Player playerTwo;


    private final int BOARD_WIDTH = 8;
    private final int BOARD_HEIGHT = 8;

    private HashMap<Point, SquareState> board = new HashMap<>(BOARD_HEIGHT * BOARD_WIDTH);


    public Board(Player p1){
        playerOne = p1;
    }

    public Board(Player p1, Player p2){
        playerOne = p1;
        playerTwo = p2;
    }

    public void startNewGame(){
        Point currentPoint;

        //Sets all square on the board to be empty
        for(int i=0; i<BOARD_HEIGHT; i++){
            for(int j=0; j<BOARD_WIDTH;j++){
                currentPoint = new Point(i,j);
                board.put(currentPoint,SquareState.EMPTY);
            }
        }

        //Puts the 4 initial pieces on the board
        board.put(new Point(3,3),SquareState.WHITE);
        board.put(new Point(3,4),SquareState.BLACK);
        board.put(new Point(4,3),SquareState.BLACK);
        board.put(new Point(4,4),SquareState.WHITE);
    }

    public HashMap<Point, SquareState> getBoard() {
        return board;
    }

    public void setBoard(HashMap<Point, SquareState> board) {
        this.board = board;
    }



}
