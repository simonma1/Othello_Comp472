import java.awt.*;
import java.util.HashMap;

public abstract class Player {

    private HeuristicCalculator heuristicCalculator;
    private Board board;
    private Turn color;


    public Player() {

    }

    public abstract HashMap<Point,SquareState> findNextMove(Board updatedBoard, Turn turn);


    public Turn getColor() {
        return color;
    }

    public void setColor(Turn color) {
        this.color = color;
    }

}
