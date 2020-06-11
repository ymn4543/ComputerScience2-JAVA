/**
 * file: HTree.java
 * author: Youssef Naguib <ymn4543@rit.edu>
 * language: java 11
 * description: Lab 1 solution
 */

package lab01.student;

import static turtle.Turtle.*;

public class HTree {
    public static final int MAX_SEGMENT_LENGTH = 200;

    public static void init(int length, int depth){
        /**
         * This method initializes the graphics.
         * length (int): The length of the main snowflake branch.
         * depth (int): The depth of recursion.
        */
        Turtle.setWorldCoordinates(-length*2, -length*2, length*2, length*2);
        Turtle.title("H-Tree, depth: " + depth );
    }

    public static void drawHTree(int length, int depth){
        /**
         * This method is a recursive HTree drawing method. it requires a
         * length int and a depth int.
         * Pre: both arguments must be integers.
         * Post: HTree is drawn.
         */
        if(depth > 0){
            //start in center of H, move to upper right
            Turtle.forward(length/2);
            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.right(90);
            //recurse
            drawHTree(length / 2, depth - 1);
            //# move to lower right of H
            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);
            //recurse
            drawHTree(length / 2, depth - 1);
            //move to upper left of H
            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            Turtle.forward(length / 2);
            Turtle.right(90);
            //recurse
            drawHTree(length / 2, depth - 1);
            //move to lower left of H
            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);
            //recurse
            drawHTree(length / 2, depth - 1);
            //return to center of H
            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.right(90);
            Turtle.forward(length / 2);
        }
    }
    public static void main(String args[]) {
        /**
         * The main method reads the command line argument for the depth
         * and draws the h-tree.
         * pre: argument between 0 and 3 must be given to method.
         * post: An h tree will be drawn with a depth of the given argument.
         */
        if(args.length < 2) {
            System.out.println("Usage: python3 HTree #");
        }
        //check the depth is >= 0
        int depth = Integer.parseInt(args[0]);
        if(depth < 0) {
            System.out.println("The depth must be greater than or equal to 0");
            return;
        }
        //initialize turtle
        init(MAX_SEGMENT_LENGTH, depth);
        // draw the h-tree
        drawHTree(MAX_SEGMENT_LENGTH, depth);
        // wait for input to close
        System.out.println("Please close window to exit program.");
    }
}