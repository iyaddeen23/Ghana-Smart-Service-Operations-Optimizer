package com.campus.optimizer.structures;

import java.util.NoSuchElementException;

/**
 * Custom double-ended queue implemented from scratch.
 *
 * @param <T> type of elements stored in the deque
 */
public class Deque<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size;

    /**
     * Adds an element to the front of the deque.
     */
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.previous = newNode;
            front = newNode;
        }

        size++;
    }

    /**
     * Adds an element to the rear of the deque.
     */
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.previous = rear;
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the first element.
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        T element = front.data;

        if (size == 1) {
            front = rear = null;
        } else {
            front = front.next;
            front.previous = null;
        }

        size--;
        return element;
    }

    /**
     * Removes and returns the last element.
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        T element = rear.data;

        if (size == 1) {
            front = rear = null;
        } else {
            rear = rear.previous;
            rear.next = null;
        }

        size--;
        return element;
    }

    /**
     * Returns the first element without removing it.
     */
    public T peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        return front.data;
    }

    /**
     * Returns the last element without removing it.
     */
    public T peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        return rear.data;
    }

    /**
     * Returns the number of elements in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the deque is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the deque.
     */
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}