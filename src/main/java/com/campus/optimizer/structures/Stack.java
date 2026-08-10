package com.campus.optimizer.structures;

import java.util.ArrayList;

// Squad 4

public class Stack<T> {
    public static int count = 0;
    ArrayList<T> stack = new ArrayList<>();


    public boolean isEmpty() {
        return count == 0;
    }

    public void push(T value) {
        stack.add(value);
        count ++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty, cannot remove objects");
            return null;
        }
        T deletedElement = stack.get(count - 1);
        stack.remove(count - 1);
        return deletedElement;
    }

    public int count() {
        return count;
    }

    public T top() {
        return stack.get(count - 1);
    }
}
