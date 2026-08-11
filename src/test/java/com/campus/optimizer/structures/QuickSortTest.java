package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testSortUnsortedArray() {
        QuickSort<Integer> quickSort = new QuickSort<>();

        Integer[] array = {50, 20, 70, 10, 40, 60, 30};

        quickSort.sort(array);

        assertArrayEquals(
                new Integer[]{10, 20, 30, 40, 50, 60, 70},
                array
        );
    }

    @Test
    void testSortAlreadySortedArray() {
        QuickSort<Integer> quickSort = new QuickSort<>();

        Integer[] array = {10, 20, 30, 40, 50};

        quickSort.sort(array);

        assertArrayEquals(
                new Integer[]{10, 20, 30, 40, 50},
                array
        );
    }

    @Test
    void testSortReverseOrderArray() {
        QuickSort<Integer> quickSort = new QuickSort<>();

        Integer[] array = {50, 40, 30, 20, 10};

        quickSort.sort(array);

        assertArrayEquals(
                new Integer[]{10, 20, 30, 40, 50},
                array
        );
    }

    @Test
    void testSortArrayWithDuplicates() {
        QuickSort<Integer> quickSort = new QuickSort<>();

        Integer[] array = {5, 2, 8, 2, 5, 1, 8};

        quickSort.sort(array);

        assertArrayEquals(
                new Integer[]{1, 2, 2, 5, 5, 8, 8},
                array
        );
    }

    @Test
    void testSortSingleElementArray() {
        QuickSort<Integer> quickSort = new QuickSort<>();

        Integer[] array = {42};

        quickSort.sort(array);

        assertArrayEquals(
                new Integer[]{42},
                array
        );
    }

    @Test
    void testSortEmptyArray() {
        QuickSort<Integer> quickSort = new QuickSort<>();

        Integer[] array = {};

        quickSort.sort(array);

        assertArrayEquals(
                new Integer[]{},
                array
        );
    }

    @Test
    void testNullArrayShouldBeRejected() {
        QuickSort<Integer> quickSort = new QuickSort<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> quickSort.sort(null)
        );
    }
}