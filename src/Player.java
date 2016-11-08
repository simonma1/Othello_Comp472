import java.util.HashMap;

public abstract class Player {

    private HeuristicCalculator heuristicCalculator;
    private Board board;
    private Turn color;


    public Player() {

    }

    public abstract void findNextMove(Board updatedBoard);

    public void updateBoard(Board updatedBoard) {
        board = updatedBoard;
    }

    public Turn getColor() {
        return color;
    }

    public void setColor(Turn color) {
        this.color = color;
    }

}
