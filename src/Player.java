import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

public abstract class Player implements Serializable{

    protected HeuristicCalculator heuristicCalculator;
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

    public HeuristicCalculator getHeuristicCalculator() {
        return heuristicCalculator;
    }

    public void setHeuristicCalculator(HeuristicCalculator heuristicCalculator) {
        this.heuristicCalculator = heuristicCalculator;
    }
}
