package com.campus.optimizer;

import com.campus.optimizer.structures.LinkedList;
import com.campus.optimizer.structures.Stack;

/**
 * Entry point — launches the console menu.
 */
public class Main {
    public static void main(String[] args) {
        // TODO: wire up DatabaseConnection, DataLoader, and launch ConsoleMenu

        // Testing Linked List data structure
        LinkedList<String> list = new LinkedList<>("First");
        list.add("Second");
        list.add("Third");
        
        System.out.println("Size of the list: " + list.size());
        System.out.println("Second in the list: " + list.getHead().next.data);

        // Testing stack data structure
        Stack<String> stack = new Stack<>();
        stack.push("Victor");
        stack.push("Wakay");
        stack.push("Yeng");
        stack.push("Jace");

        System.out.println("Size of stack: " + stack.count);
        System.out.println("Top most element is " + stack.top());
    }
}
