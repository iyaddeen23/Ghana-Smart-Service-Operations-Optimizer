package com.campus.optimizer.algorithms;

import com.campus.optimizer.algorithms.search.LinearSearch;
import com.campus.optimizer.structures.DynamicArray;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class LinearSearchTest {

    private DynamicArray<Integer> unsorted() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int v : new int[]{40, 10, 30, 20, 50}) arr.insert(v);
        return arr;
    }

    @Test
    void findsElement_inUnsortedArray() {
        int idx = LinearSearch.search(unsorted(), 30, Comparator.naturalOrder());
        assertEquals(2, idx);
    }

    @Test
    void findsFirstElement_bestCase() {
        int idx = LinearSearch.search(unsorted(), 40, Comparator.naturalOrder());
        assertEquals(0, idx);
    }

    @Test
    void returnsMinusOne_whenAbsent() {
        int idx = LinearSearch.search(unsorted(), 999, Comparator.naturalOrder());
        assertEquals(-1, idx);
    }

    @Test
    void edgeCase_emptyArray() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        assertEquals(-1, LinearSearch.search(arr, 1, Comparator.naturalOrder()));
    }

    @Test
    void edgeCase_singleElementFound() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(7);
        assertEquals(0, LinearSearch.search(arr, 7, Comparator.naturalOrder()));
    }
}
