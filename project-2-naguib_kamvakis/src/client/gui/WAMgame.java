package client.gui;

import java.util.*;

/**
 * This class acts as the game/board and keeps track of
 * every mole's status.
 */
public class WAMgame {

    /** The number of rows on the board */
    private int rows;
    /** The number of rows on the board */
    private int columns;
    /** The status of the game (running or not) */
    private boolean status;
    /** an array of player scores */
    private int[] scores;
    /** an array moles used for checking status */
    private boolean[] moles;
    /** A boolean matrix of moles used for GUI
     * true : mole is up
     * false : mole is down */
    private boolean[][] board;
    /** list of the observers (players) */
    private List<Observer<WAMgame>> observers;


    /**
     * WAMGame constructor
     * @param rows number of rows on board
     * @param columns number of columns on board
     * @param players number of players that will play
     */
    public WAMgame(int rows, int columns, int players ){
    this.observers = new LinkedList<>();
    this.status = true;
    this.rows = rows;
    this.columns = columns;
    this.scores = new int[players];
    this.moles = new boolean[rows*columns];
    //Creates a 2D array of booleans to monitor the state of each mole. False is down, True is up. All moles start down
    this.board = new boolean[columns][rows];
    for(int col=0; col<columns; col++) {
        for(int row=0; row < rows; row++) {
            board[col][row] = false;
        }
    }
    for(int x = 0;x<players;x++){
        scores[x] = 0;
    }
    for(int d = 0;d<(rows*columns);d++){
        moles[d] = false;
    }

}

    /**
     * This method adds an observer (player) the the list
     * @param observer the observer being added
     */
    public void addObserver(Observer<WAMgame> observer) {
    this.observers.add(observer);
    }

    /**
     * This method calls for all observers to update their boards.
     */
    private void alertObservers() {
        for (Observer o: this.observers ) {
            o.update(this);
        }
    }

    /**
     * Used to determine what picture to use for the button
     * @param mole mole number
     * @return the boolean value of the mole, signifying if it should be up or down
     */
    public boolean getMoleAt(int mole){
    return board[mole%columns][mole/columns];
}

    /**
     * Used by clienthandler to determine if points should be awarded/deducted.
     * @param mole mole number
     * @return if mole is up or down
     */
    public boolean getMoleStatus(int mole){
        return moles[mole];
}
    /**
     * changes the boolean value of a mole to true and notifies observers of a change
     * @param mole the id number of the mole that should be raised
     */
    public void moleUp(int mole){
        board[mole%columns][mole/columns] = true; //bless whoever came up with this formula
        moles[mole] = true;
        alertObservers();
}

    /**
     * changes the boolean value of a mole to false and notifies observers of a change
     * @param mole the id number of the mole that should be lowered
     */
    public void moleDown(int mole){
    board[mole%columns][mole/columns] = false;
    moles[mole] = false;

    alertObservers();
}

    /**
     * signals the end of the game, which will close the loop in the client.
     */
    public void endGame(){
    this.status = false;
    alertObservers();
}

    /**
     * This method returns the status of the game.
     * @return a boolean value representing whether or not the game is running
     */
    public boolean getStatus(){
    return this.status;
}


    /**
     * This method updates scores array.
     * @param scores new scores
     */
    public void updateScores(int[] scores){
        this.scores = scores;
        alertObservers();
}

    /**
     * This method returns the score array.
     * @return int[] of scores
     */
    public int[] getScores(){
        return scores;
}

    /**
     * This method closes the game.
     */
    public void close() {
        alertObservers();
    }



}
