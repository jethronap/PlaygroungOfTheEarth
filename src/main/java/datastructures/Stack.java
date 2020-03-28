package datastructures;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<T>();

    /**
     * Constructor
     */
    public Stack() {
    }

    /**
     * create stack with first element
     * @param firstElem
     */
    public Stack (T firstElem) {
        push(firstElem);
    }

    /**
     *
     * @return the number of elements in the stack
     */
    public int size() {
        return list.size();
    }

    /**
     *
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * push an element on the stack
     * @param elem
     */
    public void push(T elem) {
        list.addLast(elem);
    }


    /**
     * removes an element from the stack
     * @return
     */
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    /**
     * peeks at the top of the stack without removing an element
     * @return
     */
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }


    /**
     * iterate through the stack using an iterator
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
