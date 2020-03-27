package datastructures;

import java.util.Iterator;

public class DoubleLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;


    /**
     * internal class
     *
     * @param <T>
     */
    private class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }


    /**
     * O(n) complexity
     */
    public void clear() {
        Node<T> traversal = head;

        while (traversal != null) {
            Node<T> next = traversal.next;
            traversal.prev = traversal.next = null;
            traversal.data = null;
            traversal = next;
        }
        head = tail = traversal = null;
        size = 0;
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * add to the beginning of the list
     *
     * @param elem
     */
    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
    }


    /**
     * add to the end of the list
     *
     * @param elem
     */
    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * add an element to the tail of the list
     *
     * @param elem
     */
    public void add(T elem) {
        addLast(elem);
    }

    /**
     * check if the value of the first item exists, O(1)
     *
     * @return
     */
    public T checkFirst() {
        if (isEmpty()) throw new RuntimeException("List is Empty");
        return head.data;
    }

    /**
     * check if the value of the first item exists, O(1)
     *
     * @return
     */
    public T checkLast() {
        if (isEmpty()) throw new RuntimeException("List is Empty");
        return tail.data;
    }

    /**
     * remove the head of the list
     *
     * @return
     */
    public T removeHead() {

        //check is list is empty
        if (isEmpty()) throw new RuntimeException("List is Empty");
        // extract the data at the head and move the pointer forwards one node
        T data = head.data;
        head = head.next;
        --size;

        // if list is empty set the tail null as well.
        if (isEmpty()) tail = null;
            // clean up memory
        else head.prev = null;

        // return the data that was at the first node we just removed
        return data;
    }


    /**
     * remove the tail of the list
     *
     * @return
     */
    public T removeTail() {

        //check is list is empty
        if (isEmpty()) throw new RuntimeException("List is Empty");
        // extract the data at the head and move the pointer forwards one node
        T data = tail.data;
        tail = tail.prev;
        --size;

        // if list is empty set the head null as well.
        if (isEmpty()) head = null;
            // clean up memory
        else tail.next = null;

        // return the data that was at the first node we just removed
        return data;
    }

    /**
     * remove any given node
     *
     * @param node
     * @return
     */
    private T remove(Node<T> node) {
        // if node is at head or tail the handle them independently
        if (node.next == null) return removeTail();
        if (node.prev == null) return removeHead();

        // make the pointers of adjacent node skip over given node
        node.next.prev = node.prev;
        node.prev.next = node.next;

        // temporary store the node we want to return
        T data = node.data;

        // clean up memory
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    /**
     * remove a node at given index, O(n)
     *
     * @param index
     * @return
     */
    public T removeAtIndex(int index) {
        // check if given index is valid
        if (index < 0 || index >= size) throw new IllegalArgumentException("Given Index Does not Exist");

        int i;
        Node<T> traversal;

        // search from the head of the list
        if (index < size / 2) {
            for (i = 0, traversal = head; i != index; i++) {
                traversal = traversal.next;
            }

            // search from the tails of the list
        } else {
            for (i = size - 1, traversal = tail; i != index; --i) {
                traversal = traversal.prev;
            }
        }
        return remove(traversal);
    }


    /**
     * remove a particular value from the list, O(n)
     *
     * @param obj
     * @return
     */
    public boolean remove(Object obj) {

        Node<T> traversal = head;

        // support for searching for null
        if (obj == null) {
            for (traversal = head; traversal != null; traversal = traversal.next) {
                if (traversal.data == null) {
                    remove(traversal);
                    return true;
                }
            }
            // search for non null objects
        } else {
            for (traversal = head; traversal != null; traversal = traversal.next) {
                if (obj.equals(traversal.data)) {
                    remove(traversal);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Find the index of a given value, O(n)
     *
     * @param obj
     * @return
     */
    public int indexOf(Object obj) {

        int index = 0;
        Node<T> traversal = head;

        // support for searching for null
        if (obj == null) {
            for (traversal = head; traversal != null; traversal = traversal.next, index++) {
                if (traversal.data == null) {
                    remove(traversal);
                    return index;
                }
            }
            // search for non null objects
        } else {
            for (traversal = head; traversal != null; traversal = traversal.next, index++) {
                if (obj.equals(traversal.data)) {
                    remove(traversal);
                    return index;
                }
            }
        }
        return -1;
    }


    /**
     * check if the value is contained within the list
     *
     * @param obj
     * @return
     */
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> traversal = head;

            @Override
            public boolean hasNext() {
                return traversal != null;
            }

            @Override
            public T next() {
                T data = traversal.data;
                traversal = traversal.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node<T> traversal = head;
        while (traversal != null) {
            stringBuilder.append(traversal.data+ ",");
            traversal = traversal.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
