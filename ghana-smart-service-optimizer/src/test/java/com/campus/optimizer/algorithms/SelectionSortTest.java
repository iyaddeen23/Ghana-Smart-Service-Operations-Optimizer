package com.campus.optimizer.algorithms;

import com.campus.optimizer.algorithms.sort.SelectionSort;
import com.campus.optimizer.structures.DynamicArray;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void sorts_randomOrderAscending() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int v : new int[]{5, 3, 8, 1, 9, 2}) arr.insert(v);

        SelectionSort.sort(arr, Comparator.naturalOrder());

        int[] expected = {1, 2, 3, 5, 8, 9};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], arr.get(i));
        }
    }

    @Test
    void sorts_alreadySortedArray_worstCaseStillCorrect() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int v : new int[]{1, 2, 3, 4, 5}) arr.insert(v);
        SelectionSort.sort(arr, Comparator.naturalOrder());
        for (int i = 0; i < 5; i++) assertEquals(i + 1, arr.get(i));
    }

    @Test
    void sorts_reverseOrderArray() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int v : new int[]{5, 4, 3, 2, 1}) arr.insert(v);
        SelectionSort.sort(arr, Comparator.naturalOrder());
        for (int i = 0; i < 5; i++) assertEquals(i + 1, arr.get(i));
    }

    @Test
    void edgeCase_emptyArray() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        assertDoesNotThrow(() -> SelectionSort.sort(arr, Comparator.naturalOrder()));
        assertEquals(0, arr.size());
    }

    @Test
    void edgeCase_singleElement() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.insert(42);
        SelectionSort.sort(arr, Comparator.naturalOrder());
        assertEquals(42, arr.get(0));
    }

    @Test
    void edgeCase_duplicateKeys() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int v : new int[]{3, 1, 3, 2, 1}) arr.insert(v);
        SelectionSort.sort(arr, Comparator.naturalOrder());
        int[] expected = {1, 1, 2, 3, 3};
        for (int i = 0; i < expected.length; i++) assertEquals(expected[i], arr.get(i));
    }

    @Test
    void isInPlace_sizeUnchangedAfterSort() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int v : new int[]{9, 7, 5}) arr.insert(v);
        int sizeBefore = arr.size();
        SelectionSort.sort(arr, Comparator.naturalOrder());
        assertEquals(sizeBefore, arr.size()); // confirms in-place, no extra structure returned
    }
}
