package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedTreeTest {

    @Test
    void shouldInsertAndFindValues() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        assertTrue(tree.insert(50));
        assertTrue(tree.insert(30));
        assertTrue(tree.insert(70));

        assertTrue(tree.contains(50));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(70));
        assertFalse(tree.contains(100));
    }

    @Test
    void shouldRejectDuplicateValues() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        assertTrue(tree.insert(50));
        assertFalse(tree.insert(50));

        assertEquals(1, tree.size());
    }

    @Test
    void shouldRejectNullValues() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> tree.insert(null)
        );
    }

    @Test
    void shouldPerformLeftLeftRotation() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        assertEquals("10 20 30", tree.inorder());
        assertEquals(2, tree.height());
    }

    @Test
    void shouldPerformRightRightRotation() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        assertEquals("10 20 30", tree.inorder());
        assertEquals(2, tree.height());
    }

    @Test
    void shouldPerformLeftRightRotation() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        tree.insert(30);
        tree.insert(10);
        tree.insert(20);

        assertEquals("10 20 30", tree.inorder());
        assertEquals(2, tree.height());
    }

    @Test
    void shouldPerformRightLeftRotation() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        tree.insert(10);
        tree.insert(30);
        tree.insert(20);

        assertEquals("10 20 30", tree.inorder());
        assertEquals(2, tree.height());
    }

    @Test
    void inorderTraversalShouldReturnSortedValues() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        tree.insert(50);
        tree.insert(20);
        tree.insert(80);
        tree.insert(10);
        tree.insert(30);
        tree.insert(70);
        tree.insert(90);

        assertEquals("10 20 30 50 70 80 90", tree.inorder());
    }

    @Test
    void emptyTreeShouldHaveHeightZeroAndSizeZero() {
        BalancedTree<Integer> tree = new BalancedTree<>();

        assertEquals(0, tree.height());
        assertEquals(0, tree.size());
        assertFalse(tree.contains(50));
        assertEquals("", tree.inorder());
    }
}
