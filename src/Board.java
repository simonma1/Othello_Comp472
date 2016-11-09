import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Simon on 2016-11-07.
 */
public class Board {

    private Player playerOne;
    private Player playerTwo;
    private Turn turn = Turn.BLACK;
    private boolean gameOver = false;

    private final int BOARD_WIDTH = 8;
    private final int BOARD_HEIGHT = 8;

    private HashMap<Point, SquareState> board = new HashMap<>(BOARD_HEIGHT * BOARD_WIDTH);

    private ArrayList<Point> possibleMoves;

    /*
    * List of things to do
    * 1. Take string representation of the board as input
    * 2. Find the point that was added from this input and pass it to check validity
    * 3. Make function to find validity of a move given a point that was added
    * 4. Create function to check if the game is over
    * 5. Function to update the board if input is acceptable
    * 6. Function to find the next move
    * */


    public Board(Player p1, Turn color){
        playerOne = p1;
        playerOne.setColor(color);
    }

    public Board(Player p1){
        playerOne = p1;
        playerOne.setColor(Turn.WHITE);
    }

    public Board(Player p1, Player p2){
        playerOne = p1;
        playerTwo = p2;
    }

    public void startNewGame(){
        Point currentPoint;

        //Sets all square on the board to be empty
        for(int i=0; i<BOARD_HEIGHT; i++){
            for(int j=0; j<BOARD_WIDTH;j++){
                currentPoint = new Point(i,j);
                board.put(currentPoint,SquareState.EMPTY);
            }
        }

        //Puts the 4 initial pieces on the board
        board.put(new Point(3,3),SquareState.WHITE);
        board.put(new Point(3,4),SquareState.BLACK);
        board.put(new Point(4,3),SquareState.BLACK);
        board.put(new Point(4,4),SquareState.WHITE);

        System.out.println(this.toString());

        play();
    }

    private void play(){
        while(!gameOver){
            if (playerOne.getColor() == turn){
               // playerOne.findNextMove(this);//Maybe pass the hashmap instead
            }else{
                if(playerTwo != null){// playerTwo is human
                   // playerTwo.findNextMove(this);
                }else{//PlayerTwo is human
                    getNextMoveFromInput();
                }
            }

            gameOver = checkIfGameOver();
            updateTurn();
        }
    }

    private boolean checkIfGameOver() {
        if(true){
            return true;
        }else{
            return false;
        }
    }

    private void getNextMoveFromInput() {
        Scanner keyboard = new Scanner(System.in);
        String input;
        boolean isValidMove = false;

        while(!isValidMove) {
            System.out.println("Your turn. What is your next move?");
            System.out.println("You can enter either a point (e.g. x,y) which will be where you put your next piece, " +
                    "or a string of what the board will look like");

            Pattern pointPattern = Pattern.compile("[0-7],[0-7]");
            Pattern stringPattern = Pattern.compile("(\\([BW0]{8}\\)){8}");
            Pattern listPattern = Pattern.compile("\\(");


            if (keyboard.hasNext(pointPattern)) {
                Point userPoint;

                input = keyboard.nextLine();
                String[] pointData = input.split(",");
                int a = Integer.parseInt(pointData[0]);
                int b = Integer.parseInt(pointData[1]);
                System.out.println(input + " Point a: " + a + " Point b: " + b);

                userPoint = new Point(a, b);

                isValidMove = checkIfValidMove(userPoint);

                if (isValidMove) {
                    updateBoard();
                }


            }else if(keyboard.hasNext(stringPattern)) {

                input = keyboard.nextLine();
                System.out.println(input + " String");

                //add valid input check
            }else if(keyboard.hasNext(listPattern)){
                input = keyboard.nextLine();
                System.out.println(input + " List");
                //add parsing taking into account the line break
                //add valid input check
            }else{
                    input = keyboard.nextLine();
                    System.out.println(input + " is an invalid input. Please try again");
                }
                //When checking if valid move maybe find the point where the user made the move and interface it
            }

    }

    private void updateBoard() {//Updating the board is done here or somewhere else?
    }

    private boolean checkIfValidMove(Point point) {//Maybe the check should be done somewhere else
        return true;
    }

    private void updateTurn() {
        if (turn == Turn.BLACK) {
            turn = Turn.WHITE;
        }else {
            turn = Turn.BLACK;
        }
    }

    public HashMap<Point, SquareState> getBoard() {
        return board;
    }

    public void setBoard(HashMap<Point, SquareState> board) {
        this.board = board;
    }


    public String toString(){
        String res = "";

        for(int i=0;i<BOARD_HEIGHT;i++){
            for(int j=0; j<BOARD_WIDTH;j++){
                Point current = new Point(i,j);
                if(board.get(current) == SquareState.BLACK){
                    res += "B";
                }else if(board.get(current) == SquareState.WHITE){
                    res += "W";
                }else{
                    res += "0";
                }
            }
            res +=" \n";
        }

        return res;
    }

    public ArrayList<Point> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<Point> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
}
