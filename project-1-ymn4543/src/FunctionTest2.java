/**
 * FunctionTest2 is a test file for CS2 Project 1.
 * Author: Youssef Naguib </ymn4543@rit.edu>
 * Language: Java
 */
public class FunctionTest2 {
    public static void main(String args[]){
        //this program tests everything. Sums, Products, Cosines, and Sines.
        //this sum will include sums
        System.out.println("Creating Functions...");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        Sum sum1 = new Sum(Variable.X,new Constant(8),new Sum(Variable.X,Variable.X,new Constant(12)),
                new Sum(Variable.X,new Constant(2)));
        //this sum will include products
        Sum sum2 = new Sum(new Product(Variable.X,Variable.X,new Constant(4)),new Product(Variable.X,new Constant(3)));
        //this sum will contain trig functions
        Sum sum3 = new Sum(new Cosine(new Product(Variable.X,new Constant(5))),new Sine(Variable.X));
        //this sum will contain one of each
        Sum sum4 = new Sum(new Sum(Variable.X,Variable.X,new Constant(2)),new Product(Variable.X,Variable.X),
                new Sine(new Product(Variable.X,new Constant(2))));


       //this product will include sums
       Product prod1 = new Product(new Sum(Variable.X,new Constant(6)),new Sum(Variable.X,new Constant(3)));
       //this product will include products
       Product prod2 = new Product(new Product(Variable.X,Variable.X),
               new Product(new Constant(3),new Constant(4),Variable.X));
       //this product will include trig functions
       Product prod3 = new Product(new Cosine(Variable.X),new Sine(new Product(new Constant(7),Variable.X)));


       //this sine will include variables
       Sine sine1 = new Sine((Variable.X));
       //this sine will include a product
       Sine sine2 = new Sine(new Product(Variable.X,new Constant(5)));
       //this sine will include a sum
       Sine sine3 = new Sine(new Sum(new Constant(3),Variable.X,Variable.X));
       //this sine will includes a trig
       Sine sine4 = new Sine(new Sine(Variable.X));

       //this cosine will include variables
        Cosine cosine1 = new Cosine(Variable.X);
        //this cosine will include a product
        Cosine cosine2 = new Cosine(new Product(Variable.X,new Constant(2)));
        //this sine will include a sum
        Cosine cosine3 = new Cosine(new Sum(Variable.X,new Constant(6)));
        //this cosine will includes a trig
        Cosine cosine4 = new Cosine(new Sine(Variable.X));


        System.out.println(" ");
        System.out.println("START OF SUM TESTS");
        System.out.println("Printing Sums");
        System.out.println("Sum1 = " + sum1);
        System.out.println("Sum2 = " +sum2);
        System.out.println("Sum3 = " + sum3);
        System.out.println("Sum4 = " + sum4);
        System.out.println("");
        System.out.println("Evaluating Sums at 1");
        System.out.println("Sum 1: " + sum1.evaluate(1));
        System.out.println("Sum 2: " +sum2.evaluate(1));
        System.out.println("Sum 3: " +sum3.evaluate(1));
        System.out.println("Sum 4: " +sum4.evaluate(1));
        System.out.println("");
        System.out.println("Evaluating Sums at 3");
        System.out.println("Sum 1: " + sum1.evaluate(3));
        System.out.println("Sum 2: " +sum2.evaluate(3));
        System.out.println("Sum 3: " +sum3.evaluate(3));
        System.out.println("Sum 4: " +sum4.evaluate(3));
        System.out.println(" ");
        System.out.println("Evaluating Sums at 5");
        System.out.println("Sum 1: " + sum1.evaluate(5));
        System.out.println("Sum 2: " +sum2.evaluate(5));
        System.out.println("Sum 3: " +sum3.evaluate(5));
        System.out.println("Sum 4: " +sum4.evaluate(5));
        System.out.println("");
        System.out.println("Testing derivatives");
        System.out.println("Sum1 derivative:  " + sum1.derivative());
        System.out.println("Sum2 derivative:  " +sum2.derivative());
        System.out.println("Sum3 derivative:  " +sum3.derivative());
        System.out.println("Sum4 derivative:  " +sum4.derivative());
        System.out.println("");
        System.out.println("Testing integrals from 0 to 10");
        System.out.println("Sum1 integral:  "+sum1.integral(0,10,100000));
        System.out.println("Sum2 integral:  "+sum2.integral(0,10,100000));
        System.out.println("Sum3 integral:  "+sum3.integral(0,10,100000));
        System.out.println("Sum4 integral:  "+sum4.integral(0,10,100000));
        System.out.println(" ");
        System.out.println("SUM TESTS COMPLETE!");


        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("START OF PRODUCT TESTS");
        System.out.println("Printing Products");
        System.out.println("Product1 = "+prod1);
        System.out.println("Product2 = "+prod2);
        System.out.println("Product3 = "+prod3);
        System.out.println("");
        System.out.println("Evaluating Products at 1");
        System.out.println("prod1: " + prod1.evaluate(1));
        System.out.println("prod2: " +prod2.evaluate(1));
        System.out.println("prod3: " +prod3.evaluate(1));
        System.out.println("");
        System.out.println("Evaluating Products at 3");
        System.out.println("prod1: " + prod1.evaluate(3));
        System.out.println("prod2: " +prod2.evaluate(3));
        System.out.println("prod3: " +prod3.evaluate(3));
        System.out.println(" ");
        System.out.println("Evaluating Products at 5");
        System.out.println("prod1: " + prod1.evaluate(5));
        System.out.println("prod2: " +prod2.evaluate(5));
        System.out.println("prod3: " +prod3.evaluate(5));
        System.out.println("");
        System.out.println("Testing derivatives");
        System.out.println("Product1 derivative:  "+prod1.derivative());
        System.out.println("Product2 derivative:  "+prod2.derivative());
        System.out.println("Product3 derivative:  "+prod3.derivative());
        System.out.println("");
        System.out.println("Testing integrals from 0 to 10");
        System.out.println("Product1 integral:  "+prod1.integral(0,10,100000));
        System.out.println("Product2 integral:  "+prod2.integral(0,10,100000));
        System.out.println("Product3 integral:  "+prod3.integral(0,10,100000));
        System.out.println(" ");
        System.out.println("PRODUCT TESTS COMPLETE!");







        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("START OF SINE TESTS");
        System.out.println("Printing SINES");
        System.out.println("sin1 = "+ sine1);
        System.out.println("sin2 = "+sine2);
        System.out.println("sin3 = "+sine3);
        System.out.println("sin4 = "+sine4);
        System.out.println("");
        System.out.println("Evaluating Sines at 1");
        System.out.println("Sine1: " +sine1.evaluate(1));
        System.out.println("Sine2: " +sine2.evaluate(1));
        System.out.println("Sine3: " +sine3.evaluate(1));
        System.out.println("Sine4: " +sine4.evaluate(1));
        System.out.println("");
        System.out.println("Evaluating Sines at 3");
        System.out.println("Sine1: " +sine1.evaluate(3));
        System.out.println("Sine2: " +sine2.evaluate(3));
        System.out.println("Sine3: " +sine3.evaluate(3));
        System.out.println("Sine4: " +sine4.evaluate(3));
        System.out.println(" ");
        System.out.println("Evaluating Sines at 5");
        System.out.println("Sine1: " +sine1.evaluate(5));
        System.out.println("Sine2: " +sine2.evaluate(5));
        System.out.println("Sine3: " +sine3.evaluate(5));
        System.out.println("Sine4: " +sine4.evaluate(5));
        System.out.println("");
        System.out.println("Testing derivatives");
        System.out.println("sin1 derivative:  "+sine1.derivative());
        System.out.println("sin2 derivative:  "+sine2.derivative());
        System.out.println("sin3 derivative:  "+sine3.derivative());
        System.out.println("sin4 derivative:  "+sine4.derivative());
        System.out.println("");
        System.out.println("Testing integrals from 0 to 10");
        System.out.println("sin1 integral:  "+sine1.integral(0,10,100000));
        System.out.println("sin2 integral:  "+sine2.integral(0,10,100000));
        System.out.println("sin3 integral:  "+sine3.integral(0,10,100000));
        System.out.println("sin4 integral:  "+sine4.integral(0,10,100000));
        System.out.println(" ");
        System.out.println("SINE TESTS COMPLETE!");



        System.out.println(".");
        System.out.println(".");
        System.out.println(".");

        System.out.println("START OF COSINE TESTS");
        System.out.println("Printing COSINES");
        System.out.println("cosine1 = "+cosine1);
        System.out.println("cosine2 = "+cosine2);
        System.out.println("cosine3 = "+cosine3);
        System.out.println("cosine4 = "+cosine4);
        System.out.println("");
        System.out.println("Evaluating Cosines at 1");
        System.out.println("Cosine1: " +cosine1.evaluate(1));
        System.out.println("Cosine2: " +cosine2.evaluate(1));
        System.out.println("Cosine3: " +cosine3.evaluate(1));
        System.out.println("Cosine4: " +cosine4.evaluate(1));
        System.out.println("");
        System.out.println("Evaluating Cosines at 3");
        System.out.println("Cosine1: " +cosine1.evaluate(3));
        System.out.println("Cosine2: " +cosine2.evaluate(3));
        System.out.println("Cosine3: " +cosine3.evaluate(3));
        System.out.println("Cosine4: " +cosine4.evaluate(3));
        System.out.println(" ");
        System.out.println("Evaluating Cosines at 5");
        System.out.println("Cosine1: " +cosine1.evaluate(5));
        System.out.println("Cosine2: " +cosine2.evaluate(5));
        System.out.println("Cosine3: " +cosine3.evaluate(5));
        System.out.println("Cosine4: " +cosine4.evaluate(5));
        System.out.println("");
        System.out.println("Testing derivatives");
        System.out.println("cosine1 derivative:  "+cosine1.derivative());
        System.out.println("cosine2 derivative:  "+cosine2.derivative());
        System.out.println("cosine3 derivative:  "+cosine3.derivative());
        System.out.println("cosine4 derivative:  "+cosine4.derivative());
        System.out.println("");
        System.out.println("Testing integrals from 0 to 10");
        System.out.println("cosine1 integral:  "+cosine1.integral(0,10,100000));
        System.out.println("cosine2 integral:  "+cosine2.integral(0,10,100000));
        System.out.println("cosine3 integral:  "+cosine3.integral(0,10,100000));
        System.out.println("cosine4 integral:  "+cosine4.integral(0,10,100000));
        System.out.println(" ");
        System.out.println("COSINE TESTS COMPLETE!");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("ALL TESTS COMPLETE!");
    }
}
