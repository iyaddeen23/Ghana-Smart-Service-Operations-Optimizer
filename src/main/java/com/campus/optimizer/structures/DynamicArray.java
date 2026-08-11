package com.campus.optimizer.structures;

/**
 * Custom dynamic array implemented from scratch.
 *
 * @param <T> type of elements stored in the array
 */
public class DynamicArray<T> {

    private Object[] data;
    private int size;

    public DynamicArray() {
        data = new Object[10];
        size = 0;
    }

    /**
     * Adds an element to the end of the array.
     */
    public void insert(T element) {
        ensureCapacity();
        data[size] = element;
        size++;
    }

    /**
     * Returns the element at the specified index.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    /**
     * Replaces the element at the specified index.
     */
    public void set(int index, T element) {
        checkIndex(index);
        data[index] = element;
    }

    /**
     * Removes and returns the last element.
     */
    @SuppressWarnings("unchecked")
    public T removeLast() {
        if (size == 0) {
            throw new IllegalStateException("Dynamic array is empty");
        }

        T element = (T) data[size - 1];
        data[size - 1] = null;
        size--;

        return element;
    }

    /**
     * Returns the number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the array is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements.
     */
    public void clear() {
        data = new Object[10];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];

            System.arraycopy(data, 0, newData, 0, data.length);

            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }
}
