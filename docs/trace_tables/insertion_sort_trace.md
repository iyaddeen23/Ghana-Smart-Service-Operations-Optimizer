# Insertion Sort Trace

Input array: [5, 2, 4, 6, 1, 3]

| Step | Key | Action | Array state |
|---|---:|---|---|
| 0 | - | Initial array | [5, 2, 4, 6, 1, 3] |
| 1 | 2 | Insert 2 before 5 | [2, 5, 4, 6, 1, 3] |
| 2 | 4 | Insert 4 between 2 and 5 | [2, 4, 5, 6, 1, 3] |
| 3 | 6 | 6 already follows 5 | [2, 4, 5, 6, 1, 3] |
| 4 | 1 | Shift 6, 5, 4, and 2; insert 1 | [1, 2, 4, 5, 6, 3] |
| 5 | 3 | Shift 6, 5, and 4; insert 3 | [1, 2, 3, 4, 5, 6] |

## Result

Sorted array: **[1, 2, 3, 4, 5, 6]**

The trace demonstrates how insertion sort maintains a sorted prefix and inserts each new element into its correct position.
