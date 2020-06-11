/**
* Constant class implementation for CS2 Project 1.
* Author: Youssef Naguib </ymn4543@rit.edu>
* Language: Java
*/
public class Variable extends Function{
    /** an instance of the variable X   */
    public final static Variable X = new Variable();

    /**
     * private variable constructor that takes no params.
     * constructed as new Variable.X;
     */
    private Variable(){

    }

    /**
     * evaluates a Variable.
     * @param x constant being evaluated
     * @return value of x
     */
    @Override
    public double evaluate(double x) {
        return x;
    }

    /**
     * The derivative of a variable x is always 1.
     * @return a constant 1
     */
    public Function derivative(){
        return new Constant(1);
    }


    /**
     * String representation of a Variable
     * @return a String
     */
    public String toString(){
        return "x";
    }


    /**
     * returns boolean whether this function is a constant or not.
     * A function of the Variable class will always return false.
     */
    public boolean isConstant(){
        return false;
    }


    /**
     * integral of a variable
     * @param a lower bound
     * @param b upper bound
     * @param x delta x
     * @return a double
     */
    public double integral(double a, double b, int x){
        return 1;
    }
}
