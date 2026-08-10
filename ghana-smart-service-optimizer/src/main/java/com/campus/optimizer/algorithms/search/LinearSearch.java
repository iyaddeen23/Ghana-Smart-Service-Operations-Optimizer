package com.campus.optimizer.algorithms.search;

import com.campus.optimizer.structures.DynamicArray;
import java.util.Comparator;

/**
 * Linear search over a DynamicArray. No precondition on ordering.
 * Time complexity: O(n) worst/average case, O(1) best case (first element).
 */
public final class LinearSearch {

    private LinearSearch() { }

    /**
     * @return index of the first element equal to target (via comparator == 0), or -1 if absent.
     */
    public static <T> int search(DynamicArray<T> array, T target, Comparator<T> comparator) {
        int operations = 0; // primitive-operation counter for efficiency analysis (Section 6)
        for (int i = 0; i < array.size(); i++) {
            operations++;
            if (comparator.compare(array.get(i), target) == 0) {
                return i;
            }
        }
        return -1;
    }
}
