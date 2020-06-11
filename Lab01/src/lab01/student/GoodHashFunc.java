/**
 * file: GoodHashFunc.java
 * author: Youssef Naguib <ymn4543@rit.edu>
 * language: java 11
 * description: Lab 1 solution
 */

package lab01.student;
import java.util.Scanner;

public class GoodHashFunc {
    public static void main(String args[]) {
        /**
         * This is the main method, it asks the user for a string and
         * runs the string through the computeHash method. It then prints out
         * the returned hash value.
         * Pre: user must input a string.
         * Post: string is converted to integer using hash function.
         */
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string: ");
        String word = input.next();
        int totalhash = computeHash(word);
        System.out.println("The computed hash for the specified string is: " + totalhash);
    }
    public static int computeHash(String word){
        /**
         * This method takes in a string and uses a specific formula to convert
         * it into a hash value. The hash value is then returned.
         * Pre: method must be given a string.
         * Post: string is converted to hash value and returned.
         */
        int[]Hlist = new int[word.length()];
        int n =  word.length();
        for(int x=0;x < word.length();x++){
            char chr = word.charAt(x);
            int value = chr * (int)(Math.pow(31,n-1));
            n--;
            Hlist[x] = value;
        }
        int total = 0;
        int x=0;
        while(x <  Hlist.length){
            total += Hlist[x];
            x+=1;
        }
        return total;
    }
}
