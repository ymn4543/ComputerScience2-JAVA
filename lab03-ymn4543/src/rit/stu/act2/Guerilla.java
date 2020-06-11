package rit.stu.act2;

/**
 * Guerilla class implementation which implements the Player interface.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */

public class Guerilla implements Player {
    /** Integer representing the Guerilla's chance of beating a soldier */
    private static int CHANCE_TO_BEAT_SOLDIER;
    /** Guerilla's ID number */
    private int id;


    //constructor
    /**
     * Guerilla class constructor.
     * @param id is the guerilla's assigned ID number
     */
    public Guerilla(int id){
        this.id = id;
        CHANCE_TO_BEAT_SOLDIER = 20;
    }
    /**
     * Guerilla toString() method.
     * @return Guerilla in following string format: Guerilla ## where ## is the ID number.
     */
    @Override
    public String toString(){
        return "Guerilla #"+id;
    }
    /**
     * Guerilla victory method is called when Guerilla wins a battle.
     * @param player is the player the Guerilla defeated in battle.
     */
    public void victory(Player player){
        System.out.println(toString()+" yells, 'Victoria sobre "+ player+"!'");
    }
    /**
     * Guerilla defeat method is called when Guerilla loses a battle.
     * @param player is the player the Guerilla lost the battle to.
     */
    public void defeat(Player player){
        System.out.println(toString()+ " cries, 'Derrotado por "+player+"!'");
    }
    /**
     * Gets the Guerilla's chance of beating a soldier.
     * @return CHANCE_TO_BEAT_SOLDIER;
     */
    public int getChanceToBeatSoldier(){
        return CHANCE_TO_BEAT_SOLDIER;
    }
}
