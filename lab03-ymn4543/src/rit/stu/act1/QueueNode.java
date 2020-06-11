package rit.stu.act1;

import rit.cs.Node;
import rit.cs.Queue;

/**
 * A queue implementation that uses a Node to represent the structure.
 * @param <T> The type of data the queue will hold
 * @author Sean Strout @ RIT CS
 * @author Youssef Naguib </ymn4543@rit.edu>
 */
public class QueueNode<T> implements Queue<T> {
    /** The front of the Queue */
    private Node<T> front;
    /** The back of the Queue */
    private Node<T>back;
    /** The size of the Queue */
    private int size;

    /**
     * Create an empty queue with size 0.
     */
    public QueueNode() {
        front = new Node<T>(null,null);
        back = new Node<T>(null,null);
        size = 0;
    }

    /**
     * Returns the back of the queue.
     * @return back element
     */
    @Override
    public T back() {
        assert (back.getData() != null);
        return back.getData();
    }
    /**
     * Removes an element from the front of the queue.
     * @return front element
     */
    @Override
    public T dequeue() {
        assert (front.getData() != null);
        size-=1;
        T remove = front.getData();
        if(front.getNext()==null){
            front.setData(null);
        }
        else {
            front = front.getNext();
        }
        return remove;
    }

    @Override
    /**
     * Checks if queue is empty.
     * @return boolean
     */
    public boolean empty() {
        return front.getData() == null;
    }

    @Override
    /**
     * Adds an element to the back of the queue.
     * @param: element is the element being added to the queue.
     */
    public void enqueue(T element) {
        size+=1;
        Node<T> new1 = new Node<>(element,null);
        if(front.getData() == null){
            front = new1;
            back = new1;
        }
        else {
            back.setNext(new1);
            back = new1;

        }
    }
    /**
     * Returns the element at the front of the queue, without removing it.
     * @return front element
     */
    @Override
    public T front() {
        assert (front != null);
        return front.getData();
    }

    /**
     * Returns the size of the queue.
     * @return queue size.
     */
    public int getSize(){
        return size;
    }
}
