import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BaDaS on 2016-11-07.
 */
public class GreedyPlayer extends Player {

    @Override
    public void findNextMove(Board updatedBoard) {
        ArrayList<Board> nextStates = NextMoveGenerator.generateNextStates(updatedBoard);
    }
}
