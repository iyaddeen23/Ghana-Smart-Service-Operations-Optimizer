package com.campus.optimizer.algorithms.search;

import com.campus.optimizer.structures.DynamicArray;
import java.util.Comparator;

/**
 * Binary search over a DynamicArray.
 *
 * PRECONDITION (Section 6 requirement): the array MUST already be sorted in
 * ascending order according to the supplied comparator. This class verifies
 * that precondition explicitly and throws IllegalArgumentException if it does
 * not hold - this is the counterexample case "invalid precondition
 * (unsorted binary search input)" required by Section 8.
 *
 * Time complexity: O(log n) worst/average case, O(1) best case (middle hit).
 */
public final class BinarySearch {

    private BinarySearch() { }

    public static <T> int search(DynamicArray<T> sortedArray, T target, Comparator<T> comparator) {
        if (!isSorted(sortedArray, comparator)) {
            throw new IllegalArgumentException(
                "BinarySearch precondition violated: input array is not sorted ascending");
        }
        return searchUnchecked(sortedArray, target, comparator);
    }

    /** Skips the precondition check - only for callers that already guarantee a sorted array. */
    public static <T> int searchUnchecked(DynamicArray<T> sortedArray, T target, Comparator<T> comparator) {
        int lo = 0;
        int hi = sortedArray.size() - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(sortedArray.get(mid), target);
            if (cmp == 0) return mid;
            if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    public static <T> boolean isSorted(DynamicArray<T> array, Comparator<T> comparator) {
        for (int i = 1; i < array.size(); i++) {
            if (comparator.compare(array.get(i - 1), array.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }
}
