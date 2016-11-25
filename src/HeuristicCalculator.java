import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-07.
 */
public interface HeuristicCalculator extends Serializable {
    public int calculateHeuristic(Board board);

    int calculateHeuristic(HashMap<Point, SquareState> board);
}
