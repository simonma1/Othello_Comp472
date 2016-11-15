import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Harrison on 2016-11-07.
 */
public class GreedyPlayer extends Player {

    public GreedyPlayer() {
        super();
    }

    @Override
    public Board executifyMove(Board currentBoard) {
        ArrayList<Board> nextMoves = NextMoveGenerator.generateNextStates(currentBoard);
        int bestHeuristicValue = Constant.MAXBETAVALUE;
        Board theMoveAssociatedWithTheBestHeuristicValue = null;
        for (Board move : nextMoves) {
            int thisMovesHeuristicValue = super.getHeuristicCalculator().calculateHeuristic(move.getBoard());
            if (thisMovesHeuristicValue < bestHeuristicValue) {
                bestHeuristicValue = thisMovesHeuristicValue;
                theMoveAssociatedWithTheBestHeuristicValue = move;
            }
        }

        return theMoveAssociatedWithTheBestHeuristicValue;
    }
}
