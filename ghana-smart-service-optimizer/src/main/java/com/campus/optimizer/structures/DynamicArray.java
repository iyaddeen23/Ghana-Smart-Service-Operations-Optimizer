package com.campus.optimizer.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A resizable array built from scratch (no java.util.ArrayList).
 * Backing store is a plain Object[] cast internally.
 *
 * Amortised cost: insert/remove at end O(1) amortised, get/set O(1),
 * insert/remove at arbitrary index O(n) (element shifting).
 */
public class DynamicArray<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 8;

    private Object[] data;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) initialCapacity = DEFAULT_CAPACITY;
        data = new Object[initialCapacity];
        size = 0;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public int capacity() { return data.length; }

    /** Appends to the end. O(1) amortised. */
    public void insert(T value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    /** Inserts at a specific index, shifting later elements right. O(n). */
    public void insertAt(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("insertAt index: " + index + ", size: " + size);
        }
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    /** Removes and returns the element at index, shifting later elements left. O(n). */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }

    /** Doubles capacity when full; this is the resize trace point used in evidence. */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= data.length) return;
        int newCapacity = data.length * 2;
        if (newCapacity < minCapacity) newCapacity = minCapacity;
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) data[i] = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() { return cursor < size; }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) data[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
