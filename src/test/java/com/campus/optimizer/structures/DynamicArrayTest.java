package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void testInsertAndGet() {
        DynamicArray<String> array = new DynamicArray<>();

        array.insert("Accra");
        array.insert("Kumasi");

        assertEquals(2, array.size());
        assertEquals("Accra", array.get(0));
        assertEquals("Kumasi", array.get(1));
    }

    @Test
    void testSet() {
        DynamicArray<String> array = new DynamicArray<>();

        array.insert("Accra");
        array.insert("Kumasi");

        array.set(1, "Tamale");

        assertEquals("Tamale", array.get(1));
    }

    @Test
    void testRemoveLast() {
        DynamicArray<Integer> array = new DynamicArray<>();

        array.insert(10);
        array.insert(20);
        array.insert(30);

        assertEquals(30, array.removeLast());
        assertEquals(2, array.size());
        assertEquals(20, array.get(1));
    }

    @Test
    void testIsEmpty() {
        DynamicArray<String> array = new DynamicArray<>();

        assertTrue(array.isEmpty());

        array.insert("Test");

        assertFalse(array.isEmpty());
    }

    @Test
    void testClear() {
        DynamicArray<String> array = new DynamicArray<>();

        array.insert("Accra");
        array.insert("Kumasi");

        array.clear();

        assertTrue(array.isEmpty());
        assertEquals(0, array.size());
    }

    @Test
    void testDynamicResizing() {
        DynamicArray<Integer> array = new DynamicArray<>();

        // Initial capacity is 10.
        // Insert 20 elements to verify automatic resizing.
        for (int i = 0; i < 20; i++) {
            array.insert(i);
        }

        assertEquals(20, array.size());

        for (int i = 0; i < 20; i++) {
            assertEquals(i, array.get(i));
        }
    }

    @Test
    void testGetInvalidIndex() {
        DynamicArray<String> array = new DynamicArray<>();

        array.insert("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
    }

    @Test
    void testSetInvalidIndex() {
        DynamicArray<String> array = new DynamicArray<>();

        array.insert("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> array.set(1, "New"));
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(-1, "New"));
    }

    @Test
    void testRemoveLastFromEmptyArray() {
        DynamicArray<String> array = new DynamicArray<>();

        assertThrows(
                IllegalStateException.class,
                array::removeLast
        );
    }
}