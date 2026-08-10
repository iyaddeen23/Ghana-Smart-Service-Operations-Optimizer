package com.campus.optimizer;

import com.campus.optimizer.algorithms.sort.InsertionSort;
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
        System.out.println("\nSize of stack: " + stack.count);
        System.out.println("Top most element is " + stack.top());

        // Testing insertion sort algorithm
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("Array before sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        InsertionSort.Sort(arr);
        System.out.println("\nArray after sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
