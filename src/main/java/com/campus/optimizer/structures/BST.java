package com.campus.optimizer.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree implemented from scratch.
 *
 * @param <T> type of values stored in the tree
 */
public class BST<T extends Comparable<T>> {

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> root;
    private int size;

    /**
     * Inserts a value into the BST.
     * Duplicate values are ignored.
     */
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("BST does not allow null values");
        }

        root = insertRecursive(root, value);
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }

        int comparison = value.compareTo(node.data);

        if (comparison < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (comparison > 0) {
            node.right = insertRecursive(node.right, value);
        }

        return node;
    }

    /**
     * Searches for a value in the BST.
     */
    public boolean search(T value) {
        if (value == null) {
            return false;
        }

        Node<T> current = root;

        while (current != null) {
            int comparison = value.compareTo(current.data);

            if (comparison == 0) {
                return true;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    /**
     * Returns the nodes visited while searching for a value.
     */
    public List<T> searchPath(T value) {
        List<T> path = new ArrayList<>();

        if (value == null) {
            return path;
        }

        Node<T> current = root;

        while (current != null) {
            path.add(current.data);

            int comparison = value.compareTo(current.data);

            if (comparison == 0) {
                break;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return path;
    }

    /**
     * Performs an inorder traversal.
     * For a BST, this produces sorted output.
     */
    public List<T> inorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }

        inorderRecursive(node.left, result);
        result.add(node.data);
        inorderRecursive(node.right, result);
    }

    /**
     * Returns the number of values in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the tree is empty.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes all values from the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
