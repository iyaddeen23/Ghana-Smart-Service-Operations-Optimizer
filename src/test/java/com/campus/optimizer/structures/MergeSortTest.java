package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sortShouldArrangeNumbersInAscendingOrder() {
        MergeSort<Integer> sorter = new MergeSort<>();

        Integer[] values = {38, 27, 43, 3, 9, 82, 10};

        sorter.sort(values);

        assertArrayEquals(
                new Integer[]{3, 9, 10, 27, 38, 43, 82},
                values
        );
    }

    @Test
    void sortShouldHandleAlreadySortedArray() {
        MergeSort<Integer> sorter = new MergeSort<>();

        Integer[] values = {1, 2, 3, 4, 5};

        sorter.sort(values);

        assertArrayEquals(
                new Integer[]{1, 2, 3, 4, 5},
                values
        );
    }

    @Test
    void sortShouldHandleReverseSortedArray() {
        MergeSort<Integer> sorter = new MergeSort<>();

        Integer[] values = {5, 4, 3, 2, 1};

        sorter.sort(values);

        assertArrayEquals(
                new Integer[]{1, 2, 3, 4, 5},
                values
        );
    }

    @Test
    void sortShouldHandleDuplicates() {
        MergeSort<Integer> sorter = new MergeSort<>();

        Integer[] values = {5, 2, 5, 1, 2, 3};

        sorter.sort(values);

        assertArrayEquals(
                new Integer[]{1, 2, 2, 3, 5, 5},
                values
        );
    }

    @Test
    void sortShouldHandleSingleElementArray() {
        MergeSort<Integer> sorter = new MergeSort<>();

        Integer[] values = {42};

        sorter.sort(values);

        assertArrayEquals(
                new Integer[]{42},
                values
        );
    }

    @Test
    void sortShouldHandleEmptyArray() {
        MergeSort<Integer> sorter = new MergeSort<>();

        Integer[] values = {};

        sorter.sort(values);

        assertArrayEquals(
                new Integer[]{},
                values
        );
    }

    @Test
    void sortShouldRejectNullArray() {
        MergeSort<Integer> sorter = new MergeSort<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> sorter.sort(null)
        );
    }
}