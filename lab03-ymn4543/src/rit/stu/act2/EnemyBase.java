package rit.stu.act2;

import rit.stu.act1.QueueNode;
import rit.stu.act1.StackNode;

/**
 * EnemyBase class implementation.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */

public class EnemyBase {
    /** QueueNode representing the line of guerillas */
    private QueueNode<Guerilla> guerillas;
    /** StackNode representing the cave of hostages */
    private StackNode<Hostage> hostages;
    /** Integer representing the current number of guerilla's in the EnemyBase*/
    private int numGuerillas;
    /** Integer representing the current number of Hostage's in the EnemyBase*/
    private int numHostages;


//constructor
    /**
     * EnemyBase class constructor.
     * Creates an EnemyBase with the corresponding number of hostages and guerillas.
     */
    public EnemyBase(int numHostages, int numGuerillas) {
        this.numHostages = numHostages;
        this.numGuerillas = numGuerillas;
        this.guerillas = new QueueNode<>();
        this.hostages = new StackNode<>();
        int h = 1;
        int g = 1;
        while (h != numHostages+1) {
            hostages.push(new Hostage(h));
            h++;
        }
        while (g != numGuerillas+1) {
            guerillas.enqueue(new Guerilla(g));
            g++;
        }
    }
    /**
     * This method adds a guerilla to back of the guerilla queue.
     */
    public void addGuerilla(Guerilla guerilla){
        guerillas.enqueue(guerilla);
        numGuerillas+=1;
    }
    /**
     * This method adds a hostage to top of the hostage stack.
     */
    public void addHostage(Hostage hostage){
        hostages.push(hostage);
        numHostages +=1;
    }
    /**
     * This method removes a querilla from the guerilla queue and returns it.
     * @return guerilla at front of queue.
     */
    private Guerilla getGuerilla(){
        Guerilla g = guerillas.front();
        guerillas.dequeue();
        numGuerillas-=1;
        return g;
    }
    /**
     * This method removes a hostage from the hostage stack and returns it.
     * @return hostage at top of stack.
     */
    private Hostage getHostage(){
        Hostage h = hostages.top();
        hostages.pop();
        numHostages-=1;
        return h;
    }

    /**
     * Returns the number of guerillas in the EnemyBase.
     * @return numGuerillas
     */
    public int getNumGuerillas(){
        return  numGuerillas;
    }
    /**
     * Returns the number of hostages in the EnemyBase.
     * @return numHostages
     */
    public int getNumHostages(){
        return numHostages;
    }

    /**
     * This method enters a soldier into the EnemyBase in an attempt to rescue a Hostage.
     * The soldier battles a guerilla and if victorious, rescues one hostage. Otherwise,
     * the guerilla wins and the hostage returns to the cave.
     * Precondition: There should be at least one guerilla and one hostage.
     * @return hostage if rescued, null otherwise.
     */
    public Hostage rescueHostage(Soldier soldier){
        System.out.println(soldier.toString()+" enters enemy base...");
        Hostage current = getHostage();
        if(getNumGuerillas() == 0){
            return  current;
        }
        else {
        Guerilla g = getGuerilla();
        int roll = Battlefield.nextInt(1,100);
        System.out.println(soldier.toString() + " battles " + g.toString() + " who rolls a " + roll );
        if(roll > g.getChanceToBeatSoldier()){
            soldier.victory(g);
            g.defeat(soldier);
            return current;
        }
        else{
            g.victory(soldier);
            soldier.defeat(g);
            addGuerilla(g);
            addHostage(current);
            return null;
        }
        }
    }











}
