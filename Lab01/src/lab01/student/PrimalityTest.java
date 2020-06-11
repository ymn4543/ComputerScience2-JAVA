/**
 * file: PrimalityTest.java
 * author: Youssef Naguib <ymn4543@rit.edu>
 * language: java 11
 * description: Lab 1 solution
 */

package lab01.student;
import java.util.Scanner;

public class PrimalityTest {
    public static boolean isPrime(int num) {
        /**
         * This method,takes in an integer and checks if the number is prime
         * Pre: method must be given a valid integer.
         * Post: True or False will be returned based on the primality of the
         * integer.
         */
        if( num == 0 || num == 1){
            return false;
        }
        boolean prime = true;
        int x =2 ;
        while(x <= num/2){
            if (num % x == 0) {
                prime = false;
                return prime;
            }
            else{
                x++;
            }

        }
        return prime;
    }
    public static void main(String args[]) {
        /**
         * This is the main method, it asks the user for an integer and
         * checks if the number is prime using the isPrime method.
         * Pre: user must input a valid integer.
         * Post: Method will notify user whether their input was prime or not.
         */
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(" ");
            System.out.println("Enter a number (0 to quit): ");
            int num = Integer.parseInt(input.next());
            if (num < 1) {
                System.out.println("Goodbye user!");
                break;
            }
            boolean p = isPrime(num);
            if (p == true) {
                System.out.print(num + " is prime!");
            }
            else{
                System.out.print(num + " is not prime!");
            }
        }
    }
}
