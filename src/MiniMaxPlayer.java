import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Harrison on 2016-11-07.
 */
public class MiniMaxPlayer extends Player {

    // TODO: 2016-11-14 Create children of root
    // TODO: 2016-11-14  Create children of first child and add them to fringe
    // TODO: 2016-11-14 Repeat until depth reached
    // TODO: 2016-11-14 Evaluate heuristic of leaf node. Assign to alpha/beta depending depth
    // TODO: 2016-11-14 Alpha:max(starts at -infinity) Beta:min(starts at +infinity)
    // TODO: 2016-11-14 Keep depth to see if max or min
    // TODO: 2016-11-14 Local alpha beta comparator value for each node
    // TODO: 2016-11-14 When creating children initialize their alpha beta value to those of the parent


    private Node current;
    private final int DEPTH = 3;


    public void findChildren(Node node){

    }

    public void findChildren(){
        findChildren(current);
    }

    public void updateCurrent(Node newRoot){
        this.current = newRoot;

    }




    public void generateTree(){
        int currentHeight = 0;
        LinkedList<Node> frontier = new LinkedList<Node>();


        for(int i=0;i<DEPTH;i++){

        }
    }

}
