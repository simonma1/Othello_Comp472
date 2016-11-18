import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Harrison on 2016-11-07.
 */
public class MiniMaxPlayer extends Player {

    /*
    Potential problem: Watch out that the color whose turn it is is known by the heuristic calculator
    */

    // TODO: 2016-11-14 Create children of root
    // TODO: 2016-11-14  Create children of first child and add them to fringe
    // TODO: 2016-11-14 Repeat until depth reached
    // TODO: 2016-11-14 Evaluate heuristic of leaf node. Assign to alpha/beta depending depth
    // TODO: 2016-11-14 Alpha:max(starts at -infinity) Beta:min(starts at +infinity)
    // TODO: 2016-11-14 Keep depth to see if max or min
    // TODO: 2016-11-14 Local alpha beta comparator value for each node
    // TODO: 2016-11-14 When creating children initialize their alpha beta value to those of the parent


    private Node root;
    private final int MINIMAXDEPTH = 3;
    LinkedList<Node> stack = new LinkedList<Node>();//Our stack



    @Override
    public Board executifyMove(Board currentBoard) {
        root = new Node(currentBoard);
        generateNodes(root);
        Node current = null;

        while(!stack.isEmpty()){
            current = stack.pop();//Removes the last node added to the stack
            Node parent = current.getParent();//add check if parent is null meaning we are back to the root

            if(current.getDepth() == MINIMAXDEPTH  && Math.abs(parent.getMiniMaxValue()) == Constant.MAXBETAVALUE){//The current node is a leaf and its parent's value hasn't been defined
                int heuristicValue = heuristicCalculator.calculateHeuristic(current.getBoardValue().getBoard());
                current.setMiniMaxValue(heuristicValue);
                parent.setMiniMaxValue(heuristicValue);

                if(current.isMaxNode()){
                    parent.setAlpha(heuristicValue);
                }else{
                    parent.setBeta(heuristicValue);
                }

            }else if(current.getDepth() == MINIMAXDEPTH){//Node is a leaf node but the value of the parent has already been updated
                int heuristicValue = heuristicCalculator.calculateHeuristic(current.getBoardValue().getBoard());
                current.setMiniMaxValue(heuristicValue);

                if(parent.isMaxNode()){
                    if(heuristicValue > parent.getMiniMaxValue()){
                        parent.setMiniMaxValue(heuristicValue);
                        parent.setAlpha(heuristicValue);
                    }
                }else{//parent is a min node
                    if (heuristicValue < parent.getMiniMaxValue()){
                        parent.setMiniMaxValue(heuristicValue);
                        parent.setBeta(heuristicValue);
                    }
                }

            }else if (Math.abs(parent.getMiniMaxValue()) == Constant.MAXBETAVALUE && Math.abs(current.getMiniMaxValue())!= Constant.MAXBETAVALUE){//Not a leaf node and the parent does't have a value set
                int currentValue = current.getMiniMaxValue();
                parent.setMiniMaxValue(currentValue);

                if (parent.isMaxNode()){
                    parent.setAlpha(currentValue);
                }else{
                    parent.setBeta(currentValue);
                }
            }
        }

        findBestChildHeuristicValue(root);
        return null;
    }

    //Adds all element from the node provided to the left-most leaves to the stack
    private void generateNodes(Node root) {
        Node current = root;
        ArrayList<Board> nextMoves = null;

        while(current.getDepth() != MINIMAXDEPTH) {
            nextMoves = NextMoveGenerator.generateNextStates(current.getBoardValue());
            //Creates child of the current node and adds them to the stack
            for (Board move : nextMoves) {
                Node child = new Node(move, current.getDepth() + 1, current, current.getAlpha(), current.getBeta());
                current.addChild(child);
                current.setDefaultMiniMaxValue();
                stack.push(child);//Inserts the element as the first of the list
            }

            current = stack.peekFirst();//Last element added
        }


    }


    //Probably delete
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
