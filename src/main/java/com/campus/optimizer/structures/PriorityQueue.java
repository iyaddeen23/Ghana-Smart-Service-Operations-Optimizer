package com.campus.optimizer.structures;

import java.util.NoSuchElementException;

/**
 * Custom Min-Heap Priority Queue built from scratch.
 * Squad 3: Tina & Anu
 */
public class PriorityQueue<T extends Comparable<T>> {
    private DynamicArray<T> heap;

    public PriorityQueue() {
        this.heap = new DynamicArray<>();
    }

    /**
     * Inserts a new element into the priority queue.
     */
    // public void insert(T element) {
    //     heap.insert(element);
    //     siftUp(heap.size() - 1);
    // }

    /**
     * Removes and returns the minimum element (highest priority).
     */
    // public T extractMin() {
    //     if (isEmpty()) {
    //         throw new NoSuchElementException("Priority Queue is empty");
    //     }
    //     T min = heap.get(0);
    //     T last = heap.removeLast();
        
    //     if (heap.size() > 0) {
    //         heap.set(0, last);
    //         siftDown(0);
    //     }
    //     return min;
    // }

    /**
     * Returns the minimum element without removing it.
     */
    // public T peek() {
    //     if (isEmpty()) {
    //         throw new NoSuchElementException("Priority Queue is empty");
    //     }
    //     return heap.get(0);
    // }

    // public boolean isEmpty() {
    //     return heap.size() == 0;
    // }

    // public int size() {
    //     return heap.size();
    // }

    // private void siftUp(int index) {
    //     while (index > 0) {
    //         int parentIndex = (index - 1) / 2;
    //         if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
    //             swap(index, parentIndex);
    //             index = parentIndex;
    //         } else {
    //             break;
    //         }
    //     }
    // }

    // private void siftDown(int index) {
    //     int minIndex = index;
    //     int leftChild = 2 * index + 1;
    //     int rightChild = 2 * index + 2;

    //     if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(minIndex)) < 0) {
    //         minIndex = leftChild;
    //     }

    //     if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(minIndex)) < 0) {
    //         minIndex = rightChild;
    //     }

    //     if (index != minIndex) {
    //         swap(index, minIndex);
    //         siftDown(minIndex);
    //     }
    // }

    // private void swap(int i, int j) {
    //     T temp = heap.get(i);
    //     heap.set(i, heap.get(j));
    //     heap.set(j, temp);
    // }
}
