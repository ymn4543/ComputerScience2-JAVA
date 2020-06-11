package rit.stu.act1;

import rit.cs.Node;
import rit.cs.Stack;

/**
 * A stack implementation that uses a Node to represent the structure.
 * @param <T> The type of data the stack will hold
 * @author Sean Strout @ RIT CS
 * @author Youssef Naguib <Ymn4543@rit.edu>
 */
public class StackNode<T> implements Stack<T> {
    private  Node<T> stack;
    private int size;
    /**
     * Create an empty stack with size 0.
     */
    public StackNode() {
        stack = new Node<T>(null ,null);
        size = 0;
    }

    /**
     * Checks if the stack is empty.
     * @return boolean.
     */
    @Override
    public boolean empty() {
        return stack.getData() == null;
    }
    /**
     * Removes top element from stack.
     * @return top element;
     */
    @Override
    public T pop() {
        assert (!empty());{

            T t = stack.getData();
            if (stack.getNext() == null) {
                stack.setData(null);
            } else {
                stack = stack.getNext();

            }
            size-=1;
            return t;
        }
    }
    /**
     * Adds an element to the top of the stack.
     */
    @Override
    public void push(T element) {
        if (stack.getData() == null) {
            stack.setData(element);
        } else {
            Node<T> old = stack;
            stack = new Node<>(element,old);

        }
        size+=1;
    }

    /**
     * Reveals top element of stack without removing it.
     * @return top element;
     */
    @Override
    public T top() {
        assert (!(stack.getData() == null));
        {
            return stack.getData();
        }
    }
    /**
     * Returns size of stack.
     * @return size;
     */
    public int getSize(){
        return size;
    }

}
