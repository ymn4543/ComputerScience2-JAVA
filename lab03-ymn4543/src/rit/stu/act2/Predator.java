package rit.stu.act2;

/**
 * Predator class implementation which implements the Player interface.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */
public class Predator implements Player {
    /** Integer representing the Predator's chance of beating a Hostage */
    private static int CHANCE_TO_BEAT_HOSTAGE;
    /** Integer representing the Predator's chance of beating a Soldier */
    private static int CHANCE_TO_BEAT_SOLDIER;


    //constructor
    /**
     * Predator class constructor.
     * Creates a Predator with a CHANCE_TO_BEAT_HOSTAGE of 75 and a CHANCE_TO_BEAT_SOLDIER of 50.
     */
    public Predator(){
        CHANCE_TO_BEAT_HOSTAGE = 75;
        CHANCE_TO_BEAT_SOLDIER = 50;
    }
    /**
     * Predator defeat method is called when Predator loses a battle.
     * @param player is the player the Predator lost the battle to.
     */
    public void defeat(Player player){
        System.out.println("The predator cries out in glorious defeat to "+ player+"!");
    }
    /**
     * Predator victory method is called when Predator wins a battle.
     * @param player is the player the Predator defeated in battle.
     */
    public void victory(Player player){
        System.out.println("The predator yells out in triumphant victory over "+player+"!");
    }
    /**
     * Predator toString() method.
     * @return Predator in following string format: 'Predator'.
     */
    @Override
    public String toString(){
        return "Predator";
    }

    /**
     * Gets the Predator's chance of beating a Hostage.
     * @return CHANCE_TO_BEAT_HOSTAGE;
     */
    public int getChanceToBeatHostage(){
        return CHANCE_TO_BEAT_HOSTAGE;
    }
    /**
     * Gets the Predator's chance of beating a Soldier.
     * @return CHANCE_TO_BEAT_SOLDIER;
     */
    public int getChanceToBeatSoldier(){
        return CHANCE_TO_BEAT_SOLDIER;
    }

}

