/**
 * FunctionTest1 is a test file for CS2 Project 1.
 * Author: Youssef Naguib </ymn4543@rit.edu>
 * Language: Java
 */
public class FunctionTest1 {
    //this is a test for the Constant, Variable, and Sum classes.


    public static void main(String args[]) {
        //test sum constructor
        System.out.println("Constructing 5 Sums...");
        Sum sum1 = new Sum(new Constant(10), Variable.X, Variable.X, new Constant(12));
        Sum sum2 = new Sum(new Constant(5), Variable.X, Variable.X, Variable.X);
        Sum sum3 = new Sum(Variable.X, Variable.X);
        Sum sum4 = new Sum(new Constant(2), Variable.X, new Sum(new Constant(15), Variable.X, Variable.X));
        Sum sum5 = new Sum(Variable.X, new Sum(new Constant(10), new Sum(new Constant(20), Variable.X)));
        System.out.println("Sums constructed!");
        //test sum toString
        System.out.println(" ");
        System.out.println("Printing Sums...");
        System.out.println("sum1 = " + sum1);
        System.out.println("sum2 = " + sum2);
        System.out.println("sum3 = " + sum3);
        System.out.println("sum4 = " + sum4);
        System.out.println("sum5 = " + sum5);
        //test sum evaluate
        System.out.println(" ");
        System.out.println("Evaluating each sum at 2, 5 , and 10 ...");
        System.out.println("sum1 evaluated at 2 = 26?" + " Answer = " + sum1.evaluate(2) + " " + (sum1.evaluate(2) == 26));
        System.out.println("sum1 evaluated at 5 = 32?" + " Answer = " + sum1.evaluate(5) + " " + (sum1.evaluate(5) == 32));
        System.out.println("sum1 evaluated at 10 = 42?" + " Answer = " + sum1.evaluate(10) + " " + (sum1.evaluate(10) == 42));
        System.out.println("sum2 evaluated at 2 = 11?" + " Answer = " + sum2.evaluate(2) + " " + (sum2.evaluate(2) == 11));
        System.out.println("sum2 evaluated at 5 = 20?" + " Answer = " + sum2.evaluate(5) + " " + (sum2.evaluate(5) == 20));
        System.out.println("sum2 evaluated at 10 = 35?" + " Answer = " + sum2.evaluate(10) + " " + (sum2.evaluate(10) == 35));
        System.out.println("sum3 evaluated at 2 = 4?" + " Answer = " + sum3.evaluate(2) + " " + (sum3.evaluate(2) == 4));
        System.out.println("sum3 evaluated at 5 = 10?" + " Answer = " + sum3.evaluate(5) + " " + (sum3.evaluate(5) == 10));
        System.out.println("sum3 evaluated at 10 = 20?" + " Answer = " + sum3.evaluate(10) + " " + (sum3.evaluate(10) == 20));
        System.out.println("sum4 evaluated at 2 = 23?" + " Answer = " + sum4.evaluate(2) + " " + (sum4.evaluate(2) == 23));
        System.out.println("sum4 evaluated at 5 = 32?" + " Answer = " + sum4.evaluate(5) + " " + (sum4.evaluate(5) == 32));
        System.out.println("sum4 evaluated at 10 = 47?" + " Answer = " + sum4.evaluate(10) + " " + (sum4.evaluate(10) == 47));
        System.out.println("sum5 evaluated at 2 = 34?" + " Answer = " + sum5.evaluate(2) + " " + (sum5.evaluate(2) == 34));
        System.out.println("sum5 evaluated at 5 = 40?" + " Answer = " + sum5.evaluate(5) + " " + (sum5.evaluate(5) == 40));
        System.out.println("sum5 evaluated at 10 = 50?" + " Answer = " + sum5.evaluate(10) + " " + (sum5.evaluate(10) == 50));
        //test sum derivative
        System.out.println(" ");
        System.out.println("Calculating the derivative of each sum..");
        System.out.println("The derivative of sum1 is: " + sum1.derivative());
        System.out.println("The derivative of sum2 is: " + sum2.derivative());
        System.out.println("The derivative of sum3 is: " + sum3.derivative());
        System.out.println("The derivative of sum4 is: " + sum4.derivative());
        System.out.println("The derivative of sum5 is: " + sum5.derivative());
        //test sum integral
        System.out.println(" ");
        System.out.println("Calculating the integral of each sum..");
        System.out.println("The integral of sum1 from 0 to 10 is approx 320? Answer is: "
                + sum1.integral(0, 10, 100000) + ":   " +
                (sum1.integral(0, 10, 1000000) > 319.99 && sum1.integral(0, 10, 1000000) < 321.00));
        System.out.println("The integral of sum2 from 0 to 10 is approx 200? Answer is: "
                + sum2.integral(0, 10, 100000) + ":   " +
                (sum2.integral(0, 10, 1000000) > 199.99 && sum2.integral(0, 10, 1000000) < 201.00));
        System.out.println("The integral of sum3 from 0 to 10 is approx 100? Answer is: "
                + sum3.integral(0, 10, 100000) + ":   " +
                (sum3.integral(0, 10, 1000000) > 99.99 && sum3.integral(0, 10, 1000000) < 101.00));
        System.out.println("The integral of sum4 from 0 to 10 is approx 320? Answer is: "
                + sum4.integral(0, 10, 100000) + ":   " +
                (sum4.integral(0, 10, 1000000) > 319.99 && sum4.integral(0, 10, 1000000) < 321.00));
        System.out.println("The integral of sum5 from 0 to 10 is approx 400? Answer is: "
                + sum5.integral(0, 10, 100000) + ":   " +
                (sum5.integral(0, 10, 1000000) > 399.99 && sum3.integral(0, 10, 1000000) < 401.00));
    }
}
