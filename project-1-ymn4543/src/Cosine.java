/**
* Cosine class implementation for CS2 Project 1.
* Author: Youssef Naguib </ymn4543@rit.edu>
* Language: Java
*/
public class Cosine extends Function{
    /** Function representing the inside of the cosine function Cos(inside) */
    public Function inside;
    /** Boolean representing if this Function is a derivative of another Function */
    public boolean isDeriv;

    /**
     * Cosine Constructor.
     * @param x the inside Function of the new Cosine.
     */
    public Cosine(Function x){
        this.inside = x;
        this.isDeriv = false;
    }

    /**
     * returns inside function of a Cosine Function.
     * @return Function
     */
    public Function getInside() {
        return inside;
    }


    @Override
    /**
     * evaluates a Cosine Function at a given number x.
     * @param x is the value where the Function is being evaluated.
     * @return a double.
     */
    public double evaluate(double x) {
        double i = inside.evaluate(x);
        return Math.cos(i);
    }

    @Override
    /**
     * String representation of a Cosine Function.
     * @return String
     */
    public String toString() {
            return "Cos(" + getInside() + ")";
    }

    @Override
    /**
     * Calculates the derivative of a Cosine Function.
     * @return Function
     */
    public Function derivative() {  //cos(x)//        -sin(x)
        if(getInside().derivative().equals(new Constant(1))){
            return new Product(new Sine(this.getInside()),new Constant(-1));
        }
         return new Product( getInside().derivative(),new Sine(this.getInside()),new Constant(-1));
    }

    @Override
    /**
     * Calculates the integral of a Cosine.
     * @return double
     */
    public double integral(double lowerB, double upperB, int numbSteps) {
        double totalArea = (evaluate(lowerB) + evaluate(upperB));
        double deltaX = (upperB - lowerB) / numbSteps;
        double x;
        double stepper = lowerB + deltaX;
        while (stepper < numbSteps) {
            x = lowerB + stepper * deltaX;
            totalArea += 2 * evaluate(x);
            stepper += 1;
        }
        return totalArea * deltaX / 2;
    }

    @Override
    /**
     * returns a boolean whether or not this Function is constant.
     * Cosine functions will always return false.
     */
    public boolean isConstant() {
        return false;
    }
}
