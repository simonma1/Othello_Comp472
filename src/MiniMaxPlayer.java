import java.util.ArrayList;
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


    private Node root;
    private final int DEPTH = 3;
    LinkedList<Node> frontier = new LinkedList<Node>();//Our stack


    public void findChildren(Node node){

    }

    public void findChildren(){
        findChildren(root);
    }

    public void updateCurrent(Node newRoot){
        this.root = newRoot;

    }




    public void generateTree(){
        int currentHeight = 0;
        LinkedList<Node> frontier = new LinkedList<Node>();


        for(int i=0;i<DEPTH;i++){

        }
    }

    @Override
    public Board executifyMove(Board currentBoard) {
        root.setBoardValue(currentBoard);
        doMiniMaxSearch(root);

        findBestChildHeuristicValue(root);
        return null;
    }

    private void doMiniMaxSearch(Node root) {
        ArrayList<Board> nextMoves = NextMoveGenerator.generateNextStates(root.getBoardValue());

        //Creates child of the root and adds them to the frontier
        for (Board move : nextMoves) {
            Node child = new Node(move, root.getDepth());
            root.addChild(child);
            frontier.add(child);
        }



    }

    private Board findBestChildHeuristicValue(Node root) {
        int highestHeuristicValue = Constant.MINALPHAVALUE;
        Board bestBoardAssociatedWithHighestHeuristicValue = null;
        for (Node child : root.getChildren()) {
            if (child.getMiniMaxValue() > highestHeuristicValue ) {
                highestHeuristicValue = child.getMiniMaxValue();
                bestBoardAssociatedWithHighestHeuristicValue = child.getBoardValue();
            }
        }
        return bestBoardAssociatedWithHighestHeuristicValue;
    }

    public void generateMiniMaxTree() {
        // TODO: 2016-11-15 create a stack S to hold nodes
        // TODO: 2016-11-15 S.push(root) push the root node on top of the stack
        // TODO: 2016-11-15 while (!S.isEmpty() the stack is not empty) {
        // TODO: 2016-11-15     if (S.top().getDepth() == 3 depth of Node on top of stack == 3) {
        // TODO: 2016-11-15         S.top().setMiniMaxValue(calculateHeuristicValue(S.top().getBoardValue()));
        // TODO: 2016-11-15         S.pop();   pop the this node since we're done with it
        // TODO: 2016-11-15     }
        // TODO: 2016-11-15     else {
        // TODO: 2016-11-15         if (S.top().getChildren() == null Node on top of stack has no children) {
        // TODO: 2016-11-15             children = generateChildren();
        // TODO: 2016-11-15             for each child in children {
        // TODO: 2016-11-15                 S.top().addChild(child);
        // TODO: 2016-11-15                 child.setParent(S.top());
        // TODO: 2016-11-15                 S.push(child);
        // TODO: 2016-11-15             }
        // TODO: 2016-11-15         }
        // TODO: 2016-11-15         else {
        // TODO: 2016-11-15             S.pop() pop this parent node from top of stack
        // TODO: 2016-11-15         }
        // TODO: 2016-11-15     }
        // TODO: 2016-11-15 }


    }
}
