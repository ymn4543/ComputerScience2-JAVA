/**
 * Sine class implementation for CS2 Project 1.
 * Author: Youssef Naguib </ymn4543@rit.edu>
 * Language: Java
 */
public class Sine extends Function{
    /** Function representing the inside of the sine function Sin(inside) */
    public Function inside;
    /** Boolean representing if this Function is a derivative of another Function */
    public boolean isDeriv;

    /**
     * Sine Constructor.
     * @param x the inside Function of the new Sine.
     */
    public Sine(Function x){
        this.inside = x;
        this.isDeriv = false;
    }

    /**
     * returns inside function of a Sine Function.
     * @return Function
     */
    public Function getInside() {
        return inside;
    }

    /**
     * evaluates a Sine Function at a given number x.
     * @param x is the value where the Function is being evaluated.
     * @return a double.
     */
    @Override
    public double evaluate(double x) {
        double i = inside.evaluate(x);
        return Math.sin(i);
    }

    /**
     * String representation of a Sine Function.
     * @return String
     */
    @Override
    public String toString() {
        return "Sin("+getInside()+")";
    }


    public void setDeriv(boolean x){
        this.isDeriv = x;
    }

    @Override
    public Function derivative() {
        if(getInside().derivative().equals(new Constant(1))){
            return new Cosine(this.getInside());
        }
        else {
            Product deriv = new Product(getInside().derivative(), new Cosine(this.getInside()));
            deriv.setDerivative(true);
            return deriv;
        }
    }

    @Override
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
    public boolean isConstant() {
        return false;
    }
}
