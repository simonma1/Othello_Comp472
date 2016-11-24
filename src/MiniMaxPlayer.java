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
        stack = new LinkedList<>();
        generateNodes(root);
        Node current;
        Board nextBoard = null;
        Random rand = new Random();

        while(!stack.isEmpty()) {
            current = stack.pop();//Removes the last node added to the stack
            Node parent = current.getParent();//add check if parent is null meaning we are back to the root

            if (parent == null) {
                nextBoard = findBestChildHeuristicValue(root);
            } else {//Checks to see if the game might be over
                if(Math.abs(current.getMiniMaxValue()) == Constant.MAXBETAVALUE && isGameOverCheck(current.getBoardValue())){
                    int hValue = heuristicValueIfGameOver(current.getBoardValue(), turn);
                    current.setMiniMaxValue(hValue);
                    System.out.println("AM I HERE");
                    stack.push(current);//Repush the node on the stack so that will execute one of the other check

                }else if (current.getDepth() == MINIMAXDEPTH && Math.abs(parent.getMiniMaxValue()) == Constant.MAXBETAVALUE) {//The current node is a leaf and its parent's value hasn't been defined
                    int heuristicValue = heuristicCalculator.calculateHeuristic(current.getBoardValue(), turn);
                    current.setMiniMaxValue(heuristicValue);
                    parent.setMiniMaxValue(heuristicValue);

                    if (parent.isMaxNode()) {
                        parent.setAlpha(heuristicValue);
                    } else {
                        parent.setBeta(heuristicValue);
                    }
                    doPruning(current);

                } else if (current.getDepth() == MINIMAXDEPTH) {//Node is a leaf node but the value of the parent has already been updated
                    int heuristicValue = heuristicCalculator.calculateHeuristic(current.getBoardValue(), turn);
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
                    doPruning(current);

                } else if (Math.abs(parent.getMiniMaxValue()) == Constant.MAXBETAVALUE && Math.abs(current.getMiniMaxValue()) != Constant.MAXBETAVALUE) {//Not a leaf node and the parent does't have a value set
                    int currentValue = current.getMiniMaxValue();
                    parent.setMiniMaxValue(currentValue);

                    if (parent.isMaxNode()) {
                        parent.setAlpha(currentValue);
                    } else {
                        parent.setBeta(currentValue);
                    }

                } else if (Math.abs(current.getMiniMaxValue()) == Constant.MAXBETAVALUE) {//Case when no heuristic value has been given for this part of the tree which is not a node
                    current.setAlpha(parent.getAlpha());
                    current.setBeta(parent.getBeta());
                    generateNodes(current);

                } else if (Math.abs(current.getMiniMaxValue()) != Constant.MAXBETAVALUE && Math.abs(parent.getMiniMaxValue()) != Constant.MAXBETAVALUE) {//cases where we need to compare the parent's value with the child's to see the one that would be selected
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
                    doPruning(current);
                }
            }
        }
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
                current.addChild(child);
                current.setDefaultMiniMaxValue();
                stack.push(child);//Inserts the element as the first of the list
            }

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

    private void doPruning(Node current){
        Node parent = current.getParent();

        if (parent.isMaxNode()){
            if (current.getMiniMaxValue() > parent.getBeta()){
                while(stack.peek().getParent() == parent){
                    stack.pop();
                }
            }
        }else{
            if(current.getMiniMaxValue() < parent.getAlpha()){
                while(stack.peek().getParent() == parent){
                    stack.pop();
                }
            }
        }
    }

    public void resetPlayer(){
        root = null;
        stack = null;
    }

    private boolean isGameOverCheck(Board board){

        board.setNumBlackPieces(board.getBlackPieces().size());
        board.setNumWhitePieces(board.getWhitePieces().size());
        return board.checkIfGameOver();
    }

    private int heuristicValueIfGameOver(Board board, Turn turn){
        //Method only reached if there is a winner
        Board boardCopy = board.clone();

        boardCopy.printWinner();//Sets the winner
        if (boardCopy.getGameWinner() == turn){
            return 1000000;
        }else {
            return -1000000;
        }
    }

}
