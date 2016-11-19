import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Simon on 2016-11-13.
 */
public class Node implements Serializable{

    private Node parent = null;
    private ArrayList<Node> children = new ArrayList<Node>();
    private Board boardValue = null;


    private int alpha = Constant.MINALPHAVALUE;
    private int beta = Constant.MAXBETAVALUE;
    private int miniMaxValue;
    private int depth; //Even for max odd for min

    public Node(Board board, int depth){
        this.depth = depth;
        this.depth = depth;
    }

    public Node(Board board){
        this.boardValue = board;
        this.depth = 0;
        this.miniMaxValue = Integer.MIN_VALUE;
    }

    public Node(Board board, int depth, Node parent, int alpha, int beta){
        this.boardValue = board;
        this.depth = depth;
        this.parent = parent;
        this.alpha = alpha;
        this.beta = beta;
    }


    public void addChild(Node child){
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setDefaultMiniMaxValue(){
        if(this.depth % 2 == 0) {//Max Node
            this.miniMaxValue = Constant.MINALPHAVALUE;
        }else{
            this.miniMaxValue = Constant.MAXBETAVALUE;
        }
    }

    public boolean isMaxNode() {
        if (this.depth % 2 == 0) {//Max Node
            return true;
        }else{
            return false;
        }
    }
}
