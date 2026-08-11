package com.campus.optimizer.structures;

import java.util.NoSuchElementException;

/**
 * Custom Min-Heap Priority Queue built from scratch.
 * Squad 3: Tina & Anu
 */
public class PriorityQueue<T extends Comparable<T>> {

    private DynamicArray<T> heap;

    public PriorityQueue() {
        heap = new DynamicArray<>();
    }

    /**
     * Inserts a new element into the priority queue.
     */
    public void insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Priority Queue does not allow null values");
        }

        heap.insert(element);
        siftUp(heap.size() - 1);
    }

    /**
     * Removes and returns the minimum element (highest priority).
     */
    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority Queue is empty");
        }

        T min = heap.get(0);
        T last = heap.removeLast();

        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }

        return min;
    }

    /**
     * Returns the minimum element without removing it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority Queue is empty");
        }

        return heap.get(0);
    }

    /**
     * Checks whether the priority queue is empty.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns the number of elements.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Restores the heap property upward.
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Restores the heap property downward.
     */
    private void siftDown(int index) {
        int minIndex = index;

        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size()
                && heap.get(leftChild).compareTo(heap.get(minIndex)) < 0) {
            minIndex = leftChild;
        }

        if (rightChild < heap.size()
                && heap.get(rightChild).compareTo(heap.get(minIndex)) < 0) {
            minIndex = rightChild;
        }

        if (minIndex != index) {
            swap(index, minIndex);
            siftDown(minIndex);
        }
    }

    /**
     * Swaps two elements in the heap.
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
