package rit.stu.act2;

import rit.stu.act1.StackNode;

/**
 * Chopper class implementation.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */

public class Chopper {
    /** StackNode representing the helicopter */
    private StackNode<Player>chopper;
    /** Integer representing the helicopter's maximum occupancy*/
    private static int MAX_OCCUPANCY;
    /** Integer representing the helicopter's current number of passengers*/
    private int numPassengers;
    /** Integer representing how many players the helicopter has flown to safety*/
    private int numRescued;

    //constructor
    /**
     * Chopper class constructor.
     * Creates an empty Chopper with a MAX_OCCUPANCY of 6.
     */
    public Chopper(){
        chopper = new StackNode<>();
        MAX_OCCUPANCY = 6;
        numPassengers = 0;
        numRescued = 0;
    }
    /**
     * Checks if Chopper is empty.
     * @return boolean.
     */
    public boolean isEmpty(){
        return numPassengers == 0;
    }
    /**
     * Checks if Chopper is full.
     * @return boolean.
     */
    public boolean isFull(){
        return numPassengers == 6;
    }

    /**
     * Returns the number of players rescued by the chopper.
     * @return numRescued
     */
    public int getNumRescued(){
        return numRescued;
    }

    /**
     * This method flies passengers on the chopper to safety.
     * Precondition: Helicopter should not be empty.
     */
    public void rescuePassengers() {
        while(!isEmpty()) {
            Player p = chopper.top();
            chopper.pop();
            numPassengers-=1;
            System.out.println("Chopper transported "+ p.toString() + " to safety!");
            numRescued+=1;
        }
    }
    /**
     * This method boards passengers on to the chopper. If the Chopper is already full,
     * it will fly the on board passengers to safety first and then return and board the
     * next player.
     */
    public void boardPassenger(Player player){
        if(isFull()){
            rescuePassengers();
            chopper.push(player);
            System.out.println(player.toString() + " boards the chopper!");
        }
        else{
            chopper.push(player);
            System.out.println(player.toString() + " boards the chopper!");
        }
        numPassengers+=1;
    }
}
