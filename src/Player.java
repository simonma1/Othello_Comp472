import java.util.HashMap;

public abstract class Player {

    private HeuristicCalculator heuristicCalculator;
    private Board board;


    public Player() {

    }

    public abstract void findNextMove(Board updatedBoard);

    public void updateBoard(Board updatedBoard) {
        board = updatedBoard;
    }


}
