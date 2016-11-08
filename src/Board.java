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
                if(playerTwo != null){// playerTwo is a computer
                   // playerTwo.findNextMove(this);
                }else{//PlayerTwo is human
                    getNextMoveFromInput();
                }
            }
            updateTurn();
        }
    }

    private void getNextMoveFromInput() {
        Scanner keyboard = new Scanner(System.in);
        String input;
        boolean isValidMove = false;

        System.out.println("Your turn. What is your next move?");
        System.out.println("You can either a point (e.g. x,y) which will be where you put your next piece, " +
                    "or a string of what the board will look like");

        Pattern pattern = Pattern.compile("[0-7],[0-7]");



       if(keyboard.hasNext(pattern)){
           Point userPoint;

           while(!isValidMove) {
               input = keyboard.nextLine();
               String[] pointData = input.split(",");
               int a = Integer.parseInt(pointData[0]);
               int b = Integer.parseInt(pointData[1]);
               System.out.println(input + " Point a: " + a + " Point b: " + b);

               userPoint = new Point(a, b);

               isValidMove = checkIfValidMove(userPoint);

               if (isValidMove){
                   updateBoard();
               }
           }


       }else{
           input = keyboard.nextLine();
           System.out.println(input + " String");
       }

        gameOver=true;//For testing only remove when done with the function


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
