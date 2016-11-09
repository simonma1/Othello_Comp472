import java.util.HashMap;

public abstract class Player {

    private HeuristicCalculator heuristicCalculator;
    private Board board;
    private Turn color;


    public Player() {

    }

    public abstract void findNextMove(Board updatedBoard, Turn turn);

    public void updateBoard(Board updatedBoard, Turn turn) {
        board = updatedBoard;
    }

    public Turn getColor() {
        return color;
    }

    public void setColor(Turn color) {
        this.color = color;
    }

}
