package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    @Test
    void shouldInsertAndFindValues() {
        BTree<Integer> tree = new BTree<>();

        assertTrue(tree.insert(50));
        assertTrue(tree.insert(30));
        assertTrue(tree.insert(70));
        assertTrue(tree.insert(20));
        assertTrue(tree.insert(40));

        assertTrue(tree.contains(50));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(70));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(40));

        assertFalse(tree.contains(100));
    }

    @Test
    void shouldRejectDuplicateValues() {
        BTree<Integer> tree = new BTree<>();

        assertTrue(tree.insert(50));
        assertFalse(tree.insert(50));

        assertEquals(1, tree.size());
    }

    @Test
    void shouldRejectNullValues() {
        BTree<Integer> tree = new BTree<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> tree.insert(null)
        );
    }

    @Test
    void shouldStartEmpty() {
        BTree<Integer> tree = new BTree<>();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void shouldSplitFullNodeDuringInsertion() {
        BTree<Integer> tree = new BTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        assertEquals(5, tree.size());

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(40));
        assertTrue(tree.contains(50));
    }

    @Test
    void shouldReturnSearchPath() {
        BTree<Integer> tree = new BTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        String path = tree.searchPath(40);

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertTrue(path.contains("40"));
    }

    @Test
    void shouldReturnFalseForNullSearch() {
        BTree<Integer> tree = new BTree<>();

        tree.insert(10);

        assertFalse(tree.contains(null));
    }
}
