package rit.stu.act2;

import rit.stu.act1.QueueNode;

/**
 * Bunker class implementation.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */
public class Bunker {
    /** QueueNode representing the bunker */
    private QueueNode<Soldier> bunker;
    /** Integer representing number of soldiers in the bunker */
    private int	numSoldiers;


    //constructor
    /**
     * Bunker class constructor.
     * Creates an Bunker with the correct number of soldiers.
     * @param numSoldiers is th number of soldiers the bunker will hold
     */
    public Bunker(int numSoldiers){
        this.numSoldiers = numSoldiers;
        this.bunker = new QueueNode<>();
        int x = 1;
        while(x!=numSoldiers+1){
            this.bunker.enqueue(new Soldier(x));
            x++;
        }
    }

    /**
     * Returns the number of soldiers inside the bunker.
     * @return numSoldiers
     */
    public int getNumSoldiers() {
        return numSoldiers;
    }
    /**
     * Checks that the bunker has soldiers in it.
     * @return boolean
     */
    public boolean hasSoldiers() {
        return bunker.getSize()>0;
    }

    /**
     * This method deploys a soldier by removing them from the bunker.
     * Precondition: Bunker should not be empty.
     * @return soldier at front of bunker queue.
     */
    public Soldier deployNextSoldier(){
        assert bunker.front()!=null;
        Soldier deployed = bunker.front();
        bunker.dequeue();
        numSoldiers-=1;
        return deployed;
    }
    /**
     * This method adds soldiers to the back of the bunker queue.
     * @param soldier is the soldier that will enter the bunker.
     */
    public  void fortifySoldiers(Soldier soldier){

        bunker.enqueue(soldier);
        numSoldiers+=1;
    }

}
