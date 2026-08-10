package com.campus.optimizer.algorithms;

import com.campus.optimizer.algorithms.search.BinarySearch;
import com.campus.optimizer.structures.DynamicArray;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    private DynamicArray<Integer> sorted() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int v : new int[]{10, 20, 30, 40, 50}) arr.insert(v);
        return arr;
    }

    @Test
    void findsMiddleElement() {
        int idx = BinarySearch.search(sorted(), 30, Comparator.naturalOrder());
        assertEquals(2, idx);
    }

    @Test
    void findsFirstAndLastElement() {
        DynamicArray<Integer> arr = sorted();
        assertEquals(0, BinarySearch.search(arr, 10, Comparator.naturalOrder()));
        assertEquals(4, BinarySearch.search(arr, 50, Comparator.naturalOrder()));
    }

    @Test
    void returnsMinusOne_whenAbsent() {
        int idx = BinarySearch.search(sorted(), 25, Comparator.naturalOrder());
        assertEquals(-1, idx);
    }

    @Test
    void isSorted_trueForAscendingArray() {
        assertTrue(BinarySearch.isSorted(sorted(), Comparator.naturalOrder()));
    }

    /**
     * COUNTEREXAMPLE (required by Section 8): binary search's precondition is
     * that the input must be sorted. Feeding it an unsorted array must be
     * rejected loudly rather than silently returning a wrong/misleading index.
     */
    @Test
    void counterexample_unsortedInputViolatesPrecondition() {
        DynamicArray<Integer> unsorted = new DynamicArray<>();
        for (int v : new int[]{5, 1, 4, 2, 3}) unsorted.insert(v);

        assertFalse(BinarySearch.isSorted(unsorted, Comparator.naturalOrder()));
        assertThrows(IllegalArgumentException.class,
                () -> BinarySearch.search(unsorted, 3, Comparator.naturalOrder()));
    }

    @Test
    void edgeCase_emptyArray() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        assertEquals(-1, BinarySearch.search(arr, 1, Comparator.naturalOrder()));
    }

    @Test
    void edgeCase_singleElement() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(7);
        assertEquals(0, BinarySearch.search(arr, 7, Comparator.naturalOrder()));
        assertEquals(-1, BinarySearch.search(arr, 8, Comparator.naturalOrder()));
    }
}
