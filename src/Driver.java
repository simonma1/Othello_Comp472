/**
 * Created by Simon on 2016-11-07.
 */
public class Driver {
    public static void main(String[] args) {

        Player p1 = new MiniMaxPlayer();
        HeuristicCalculator heuristic = new SimonHeuristic1();
        p1.setHeuristicCalculator(heuristic);
        Board board = new Board(p1);

        board.startNewGame();
    }
}
