package datastructures;

import java.util.*;

class Entry<K, V> {
    int hash;
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> other) {
        if (hash != other.hash) return false;
        return key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + "=> " + value;
    }
}

/**
 * Implementation of HashTable using separate chaining.
 */
public class HashTable<K, V> implements Iterable<K> {

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity;
    private int threshold;
    private int size = 0;
    /**
     * Array of linked lists that have entries.
     */
    private LinkedList<Entry<K, V>>[] table;

    public HashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTable(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Designated Constructor
     *
     * @param capacity
     * @param maxLoadFactor
     */
    public HashTable(int capacity, double maxLoadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity should not be less than 0.");
        }

        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)) {
            throw new IllegalArgumentException("Illegal max load factor!");
        }
        this.maxLoadFactor = maxLoadFactor;
        // we don't want the capacity to be too low.
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    /**
     * @return the numbers of elements inside the hash-table
     */
    public int size() {
        return size;
    }

    /**
     * @return true/false whether the hash-table is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Converts a hash value to an index. It makes the hash value positive and places it
     * into the domain [0, capacity)
     *
     * @param keyHash
     * @return
     */
    private int normalizeIndex(int keyHash) {
        return (keyHash & 0xFFFFFFF) % capacity;
    }

    /**
     * Empties the contents of the hash-table
     */
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    /**
     * @param key
     * @return true/false whether the key is inside the hash-table or not
     */
    public boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    /**
     * Places a value in the hash-table
     *
     * @param key
     * @param value
     * @return
     */
    public V insert(K key, V value) {

        if (key == null) throw new IllegalArgumentException("Cannot have a null key!");
        Entry<K, V> newEntry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(newEntry.hash);
        return bucketInsertEntry(bucketIndex, newEntry);
    }

    /**
     * Insert an entry in a given bucket if it doesn't exist.
     * If it does, update the entry value
     *
     * @param bucketIndex
     * @param entry
     * @return
     */
    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        // lazily create LinkedLists, no sense in using more memory than we need
        if (bucket == null) table[bucketIndex] = bucket = new LinkedList<>();

        Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
        if (existentEntry == null) {
            bucket.add(entry);
            if (++size > threshold) resizeTable();
            return null; // we use null to indicate there is no previous entry
        } else {
            V oldVal = existentEntry.value;
            existentEntry.value = entry.value;
            return oldVal;
        }
    }

    /**
     * Resizes the internal table holding bucket of entries
     */
    private void resizeTable() {

        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if (bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }
                // avoid memory leaks, helping the garbage collector
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }

    public V put(K key, V value) {
        return insert(key, value);
    }

    public V add(K key, V value) {
        return insert(key, value);
    }

    /**
     * Get a key's value from the map
     *
     * @param key
     * @return the value of the key
     * NOTE: returns null if both the key doesn't exist and when it's null
     */
    public V get(K key) {
        if (key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) return entry.value;
        return null;
    }

    /**
     * Find and return an entry in a given bucket if it exists
     *
     * @param bucketIndex
     * @param key
     * @return
     */
    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        if (key == null) return null;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) return null;
        for (Entry<K, V> entry : bucket)
            if (entry.key.equals(key))
                return entry;
        return null;
    }

    /**
     * Removes a key from the map
     *
     * @param key
     * @return the value
     * NOTE: returns null if both the value is null and the key doesn't exist
     */
    public V remove(K key) {
        if (key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    /**
     * Removes an entry from a given bucket if it exists
     *
     * @param bucketIndex
     * @param key
     * @return
     */
    private V bucketRemoveEntry(int bucketIndex, K key) {
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) {
            LinkedList<Entry<K, V>> entryLinkedList = table[bucketIndex];
            entryLinkedList.remove(entry);
            --size;
            return entry.value;
        } else return null;
    }

    /**
     * @return the list of keys found within the hash-table
     */
    public List<K> key() {
        List<K> keys = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null)
                for (Entry<K, V> entry : bucket)
                    keys.add(entry.key);
        return keys;
    }

    /**
     * @return the list of values found within the hash-table
     */
    public List<V> values() {
        List<V> values = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null)
                for (Entry<K, V> entry : bucket)
                    values.add(entry.value);
        return values;
    }

    @Override
    public Iterator<K> iterator() {
        final int elementCount = size();
        return new Iterator<K>() {
            int bucketIndex = 0;
            Iterator<Entry<K, V>> bucketIterator = (table[0] == null) ? null : table[0].iterator();

            @Override
            public boolean hasNext() {
                // an item was added or removed while iterating
                if (elementCount != size) throw new ConcurrentModificationException();

                // no iterator or the current iterator is empty
                if (bucketIterator == null || !bucketIterator.hasNext()) {
                    // search next buckets until a valid iterator is found
                    while (++bucketIndex < capacity) {
                        if (table[bucketIndex] != null) {
                            // make sure this iterator actually has elements
                            Iterator<Entry<K, V>> nextIterator = table[bucketIndex].iterator();
                            if (nextIterator.hasNext()) {
                                bucketIterator = nextIterator;
                                break;
                            }
                        }
                    }
                }
                return bucketIndex < capacity;
            }

            @Override
            public K next() {
                return bucketIterator.next().key;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     *
     * @return a String representation of this hash-table
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) continue;
            for (Entry<K,V> entry: table[i])
                stringBuilder.append(entry+", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
