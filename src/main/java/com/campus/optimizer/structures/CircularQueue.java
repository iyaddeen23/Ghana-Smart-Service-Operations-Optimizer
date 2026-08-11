package com.campus.optimizer.structures;
import java.util.NoSuchElementException;


// Squad 2

public class CircularQueue<T> {
    private final Object[] data;
    private final int capacity;
    private int front;
    private int rear;
    private int count;

    public CircularQueue(int capacity){
        
    if (capacity <= 0) {
        throw new IllegalArgumentException("Capacity must be greater than 0")
    
    }
    this.capacity = capacity;
    this.data = new Object[capacity];
    this.front = 0;
    this.rear = 0;
    this.count = 0;    
}
    public int size() {
        return count;
    }
    public boolean isEmpty() {
        return count == 0;
    }
    public boolean isFull() {
        return count == capacity;
    }
    public int frontIndex() {
        return front;
    }
    public int rearIndex() {
        return rear;
    }

    public void enque(T value) {
        if (isFull()) {
            throw new IllegalArgumentException("CircularQueue is full");
        }
        data[rear] = value;
        rear = (rear + 1) % capacity;
        count++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("CircularQueue is empty");
        }
        T value = (T) data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        count--;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("CircularQueue is empty");
    }
    return (T) data[front];
}
}
