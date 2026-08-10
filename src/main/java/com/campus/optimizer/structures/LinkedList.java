package com.campus.optimizer.structures;

// Squad 4


// public void add(T data) {
//         Node<T> newNode = new Node<>(data);
//         if (head == null) {
//             head = newNode;
//         } else {
//             Node<T> current = head;
//             while (current.next != null) {
//                 current = current.next;
//             }
//             current.next = newNode;
//         }
//     }


// request_id,source_location_id,destination_location_id,category,urgency,time_submitted,deadline,status

public class LinkedList<T> {

    public class Node<D> {
        public D data;
        public Node<D> next;

        public Node(D data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    public LinkedList(T data) {
        this.head = new Node<>(data);
    }

    private Node<T> previous;

    public void add(T data) {
        if (previous == null) {
            previous = head;
            add(data);
        } else {
            Node<T> current = new Node<>(data);
            previous.next = current;
            previous = current;
        }
    }

    public Node<T> getHead() {
        return head;
    }

    public int size() {
        Node<T> current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}