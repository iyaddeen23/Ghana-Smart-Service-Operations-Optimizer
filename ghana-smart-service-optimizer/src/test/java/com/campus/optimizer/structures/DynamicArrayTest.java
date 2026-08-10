package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void insertAndGet_basicSequence() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(10);
        arr.insert(20);
        arr.insert(30);
        assertEquals(3, arr.size());
        assertEquals(10, arr.get(0));
        assertEquals(20, arr.get(1));
        assertEquals(30, arr.get(2));
    }

    @Test
    void set_overwritesValueAtIndex() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.insert("a");
        arr.set(0, "z");
        assertEquals("z", arr.get(0));
    }

    @Test
    void insertAt_shiftsLaterElementsRight() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(1);
        arr.insert(3);
        arr.insertAt(1, 2); // [1,2,3]
        assertEquals(1, arr.get(0));
        assertEquals(2, arr.get(1));
        assertEquals(3, arr.get(2));
    }

    @Test
    void remove_shiftsLaterElementsLeft() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(1);
        arr.insert(2);
        arr.insert(3);
        int removed = arr.remove(1); // remove "2"
        assertEquals(2, removed);
        assertEquals(2, arr.size());
        assertEquals(1, arr.get(0));
        assertEquals(3, arr.get(1));
    }

    /** Resize trace: capacity starts at 8; inserting a 9th element must double it to 16. */
    @Test
    void resize_doublesCapacityWhenFull() {
        DynamicArray<Integer> arr = new DynamicArray<>(4); // small capacity to force resize fast
        assertEquals(4, arr.capacity());
        for (int i = 0; i < 4; i++) arr.insert(i);
        assertEquals(4, arr.capacity()); // still exactly full, not yet resized

        arr.insert(99); // triggers resize
        assertEquals(8, arr.capacity());
        assertEquals(5, arr.size());
        assertEquals(99, arr.get(4));
    }

    @Test
    void edgeCase_emptyArrayHasZeroSize() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        assertTrue(arr.isEmpty());
        assertEquals(0, arr.size());
    }

    @Test
    void edgeCase_getOutOfBoundsThrows() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(1);
        assertThrows(IndexOutOfBoundsException.class, () -> arr.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> arr.get(-1));
    }

    @Test
    void iterator_visitsElementsInOrder() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(1);
        arr.insert(2);
        arr.insert(3);
        int sum = 0;
        for (int v : arr) sum += v;
        assertEquals(6, sum);
    }
}
