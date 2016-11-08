import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-07.
 */
public class MinMaxPlayer extends Player {

    @Override
    public void findNextMove(Board updatedBoard) {
        ArrayList<Board> nextStates = NextMoveGenerator.generateNextStates(updatedBoard);
    }
}
