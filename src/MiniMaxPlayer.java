import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

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
    private Turn turn;



    @Override
    public Board executifyMove(Board currentBoard) {
        root = new Node(currentBoard);
        turn = root.getBoardValue().getTurn();
        generateNodes(root);
        Node current = null;
        Board nextBoard = null;
        Random rand = new Random();

        while(!stack.isEmpty()) {
            current = stack.pop();//Removes the last node added to the stack
            Node parent = current.getParent();//add check if parent is null meaning we are back to the root

            if (parent == null) {
                nextBoard = findBestChildHeuristicValue(root);
                System.out.println("HEREERERERE");
            } else {
                if (current.getDepth() == MINIMAXDEPTH && Math.abs(parent.getMiniMaxValue()) == Constant.MAXBETAVALUE) {//The current node is a leaf and its parent's value hasn't been defined
                    //int heuristicValue = heuristicCalculator.calculateHeuristic(current.getBoardValue().getBoard());
                    int heuristicValue = rand.nextInt(150);
                    current.setMiniMaxValue(heuristicValue);
                    parent.setMiniMaxValue(heuristicValue);
                    System.out.println("1st check" + current.getMiniMaxValue());

                    if (parent.isMaxNode()) {
                        parent.setAlpha(heuristicValue);
                    } else {
                        parent.setBeta(heuristicValue);
                    }

                } else if (current.getDepth() == MINIMAXDEPTH) {//Node is a leaf node but the value of the parent has already been updated
                    //int heuristicValue = heuristicCalculator.calculateHeuristic(current.getBoardValue().getBoard());
                    int heuristicValue = rand.nextInt(150);
                    current.setMiniMaxValue(heuristicValue);

                    if (parent.isMaxNode()) {
                        if (heuristicValue > parent.getMiniMaxValue()) {
                            parent.setMiniMaxValue(heuristicValue);
                            parent.setAlpha(heuristicValue);
                        }
                    } else {//parent is a min node
                        if (heuristicValue < parent.getMiniMaxValue()) {
                            parent.setMiniMaxValue(heuristicValue);
                            parent.setBeta(heuristicValue);
                        }
                    }
                    System.out.println("2nd check" + current.getMiniMaxValue());
                    System.out.println("2st check parent " + parent.getMiniMaxValue());
                    System.out.println("2nd check depth" + current.getDepth());

                } else if (Math.abs(parent.getMiniMaxValue()) == Constant.MAXBETAVALUE && Math.abs(current.getMiniMaxValue()) != Constant.MAXBETAVALUE) {//Not a leaf node and the parent does't have a value set
                    int currentValue = current.getMiniMaxValue();
                    System.out.println("3rd check paretn before" + parent.getMiniMaxValue());
                    parent.setMiniMaxValue(currentValue);

                    if (parent.isMaxNode()) {
                        parent.setAlpha(currentValue);
                    } else {
                        parent.setBeta(currentValue);
                    }
                    System.out.println("3rd check " + current.getMiniMaxValue());
                    System.out.println("3rd check paretn " + parent.getMiniMaxValue());
                    System.out.println("3rd check depth " + current.getDepth());



                } else if (Math.abs(current.getMiniMaxValue()) == Constant.MAXBETAVALUE) {//Case when no heuristic value has been given for this part of the tree which is not a node
                    System.out.println("HOLAAAAAAAAAAAAAA ");
                    current.setAlpha(parent.getAlpha());
                    current.setBeta(parent.getBeta());
                    generateNodes(current);

                    System.out.println("4th check " + current.getMiniMaxValue());
                    System.out.println("4th check parent " + parent.getMiniMaxValue());

                } else if (Math.abs(current.getMiniMaxValue()) != Constant.MAXBETAVALUE && Math.abs(parent.getMiniMaxValue()) != Constant.MAXBETAVALUE) {//cases where we need to compare the parent's value with the child's to see the one that would be selected
                    System.out.println("Last check  " + current.getMiniMaxValue());
                    System.out.println("Last check  depth " + current.getDepth());
                    int currentValue = current.getMiniMaxValue();
                    if (parent.isMaxNode()) {
                        if (currentValue > parent.getMiniMaxValue()) {
                            parent.setMiniMaxValue(currentValue);
                            parent.setAlpha(currentValue);
                        }
                    } else {
                        if (currentValue < parent.getMiniMaxValue()) {
                            parent.setMiniMaxValue(currentValue);
                            parent.setBeta(currentValue);
                        }
                    }
                }
            }
        }
        System.out.println("Executify end" + current.getAlpha());
        return nextBoard;
    }

    //Adds all element from the node provided to the left-most leaves to the stack
    private void generateNodes(Node root) {
        Node current = root;
        ArrayList<Board> nextMoves = null;
        stack.push(current);

        while(current.getDepth() != MINIMAXDEPTH) {
            nextMoves = NextMoveGenerator.generateNextStates(current.getBoardValue());
            //Creates child of the current node and adds them to the stack
            if(nextMoves.size() ==0){
                nextMoves.add(current.getBoardValue());
            }
            for (Board move : nextMoves) {
                Node child = new Node(move, current.getDepth() + 1, current, current.getAlpha(), current.getBeta());
                child.setDefaultMiniMaxValue();
                Turn tempTurn = child.findTurn(turn);
                child.getBoardValue().setTurn(tempTurn);
                System.out.println("generateNodes " + child.getBoardValue().toString());
                System.out.println("generate nodes depth " + current.getDepth());
                current.addChild(child);
                current.setDefaultMiniMaxValue();
                System.out.println("generate nodes Value" + current.getMiniMaxValue());
                stack.push(child);//Inserts the element as the first of the list
            }

            System.out.println("generating " + current.getChildren().size());
            current = stack.peekFirst();//Last element added

        }

    }


    //Probably delete
    private Board findBestChildHeuristicValue(Node root) {
        Board nextBoard = null;
        for (Node child : root.getChildren()) {
            if (child.getMiniMaxValue()==root.getMiniMaxValue() ) {
                nextBoard = child.getBoardValue();
            }
        }
        return nextBoard;
    }


}
