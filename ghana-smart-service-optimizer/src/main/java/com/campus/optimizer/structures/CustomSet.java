package com.campus.optimizer.structures;

/**
 * A Set built directly on top of our own HashTable (not java.util.HashSet).
 * Used for fast membership checks, e.g. "has this locationId already been
 * loaded?" or "which locations are visited in this traversal?".
 */
public class CustomSet<T> {

    private static final Object PRESENT = new Object();
    private final HashTable<T, Object> table;

    public CustomSet() {
        table = new HashTable<>();
    }

    public CustomSet(int initialCapacity) {
        table = new HashTable<>(initialCapacity);
    }

    /** Returns true if the element was newly added (was not already present). */
    public boolean add(T element) {
        if (table.containsKey(element)) return false;
        table.put(element, PRESENT);
        return true;
    }

    public boolean contains(T element) {
        return table.containsKey(element);
    }

    public boolean remove(T element) {
        if (!table.containsKey(element)) return false;
        table.remove(element);
        return true;
    }

    public int size() { return table.size(); }

    public boolean isEmpty() { return table.isEmpty(); }
}
