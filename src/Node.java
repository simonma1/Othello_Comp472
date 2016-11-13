import java.util.ArrayList;

/**
 * Created by Simon on 2016-11-13.
 */
public class Node {

    private Node parent = null;
    private ArrayList<Node> children = null;
    private Board value = null;

    public Node(){

    }

    public Node(Board board){
        this.value = board;
    }

    public void add(Node child){
        children.add(child);
    }

    public ArrayList<Node> getChildren(){
        return children;
    }
}
