/**
 * Created by Simon on 2016-11-07.
 */
public class Driver {
    public static void main(String[] args) {

        Player p1 = new MiniMaxPlayer();
        HeuristicCalculator heuristic = new SimonHeuristic1();
        p1.setHeuristicCalculator(heuristic);

        Player p2 = new MiniMaxPlayer();
        HeuristicCalculator secondHeuristic = new SimonHeuristic2();
        p2.setHeuristicCalculator(secondHeuristic);

        Player p3 = new MiniMaxPlayer();
        HeuristicCalculator thirdHeuristic = new SimonHeuristic3();
        p3.setHeuristicCalculator(thirdHeuristic);

        Player p4 = new GreedyPlayer();
        HeuristicCalculator greedyPlayer = new SimonHeuristic3();
        p4.setHeuristicCalculator(greedyPlayer);

        Player p5 = new MiniMaxPlayer();
        HeuristicCalculator fourthHeuristic = new HarrisonHeuristic1();
        p5.setHeuristicCalculator(fourthHeuristic);

        Player p6 = new MiniMaxPlayer();
        HeuristicCalculator fifthHeuristic = new HarrisonHeuristic2();
        p6.setHeuristicCalculator(fifthHeuristic);

        Player p7 = new MiniMaxPlayer();
        HeuristicCalculator sixthHeuristic = new HarrisonHeuristic2();
        p7.setHeuristicCalculator(sixthHeuristic);

        Board board = new Board(p7,p3);

        board.startNewGame();
    }
}
