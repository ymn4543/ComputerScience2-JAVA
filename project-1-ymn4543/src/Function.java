/**
 * Function abstract class implementation for CS2 Project 1.
 * Author: Youssef Naguib </ymn4543@rit.edu>
 * Language: Java
 */
public abstract class Function {

    /** this method will evaluate a function at a given value x */
    public abstract double evaluate(double x);

    /** this method will represent a function as a string */
    public abstract String toString();

    /** this method will return the derivative of a function */
    public abstract Function derivative();

    /** this method will return the integral of a function between two bounds */
    public abstract double integral(double lowerB, double upperB, int numbT);

    /** this method will check whether a function is constant or not */
    public abstract boolean isConstant();

}
