/**
 * Created by Simon on 2016-11-07.
 */
public class Driver {
    public static void main(String[] args) {

        Player p1 = new MinMaxPlayer();
        Board board = new Board(p1);

        board.startNewGame();
        System.out.println(board.toString());
    }
}
