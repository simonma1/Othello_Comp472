/**
 * Created by Simon on 2016-11-13.
 */
public class MiniMaxTree {

    private Node current;
    private final int DEPTH = 3;

    public MiniMaxTree(Board board){
        current = new Node(board);
    }

    public void findChildren(Node node){

    }

    public void findChildren(){
        findChildren(current);
    }

    public void updateCurrent(Node newRoot){
        this.current = newRoot;
        generateNextLevel();
    }

    public void generateNextLevel(){
        //Watch out for end game. Maybe count turn Number
        

    }
}
