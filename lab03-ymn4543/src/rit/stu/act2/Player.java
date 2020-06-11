package rit.stu.act2;
/**
 * Player interface implementation.
 * @author Youssef Naguib </ymn4543@rit.edu>
 * CS2 Lab 3
 */
public interface Player {
    /** victory method all players must implement*/
    public void victory(Player player);
    /** defeat method all players must implement*/
    public void defeat(Player player);
}
