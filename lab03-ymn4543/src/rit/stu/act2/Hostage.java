package rit.stu.act2;

/**
 * Hostage class implementation which implements the Player interface.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */
public class Hostage implements Player{
    /** Hostage's ID number */
    private  int id;


    //constructor
    /**
     * Hostage class constructor.
     * @param id is the Hostage's assigned ID number
     */
    public Hostage(int id){
        this.id = id;
    }
    /**
     * Hostage toString() method.
     * @return Hostage in following string format: Hostage ## where ## is the ID number.
     */
    @Override
    public String toString(){
        return "Hostage #"+id;
    }
    /**
     * Hostage defeat method is called when Hostage loses a battle.
     * @param player is the player the Hostage lost the battle to.
     */
    public void defeat(Player player){
        System.out.println( toString() + " cries, 'Defeated by "+player+"!'");

    }
    /**
     * Hostage victory method is called when Hostage wins a battle.
     * @param player is the player the Hostage defeated in battle.
     */
    public void victory(Player player){
        System.out.println( toString()+ " yells, 'Victory over "+player+"!'");
    }


}
