/**
 * Constant class implementation for CS2 Project 1.
 * Author: Youssef Naguib </ymn4543@rit.edu>
 * Language: Java
 */

public class Constant extends Function {
    /** a double symbolising the numerical value of this constant */
    public double constant;

    //constructor

    /**
     * Constant constructor.
     * @param constant the numerical value of this constant.
     */
    public Constant(double constant){
        this.constant = constant;
    }

    /**
     * The derivative of a constant.
     * @return null
     */
    public Function derivative(){
        return null;
    }

    @Override
    /**
     * returns boolean whether this function is a constant or not.
     * A function of the Constant class will always return true.
     */
    public boolean isConstant() {
        return true;
    }

    /**
     * String representation of a Constant
     * @return a String
     */
    public String toString(){
        return "" + getConstant();
    }

    /**
     * evaluates a Constant.
     * @param constant constant being evaluated
     * @return value of the constant
     */
    public double evaluate(double constant){
        return constant;
    }

    /**
     * returns the value of a Constant
     * @return double
     */
    public double getConstant(){
        return constant;
    }

    /**
     * integral method of a constant. Since a Constant is not a function,
     * 0 is returned.
     * @param a lower bound
     * @param b upper bound
     * @param x number of steps
     * @return integral (0.0)
     */
    public double integral(double a, double b, int x){
        return 0.0;
    }


}
