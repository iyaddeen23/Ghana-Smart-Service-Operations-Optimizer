package com.campus.optimizer.structures;

/**
 * Custom merge sort implementation from scratch.
 *
 * @param <T> type of elements being sorted
 */
public class MergeSort<T extends Comparable<T>> {

    /**
     * Sorts the given array in ascending order using merge sort.
     *
     * @param array array to sort
     */
    public void sort(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        if (array.length < 2) {
            return;
        }

        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(T[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(array, left, middle);
        mergeSort(array, middle + 1, right);

        merge(array, left, middle, right);
    }

    private void merge(T[] array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Comparable[leftSize];

        @SuppressWarnings("unchecked")
        T[] rightArray = (T[]) new Comparable[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}