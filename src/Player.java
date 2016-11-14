import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

public abstract class Player implements Serializable{

    private HeuristicCalculator heuristicCalculator;
    private Turn color;


    public Player() {

    }

    public abstract Board executifyMove(Board currentBoard);

    public Turn getColor() {
        return color;
    }

    public void setColor(Turn color) {
        this.color = color;
    }


}
