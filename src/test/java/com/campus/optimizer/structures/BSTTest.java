package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @Test
    void insertAndSearchShouldWork() {
        BST<Integer> bst = new BST<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        assertTrue(bst.search(50));
        assertTrue(bst.search(20));
        assertTrue(bst.search(80));
        assertFalse(bst.search(100));
    }

    @Test
    void duplicateValuesShouldNotIncreaseSize() {
        BST<Integer> bst = new BST<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(50);
        bst.insert(30);

        assertEquals(2, bst.size());
    }

    @Test
    void inorderTraversalShouldReturnSortedValues() {
        BST<Integer> bst = new BST<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        List<Integer> result = bst.inorderTraversal();

        assertEquals(
                List.of(20, 30, 40, 50, 60, 70, 80),
                result
        );
    }

    @Test
    void searchPathShouldShowVisitedNodes() {
        BST<Integer> bst = new BST<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        assertEquals(
                List.of(50, 30, 40),
                bst.searchPath(40)
        );

        assertEquals(
                List.of(50, 70, 80),
                bst.searchPath(80)
        );
    }

    @Test
    void searchPathForMissingValueShouldShowSearchRoute() {
        BST<Integer> bst = new BST<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        assertEquals(
                List.of(50, 30, 40),
                bst.searchPath(45)
        );
    }

    @Test
    void emptyTreeShouldBehaveCorrectly() {
        BST<Integer> bst = new BST<>();

        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertFalse(bst.search(10));
        assertTrue(bst.inorderTraversal().isEmpty());
        assertTrue(bst.searchPath(10).isEmpty());
    }

    @Test
    void clearShouldRemoveAllValues() {
        BST<Integer> bst = new BST<>();

        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        assertEquals(3, bst.size());

        bst.clear();

        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertTrue(bst.inorderTraversal().isEmpty());
    }

    @Test
    void nullInsertShouldBeRejected() {
        BST<Integer> bst = new BST<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> bst.insert(null)
        );
    }

    @Test
    void nullSearchShouldReturnFalse() {
        BST<Integer> bst = new BST<>();

        bst.insert(10);

        assertFalse(bst.search(null));
        assertTrue(bst.searchPath(null).isEmpty());
    }
}
