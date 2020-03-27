package datastructures;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T arr[];
    private int len = 0; // the size the user thinks array has
    private int capacity = 0; // the actual array size

    public DynamicArray(int capacity) {

        if (capacity < 0) throw new IllegalArgumentException("Capacity should be greater than zero!");

        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public DynamicArray() {

        this(13);
    }

    public int size() {
        return len;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T elem) {
        arr[index] = elem;
    }

    // remove all the elements of the array and reset length
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T elem) {
        // decide when to resize the Array
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double the size of the array
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++)
                new_arr[i] = arr[i];
            arr = new_arr; // now arr has extra null positions
        }
        arr[len++] = elem;
    }

    public T removeAtIndex(int rmIndex) {
        if (rmIndex >= len && rmIndex < 0) throw new IndexOutOfBoundsException("Make sure index exists in Array");
        T data = arr[rmIndex];
        T[] new_arr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++)
            if (i == rmIndex) j--; // leave out rmIndex by temporarily fixing j
            else new_arr[j] = arr[i];
        arr = new_arr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAtIndex(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj))
                return i;
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder stringBuilder = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) {
                stringBuilder.append(arr[i] + ",");
            }
            return stringBuilder.append(arr[len - 1] + "]").toString();
        }
    }
}
