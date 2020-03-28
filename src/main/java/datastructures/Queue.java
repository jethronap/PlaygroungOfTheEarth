package datastructures;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<T>();

    /**
     * Constructor
     */
    public Queue() {
    }

    /**
     * create a queue with a first element
     * @param firstElem
     */
    public Queue(T firstElem) {
        offer(firstElem);
    }

    /**
     *
     * @return the list of a size
     */
    public int size() {
        return list.size();
    }

    /**
     *
     * @return true is queue is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * peek the element at the front of the queue
     * @return
     */
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Empty Queue");
        return list.peekFirst();
    }

    /**
     * remove an element from the front of the queue
     * @return
     */
    public T poll() {
        if (isEmpty()) throw new RuntimeException("Empty Queue");
        return list.removeFirst();
    }

    /**
     * add element at the back of the queue
     * @param elem
     */
    public void offer(T elem) {
        list.addLast(elem);
    }

    /**
     *
     * @return an iterator to allow the user traverse through the elements of a queue.
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
