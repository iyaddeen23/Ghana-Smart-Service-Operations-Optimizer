# Merge Sort and Quick Sort Trace

Input array: [5, 2, 4, 6, 1, 3]

## Merge Sort Trace

| Step | Operation | Array/Partition |
|---|---|---|
| 1 | Split | [5, 2, 4] and [6, 1, 3] |
| 2 | Split left | [5] and [2, 4] |
| 3 | Split [2, 4] | [2] and [4] |
| 4 | Merge [2] and [4] | [2, 4] |
| 5 | Merge [5] and [2, 4] | [2, 4, 5] |
| 6 | Split right | [6] and [1, 3] |
| 7 | Split [1, 3] | [1] and [3] |
| 8 | Merge [1] and [3] | [1, 3] |
| 9 | Merge [6] and [1, 3] | [1, 3, 6] |
| 10 | Final merge | [1, 2, 3, 4, 5, 6] |

### Merge Sort Result

Sorted array: **[1, 2, 3, 4, 5, 6]**

Merge sort repeatedly divides the array into smaller subarrays and then merges those subarrays in sorted order.

## Quick Sort Trace

Input array: [5, 2, 4, 6, 1, 3]

| Step | Pivot | Operation | Array state |
|---|---:|---|---|
| 1 | 3 | Partition around 3 | [2, 1, 3, 6, 5, 4] |
| 2 | 1 | Partition left section | [1, 2, 3, 6, 5, 4] |
| 3 | 4 | Partition right section | [1, 2, 3, 4, 5, 6] |
| 4 | 6 | Partition remaining section | [1, 2, 3, 4, 5, 6] |

### Quick Sort Result

Sorted array: **[1, 2, 3, 4, 5, 6]**

The Quick Sort implementation uses the last element as the pivot, partitions the array around the pivot, and recursively sorts the elements on either side.
