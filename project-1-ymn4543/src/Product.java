/**
 * Product class implementation for CS2 Project 1.
 * Author: Youssef Naguib </ymn4543@rit.edu>
 * Language: Java
 */
import java.util.ArrayList;

public class Product extends Function {
    /** a list containing all the functions in the product */
    public ArrayList<Function> items;
    /** a double representing the constant multiplier */
    public double number;
    /** an integer representing the number of variables in the product */
    public int vars;
    /** a boolean indicating whether the product is a derivative
     * of another function or not */
    public boolean isDerivative;
    /** an integer representing the number of Sine functions in the product */
    public int sinVars;
    /** an integer representing the number of Cosine functions in the product */
    public int cosVars;
    /** an integer representing how many products are in the product */
    public int numProds;
    /** an integer representing how many sums are in the product */
    public int numSums;

    /**
     *  Product constructor, takes in as many functions as provided.
     */
    public Product(Function... terms) {
        this.items = new ArrayList<>();
        this.number = 1.0;
        this.vars = 0;
        this.sinVars = 0;
        this.cosVars = 0;
        this.numProds = 0;

        isDerivative = false;
        for (Function element : terms) {
            items.add(element);
            if (element instanceof Constant) {
                number = number * ((Constant) element).getConstant();
            }
            if (element instanceof Variable) {
                vars = vars + 1;
            }
            if (element instanceof Sine) {
                sinVars += 1;
            }
            if (element instanceof Cosine) {
                cosVars += 1;
            }
            if(element instanceof Product){
                number = number * ((Product) element).getNumber();
                vars = vars + ((Product) element).getVars();
                cosVars = cosVars + ((Product) element).cosVars;
                sinVars = sinVars + ((Product) element).sinVars;
                numProds+=1;
            }
            if(element instanceof Sum){
                number = number * ((Sum) element).getNumber();
                vars = vars + ((Sum) element).getVars();
                numSums+=1;
            }

        }
    }


    /**
     * number getter
     * @return the constant multipler
     */
    public double getNumber() {
        return number;
    }

    /**
     * vars getter
     * @return the number of variables
     */
    public int getVars() {
        return vars;
    }

    /**
     * checks if function is constant
     * @return boolean
     */
    @Override
    public boolean isConstant() {
        return false;
    }

    /**
     * toString implementation for Product class
     * @return string representation of Product.
     */
    public String toString() {
        String s = "(";
        int l = 0;
        if(getNumber()!=1) {
            while(l <= items.size()-1) {
                if (l < items.size() - 1) {
                    if(items.get(l) instanceof Constant){
                        l+=1;
                    }
                    else {
                        s += items.get(l).toString() + " * ";
                        l += 1;
                    }
                }

                if (l == items.size() - 1) {
                    if (items.get(l) instanceof Constant) {
                        l += 1;
                    }else {
                        s += items.get(l).toString();
                        l += 1;
                    }
                }
            }
            s += getNumber() + ")";
            return s;
        }

        if(getNumber()==1){
            while(l<= items.size()-1){
                if(l< items.size()) {
                    if (items.get(l) instanceof Constant) {
                        l += 1;
                    } else {
                        s += items.get(l) + " * ";
                        l+=1;
                    }
                }
                if(l == items.size()-1){
                    if (items.get(l) instanceof Constant) {
                        l += 1;
                    } else {
                        s += items.get(l);
                        l+=1;
                    }
                    s += ")";
                }

            }
            return s;
        }
       return s;
    }


    /**
     * evaluates the function at a given value x
     * @param x value function will be evaluated at
     * @return a double
     */
    public double evaluate(double x){
            double total = 1;
            for(Function f : items){
                if(f instanceof Constant){
                    total*=((Constant) f).getConstant();
                }
                else {
                    total *= f.evaluate(x);
                }
            }

            return total;
        }

        /**
         * isDeriv setter
         */
        public void setDerivative(boolean x){
            this.isDerivative = x;
        }

        /**
         * adds a term to the items list, hence multiplying
         * the function by a new function.
         */
        public void multiply(Function x){
            if(x instanceof Constant){
                number = number * ((Constant) x).getConstant();

            }
            if(x instanceof Variable){
                vars = vars+1;
                items.add(x);
            }
            if(x instanceof Product){
                number = number * ((Product) x).getNumber();
                vars = vars + ((Product) x).getVars();
                cosVars = cosVars + ((Product) x).cosVars;
                sinVars = sinVars + ((Product) x).sinVars;
                items.add(x);
            }
            if(x instanceof Sum){
                number = number * ((Sum) x).getNumber();
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
         * calculates the derivative of the function
         * @return a function
         */
        public Function derivative() { //99x^2 = (x * 99) + (x * 99)
            if(numSums==items.size()) {
                Sum b = new Sum();
                for (Function x : items) {
                    for (Function e : items) {
                        if (!e.equals(x)) {
                            b.add(new Product(x, e.derivative()));
                        }
                    }
                }
                return b;
            }
            if(numProds==items.size()){
                int newv = getVars()-1;
                double newcon = getVars()*getNumber();
                Product deriv = new Product(new Constant(newcon));
                while(newv!=0){
                    deriv.multiply(Variable.X);
                    newv-=1;
                }
                return deriv;

            }
            if(sinVars+cosVars==items.size()){
                Sum b = new Sum();
                for (Function x : items) {
                    for (Function e : items) {
                        if (!e.equals(x)) {
                            b.add(new Product(x, e.derivative()));
                        }
                    }
                }
                return b;
            }
            if (sinVars > 0 || cosVars > 0||numProds>0||numSums>0) {
                Product deriv = new Product();
                Sum b = new Sum();
                deriv.multiply(new Constant(this.getNumber()));
                for (Function x : items) {
                    if( x instanceof Sine || x instanceof Cosine || x instanceof Variable) {
                        deriv.multiply(x.derivative());
                    }
                    if(x instanceof Sum){
                        for(Function e: items) {
                            if (!e.equals(x)) {
                                b.add(new Product(x, e.derivative()));
                            }
                        }
                        deriv.multiply(b);

                    }
                }
                return deriv;
            }


            else {
                if(getVars() == 1){
                    return new Constant(getNumber());
                }
                int i = 0;
                Sum deriv = new Sum();
                while (i <= vars - 1) {
                    deriv.add(new Product(Variable.X, new Constant(getNumber())));
                    i += 1;
                }
                double v = deriv.getVars();
                double n = deriv.getNumber();
                deriv.setDerivative(true);
                return deriv;
            }
        }

    /**
     * calculates the integral of the function
      * @param lowerB lowerbound
     * @param upperB upperbound
     * @param numbSteps number of steps
     * @return double
     */
    public double integral(double lowerB, double upperB, int numbSteps){
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
}

