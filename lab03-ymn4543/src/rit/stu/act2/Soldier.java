package rit.stu.act2;

/**
 * Soldier class implementation which implements the Player interface.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */

public class Soldier implements Player {
    /** soldier's ID number */
    private int id;


    /**
     * Soldier class constructor.
     * @param id is the soldier's assigned id number
     */
    public Soldier(int id){
        this.id = id;
    }
    /**
     * Soldier toString() method.
     * @return soldier in following string format: Soldier ## where ## is the ID number.
     */
    @Override
    public String toString(){
        return "Soldier #"+id;
    }
    /**
     * Soldier defeat method is called when Soldier loses a battle.
     * @param player is the player the soldier lost the battle to.
     */
    public void defeat(Player player){
        System.out.println( toString() + " cries, 'Besiegt von " + player +"!'");

    }
    /**
     * Soldier victory method is called when Soldier wins a battle.
     * @param player is the player the soldier defeated in battle.
     */
    public void victory(Player player){
        System.out.println( toString()+" yells, 'Sieg über " + player + "!'");
    }




}
