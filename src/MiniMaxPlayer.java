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
        doMiniMax(root);

        return null;
    }

    private void doMiniMax(Node root) {
        findBestChildHeuristicValue(root);
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
}
