/**
 * file: SieveOfEratosthenes.java
 * author: Youssef Naguib <ymn4543@rit.edu>
 * language: java 11
 * description: Lab 1 solution
 */

package lab01.student;

import java.util.Scanner;

public class SieveOfEratosthenes {
    public static int[] makeSieve(int Bound) {
        /**
         * This method takes an upper bound and creates an array of the
         * length of upper bound. For each prime index, the element is 0,
         * otherwise it is 1.
         * Pre: Bound must be an integer.
         * Post: int array created using the sieve of eratosthenes and
         * is returned.
         */
        int[] upperBound = new int[Bound];
        upperBound[0] = 1;
        upperBound[1] = 1;
        int x = 2;
        while (x < (upperBound.length / 2)) {
            int i = 2;
            while (i < upperBound.length) {
                if (i != x && i % x == 0) {
                    upperBound[i] = 1;
                    i++;
                }
                else{
                    i++;
                    }
            }
            x++;
        }
        return upperBound;
    }

    public static void main(String args[]) {
        /**
         * This main method function asks the user for an upperbound, and uses
         * that integer to create a sieve of eratosthenes using the makeSieve
         * method. User can then check if numbers are prime or not.
         * Pre: Bound must be an integer, num must be an integer.
         * Post: Program will notify user whether num is prime or not.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an upper bound: ");
        int Bound = Integer.parseInt(input.next());
        int[] array = makeSieve(Bound);
        while (true) {
            System.out.println("Please enter a positive number (0 to quit): ");
            int num = Integer.parseInt(input.next());
            if (num < 1) {
                System.out.println("Goodbye!");
                break;
            }
            if (array[num] == 0) {
                System.out.println(num + " is prime.");
            }
            if (array[num] == 1) {
                System.out.println(num + " is not prime.");
            }

        }
    }
}


