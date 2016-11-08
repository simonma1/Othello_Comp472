import java.awt.*;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-07.
 */
public interface HeuristicCalculator {
    public int calculateHeuristic(HashMap<Point, SquareState> stateMap);
}
