package com.campus.optimizer.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Simplified B-tree used as a database index simulation.
 *
 * The tree uses a minimum degree of 2:
 * - each node can contain up to 3 keys
 * - a full node is split before insertion continues
 *
 * @param <T> type of values stored in the tree
 */
public class BTree<T extends Comparable<T>> {

    private static final int MINIMUM_DEGREE = 2;
    private static final int MAX_KEYS = (2 * MINIMUM_DEGREE) - 1;

    /**
     * A page/node in the simulated B-tree.
     */
    private class Node {
        List<T> keys = new ArrayList<>();
        List<Node> children = new ArrayList<>();
        boolean leaf;

        Node(boolean leaf) {
            this.leaf = leaf;
        }
    }

    private Node root;
    private int size;

    /**
     * Creates an empty B-tree.
     */
    public BTree() {
        root = new Node(true);
        size = 0;
    }

    /**
     * Inserts a value into the B-tree.
     *
     * @param value value to insert
     * @return true if inserted, false if already present
     */
    public boolean insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        if (contains(value)) {
            return false;
        }

        if (root.keys.size() == MAX_KEYS) {
            Node newRoot = new Node(false);
            newRoot.children.add(root);
            root = newRoot;

            splitChild(newRoot, 0);
        }

        insertNonFull(root, value);
        size++;

        return true;
    }

    /**
     * Inserts a value into a node that is known not to be full.
     */
    private void insertNonFull(Node node, T value) {
        int index = node.keys.size() - 1;

        if (node.leaf) {
            node.keys.add(null);

            while (index >= 0 &&
                    value.compareTo(node.keys.get(index)) < 0) {

                node.keys.set(index + 1, node.keys.get(index));
                index--;
            }

            node.keys.set(index + 1, value);
            return;
        }

        while (index >= 0 &&
                value.compareTo(node.keys.get(index)) < 0) {
            index--;
        }

        index++;

        if (node.children.get(index).keys.size() == MAX_KEYS) {
            splitChild(node, index);

            if (value.compareTo(node.keys.get(index)) > 0) {
                index++;
            }
        }

        insertNonFull(node.children.get(index), value);
    }

    /**
     * Splits a full child into two nodes.
     */
    private void splitChild(Node parent, int childIndex) {
        Node fullChild = parent.children.get(childIndex);
        Node rightChild = new Node(fullChild.leaf);

        T middleKey = fullChild.keys.get(1);

        rightChild.keys.add(fullChild.keys.get(2));
        fullChild.keys.remove(2);
        fullChild.keys.remove(1);

        if (!fullChild.leaf) {
            rightChild.children.add(fullChild.children.remove(2));
            rightChild.children.add(fullChild.children.remove(2));
        }

        parent.children.add(childIndex + 1, rightChild);
        parent.keys.add(childIndex, middleKey);
    }

    /**
     * Searches for a value in the B-tree.
     *
     * @param value value to search for
     * @return true if found
     */
    public boolean contains(T value) {
        if (value == null) {
            return false;
        }

        return search(root, value);
    }

    private boolean search(Node node, T value) {
        int index = 0;

        while (index < node.keys.size()
                && value.compareTo(node.keys.get(index)) > 0) {
            index++;
        }

        if (index < node.keys.size()
                && value.compareTo(node.keys.get(index)) == 0) {
            return true;
        }

        if (node.leaf) {
            return false;
        }

        return search(node.children.get(index), value);
    }

    /**
     * Returns the number of values stored in the B-tree.
     *
     * @return number of values
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree contains no values.
     *
     * @return true when empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the keys visited during a search.
     * Useful for demonstrating how a B-tree index searches pages.
     *
     * @param value value to search for
     * @return search path as a string
     */
    public String searchPath(T value) {
        if (value == null) {
            return "";
        }

        List<String> pages = new ArrayList<>();
        collectSearchPath(root, value, pages);

        return String.join(" -> ", pages);
    }

    private void collectSearchPath(
            Node node,
            T value,
            List<String> pages) {

        pages.add(node.keys.toString());

        int index = 0;

        while (index < node.keys.size()
                && value.compareTo(node.keys.get(index)) > 0) {
            index++;
        }

        if (index < node.keys.size()
                && value.compareTo(node.keys.get(index)) == 0) {
            return;
        }

        if (!node.leaf) {
            collectSearchPath(node.children.get(index), value, pages);
        }
    }
}
