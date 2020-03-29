package datastructures;


import java.util.*;

/**
 * Implementation of min priority queue.
 * Thank you william fiset - https://github.com/williamfiset
 */
public class MinPQueue<T extends Comparable<T>> { // elements inside our queue have to be comparable

    /**
     * Elements inside heap
     */
    private int heapSize = 0;

    /**
     * Internal capacity of heap, i.e. the size of the list
     */
    private int heapCapacity = 0;

    /**
     * A list of elements inside heap
     */
    private List<T> heap = null;

    /**
     * A map that keep record of possible indices of
     * a particular node in the heap. It allows for
     * O(log(n)) removals and O(1) for containment checks
     */
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    /**
     * Empty priority queue
     */
    public MinPQueue() {
        this(1);
    }

    /**
     * Priority queue with an initial capacity
     *
     * @param sz heap capacity
     */
    public MinPQueue(int sz) {
        heap = new ArrayList<>(sz);
    }

    /**
     * A priority queue using heapify in O(n)
     * http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
     *
     * @param elements
     */
    public MinPQueue(T[] elements) {

        heapSize = heapCapacity = elements.length;
        heap = new ArrayList<T>(heapCapacity);

        // place elements in heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elements[i], i);
            heap.add(elements[i]);
        }

        // heapify process
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }

    /**
     * Constructor, O(log(n))
     *
     * @param elements
     */
    public MinPQueue(Collection<T> elements) {
        this(elements.size());
        for (T element : elements) {
            add(element);
        }
    }

    /**
     * @return true if priority queue is empty
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Clears the heap, O(n)
     */
    public void clear() {
        for (int i = 0; i < heapCapacity; i++) {
            heap.set(i, null);
        }
        heapSize = 0;
        map.clear();
    }

    /**
     * @return the size of the heap
     */
    public int size() {
        return heapSize;
    }

    /**
     * @return the element with the lowest priority
     * if queue is empty null is returned
     */
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    /**
     * removes the root element, O(log(n))
     *
     * @return
     */
    public T poll() {
        return removeAt(0);
    }

    /**
     * O(1)
     *
     * @param element
     * @return true if element is in heap
     */
    public boolean contains(T element) {
        // map lookup
        if (element == null) return false;
        return map.containsKey(element);

//         linear scan for containment check

//        for (int i = 0; i < heapSize; i++) {
//            if (heap.get(i).equals(element)) {
//                return true;
//            } else {
//                return false;
//            }
//        }
// the choice between the two methods depends on the removals from the map the user wants
// if removals == additions, then use map implementation.
    }

    /**
     * Add non-null element to queue, O(log(n))
     *
     * @param element
     */
    public void add(T element) {

        if (element == null) throw new IllegalArgumentException();

        if (heapSize < heapCapacity) {
            heap.set(heapSize, element);
        } else {
            heap.add(element);
            heapSize++;
        }
        mapAdd(element, heapSize);

        bubbleUp(heapSize);
        heapSize++;
    }

    /**
     * Removes a particular element in the heap, O(log(n))
     *
     * @param element
     * @return
     */
    public boolean remove(T element) {
        if (element == null) return false;

        // logarithmic removal with map, O(log(n))
        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        return index != null;

        // linear removal through search, O(n)
//        for (int i = 0; i < heapSize; i++) {
//            if (element.equals(heap.get(i))) {
//                removeAt(i);
//                return true;
//            }
//        }
    }

    /**
     * Checks if heap is mean using recursion.
     * Makes sure the heap invariant is kept
     * If called with k=0 starts at root.
     *
     * @param k
     * @return true if invariant is maintained
     */
    public boolean isMinHeap(int k) {
        // if we are outside the bounds of the heap return true
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // make sure both children are bigger than the parent
        // returns false otherwise
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // recurse on both children to verify they're valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    /**
     * O(1)
     *
     * @param i valid index
     * @param j valid index
     * @return true if node i <= node j.
     */
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    /**
     * Bottom up swim of node, O(log(n))
     *
     * @param k
     */
    private void bubbleUp(int k) {

        // get the index of parent node with respect to k
        int parent = (k - 1) / 2;

        // bubble up: until we reach the root && less than our parent
        while (k > 0 && less(k, parent)) {
            // exchange k with parent
            swap(parent, k);
            k = parent;

            // get the index of the parent node with respect to k
            parent = (k - 1) / 2;
        }
    }

    /**
     * Top down sinking of node, O(log(n))
     *
     * @param k
     */
    private void bubbleDown(int k) {
// let i be the parent of node index
// left child index: 2i + 1
// right child index: 2i + 2

        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left; // arbitrarily assuming left is smaller

            // find if right is smaller than left
            if (right < heapSize && less(right, left)) {
                smallest = right;
            }
            // stop if we out of bounds or k cannot bubbleDown anymore
            if (left >= heapSize || less(k, smallest)) {
                break;
            }
            // move down following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    /**
     * Swaps two nodes, O(1)
     *
     * @param i valid index
     * @param j valid index
     */
    private void swap(int i, int j) {
        T iElem = heap.get(i);
        T jElem = heap.get(j);

        heap.set(i, jElem);
        heap.set(j, iElem);

        // here is the overhead for using map,
        // it can cost to swap elements inside it
        mapSwap(iElem, jElem, i, j);
    }

    /**
     * Removes node at given index, O(log(n))
     *
     * @param i
     * @return
     */
    private T removeAt(int i) {

        if (isEmpty()) return null;

        heapSize--;
        T removedData = heap.get(i);
        swap(i, heapSize);

        // explicitly remove the value
        heap.set(heapSize, null);
        mapRemove(removedData, heapSize);

        // remove last element
        if (i == heapSize) return removedData;

        T element = heap.get(i);

        // try sinking element
        bubbleDown(i);

        // if nothing happens try swimming
        if (heap.get(i).equals(element)) {
            bubbleUp(i);
        }
        return removedData;
    }

    /**
     * Add node value and its index to the map
     *
     * @param value
     * @param index
     */
    private void mapAdd(T value, int index) {
        TreeSet<Integer> set = map.get(value);

        // insert new value in the map
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);

            // value already exists in the map
        } else {
            set.add(index);
        }
    }

    /**
     * Removes the index at given value
     *
     * @param value
     * @param index
     */
    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index); // TreeSet removal time O(log(n)), because it's a balanced binary search tree in Java.
        if (set.size() == 0) map.remove(value);
    }

    /**
     * Extract index position for the given value
     *
     * @param value
     * @return
     * NOTE: if value exists multiple times, arbitrarily the highest is returned
     */
    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) return set.last();
        return null;
    }

    /**
     * Change the indices of two nodes within the map
     *
     * @param val1
     * @param val2
     * @param val1Index
     * @param val2Index
     */
    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {

        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val2Index);
        set2.remove(val1Index);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
