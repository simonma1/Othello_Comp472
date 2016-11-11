import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-07.
 */
public class GreedyPlayer extends Player {

    @Override
    public HashMap<Point,SquareState> findNextMove(Board updatedBoard, Turn turn) {
        HashMap<Point,SquareState> move = null;
        ArrayList<Board> nextStates = NextMoveGenerator.generateNextStates(updatedBoard, turn);

        return move;
    }
}
