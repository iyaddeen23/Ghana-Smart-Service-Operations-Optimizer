package com.campus.optimizer.structures;

/**
 * Simplified balanced binary search tree.
 * Uses AVL-style height balancing after insertion.
 *
 * @param <T> type of values stored in the tree
 */
public class BalancedTree<T extends Comparable<T>> {

    /**
     * A node in the balanced tree.
     */
    private class Node {
        T data;
        Node left;
        Node right;
        int height;

        Node(T data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    /**
     * Inserts a value into the tree.
     *
     * @param value value to insert
     * @return true if inserted, false if the value already exists
     */
    public boolean insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        int oldSize = size();
        root = insert(root, value);
        return size() > oldSize;
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        int comparison = value.compareTo(node.data);

        if (comparison < 0) {
            node.left = insert(node.left, value);
        } else if (comparison > 0) {
            node.right = insert(node.right, value);
        } else {
            // Duplicate values are not inserted.
            return node;
        }

        updateHeight(node);

        return rebalance(node);
    }

    /**
     * Searches for a value in the tree.
     *
     * @param value value to search for
     * @return true if the value exists
     */
    public boolean contains(T value) {
        Node current = root;

        while (current != null) {
            int comparison = value.compareTo(current.data);

            if (comparison == 0) {
                return true;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    /**
     * Returns the height of the tree.
     *
     * @return tree height
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Returns the number of values stored in the tree.
     *
     * @return number of values
     */
    public int size() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /**
     * Returns the values using inorder traversal.
     * The result is sorted.
     *
     * @return inorder representation of the tree
     */
    public String inorder() {
        StringBuilder result = new StringBuilder();
        inorder(root, result);
        return result.toString().trim();
    }

    private void inorder(Node node, StringBuilder result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);

        if (result.length() > 0) {
            result.append(" ");
        }

        result.append(node.data);

        inorder(node.right, result);
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    /**
     * Calculates the balance factor.
     *
     * balance factor = height(left) - height(right)
     */
    private int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    /**
     * Restores the balance of a node after insertion.
     */
    private Node rebalance(Node node) {
        int balance = balanceFactor(node);

        // Left-heavy
        if (balance > 1) {

            // Left-Right case
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }

            // Left-Left case
            return rotateRight(node);
        }

        // Right-heavy
        if (balance < -1) {

            // Right-Left case
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }

            // Right-Right case
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Performs a right rotation.
     */
    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        Node movedSubtree = newRoot.right;

        newRoot.right = node;
        node.left = movedSubtree;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Performs a left rotation.
     */
    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        Node movedSubtree = newRoot.left;

        newRoot.left = node;
        node.right = movedSubtree;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }
}
