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



   private HashMap<Point, SquareState> board = new HashMap<>(BOARD_HEIGHT, BOARD_WIDTH);


    public Board(Player p1){
        playerOne = p1;
    }

    public Board(Player p1, Player p2){
        playerOne = p1;
        playerTwo = p2;
    }

    public void startNewGame(){

    }

}
