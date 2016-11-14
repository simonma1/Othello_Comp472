import java.util.ArrayList;

/**
 * Created by Simon on 2016-11-13.
 */
public class Node {

    private Node parent = null;
    private ArrayList<Node> children = null;
    private Board boardValue = null;


    private int alpha = Constant.MINALPHAVALUE;
    private int beta = Constant.MAXBETAVALUE;
    private int miniMaxValue;
    private int depth; //Even for max odd for min

    public Node(){

    }

    public Node(Board board){
        this.boardValue = board;
    }

    public void add(Node child){
        children.add(child);
    }

    public ArrayList<Node> getChildren(){
        return children;
    }

    public Board getBoardValue() {
        return boardValue;
    }

    public void setBoardValue(Board boardValue) {
        this.boardValue = boardValue;
    }

    public int getMiniMaxValue() {
        return miniMaxValue;
    }

    public void setMiniMaxValue(int miniMaxValue) {
        this.miniMaxValue = miniMaxValue;
    }
}
