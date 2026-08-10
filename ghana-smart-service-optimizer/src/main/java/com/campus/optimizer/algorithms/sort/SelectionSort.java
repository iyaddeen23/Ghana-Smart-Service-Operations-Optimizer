package com.campus.optimizer.algorithms.sort;

import com.campus.optimizer.structures.DynamicArray;
import java.util.Comparator;

/**
 * In-place selection sort over a DynamicArray.
 * Not stable (may reorder equal keys - swap-based selection of the minimum).
 * Time complexity: O(n^2) in all cases (best/average/worst) - always scans
 * the remaining unsorted region to find the minimum, regardless of input order.
 */
public final class SelectionSort {

    private SelectionSort() { }

    public static <T> void sort(DynamicArray<T> array, Comparator<T> comparator) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(array.get(j), array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
    }

    private static <T> void swap(DynamicArray<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
