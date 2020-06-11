/**
 * Sum class implementation for CS2 Project 1.
 * Author: Youssef Naguib </ymn4543@rit.edu>
 * Language: Java
 */
import java.util.ArrayList;
import java.util.LinkedList;

public class Sum extends Function {
    /** a list containing all the functions in the sum */
    public ArrayList<Function> items;
    /** a double representing a constant being added to the sum */
    public double number;
    /** an integer representing the number of variables in the sum */
    public int vars;
    /** a boolean indicating whether the sum is a derivative
     * of another function or not */
    public boolean isDerivative;
    /** an integer representing the number of Sine functions in the sum */
    public int sinVars;
    /** an integer representing the number of Cosine functions in the sum */
    public int cosVars;
    /** an integer representing how many products are in the sum */
    public int numProds;
    /** an integer representing how many sums are in the sum */
    public int numSums;


    /**
     *  Sum constructor, takes in as many functions as provided.
     */
public Sum(Function... terms){
    this.items = new ArrayList<>();
    this.number = 0.0;
    this.vars = 0;
    this.sinVars = 0;
    this.cosVars = 0;
    this.numSums = 0;
    this.numProds=0;
    isDerivative = false;
    for(Function element:terms){
        items.add(element);
        if(element instanceof Constant){
            number = number + ((Constant) element).getConstant();
        }
        if(element instanceof Variable){
            vars = vars+1;
        }
        if (element instanceof Sine){
            sinVars +=1;
        }
        if(element instanceof Cosine){
            cosVars +=1;
        }
        if(element instanceof Sum){
            this.number = number + ((Sum) element).getNumber();
            this.vars = vars + ((Sum) element).getVars();
            this.cosVars =  this.cosVars + ((Sum) element).cosVars;
            this.sinVars =  this.sinVars + ((Sum) element).sinVars;
            numSums+=1;

        }
        if(element instanceof Product){
            numProds +=1;
            vars = vars + ((Product) element).getVars();
            cosVars = cosVars + ((Product) element).cosVars;
            sinVars = sinVars + ((Product) element).sinVars;
        }
    }
}

    /**
     * number getter
     * @return the constant being added
     */
public double getNumber(){
    return number;
}

    /**
     * vars getter
     * @return the number of variables
     */
public int getVars(){
    return vars;
}

    /**
     * adds a term to the items list, hence adding
     * the function to the total sum.
     */
public void add(Function x){
    if(x instanceof Constant){
        number = number + ((Constant) x).getConstant();
        items.add(x);
    }
    if(x instanceof Variable){
        vars = vars+1;
        items.add(x);
    }
    if(x instanceof Product){
        number = number + ((Product) x).getNumber();
        vars = vars + ((Product) x).getVars();
        items.add(x);
    }
    if(x instanceof Sum){
        number = number + ((Sum) x).getNumber();
        vars = vars + ((Sum) x).getVars();
        items.add(x);
        numSums+=1;
    }
    if(x instanceof Sine){
        sinVars +=1;
        items.add(x);
    }
    if(x instanceof Cosine){
        cosVars +=1;
        items.add(x);
    }
}


    /**
     * checks if function is constant
     * @return boolean
     */
    @Override
    public boolean isConstant() {
        if(vars>0) {
            return false;
        }
        return true;
    }

    public String toString() {
        String sum = "(";


        if (isDerivative) {
            double m = number / vars;
            for (int i = 0; i < vars - 1; ++i) {
                sum = sum + "( x * " + m + ") + ";
            }
            sum = sum + "( x * " + m + ")";
            return sum + ")";
        }

        else {
            if(numSums !=0 || numProds!=0) {
                int s = 0;
                int l = items.size();
                while (s <= l - 1) {
                    if (s < l - 1) {
                        sum = sum + items.get(s) + " + ";
                        s += 1;

                    }
                    if (s == l - 1) {
                        sum = sum + items.get(s);
                        s += 1;
                    }
                }
                sum += ")";
            }
            else{
                double t = 0;
                for(Function x: items){
                    if(x instanceof Constant){
                        t+=((Constant) x).getConstant();
                    }
                    else{
                        sum+=x + " + ";
                    }

                }
                if(t!=0) {
                    sum += t + ")";
                }
                else{
                    sum += ")";
                }
            }


        }
        return sum;
    }



    /**
     * isDeriv setter
     */
    public void setDerivative(boolean x){
        this.isDerivative = x;
    }

    /**
     * evaluates the function at a given value x
     * @param x value function will be evaluated at
     * @return a double
     */
    public double evaluate(double x){
        double total = 0;
        for(Function f: items){
            if(f instanceof Constant){
                total+=((Constant) f).getConstant();
            }
            else {
                total += f.evaluate(x);
            }
        }
        return total;
    }

    /**
     * calculates the derivative of the function
     * @return a function
     */
    public Function derivative() {
        if(numProds > 0||sinVars>0||cosVars>0){
            Sum deriv = new Sum();
            for(Function x:items){
                deriv.add(x.derivative());
            }
            return deriv;
        }

        return new Constant(getVars());
    }

    /**
     * calculates the integral of the function
     * @param lowerB lowerbound
     * @param upperB upperbound
     * @param numbSteps number of steps
     * @return double
     */
    public double integral(double lowerB, double upperB, int numbSteps) {
        double totalArea = (evaluate(lowerB) + evaluate(upperB));
        double deltaX = (upperB - lowerB) / numbSteps;
        double x;
        double stepper = lowerB + deltaX;
        while (stepper < numbSteps) {
            x = lowerB + stepper*deltaX;
            totalArea += 2 * evaluate(x);
            stepper += 1;
        }
        return totalArea * deltaX/2;

    }


}
