# Search in Rotated Sorted Array

## Problem

Given an array sorted in ascending order that has been rotated, find the index of a target element.

Assume all elements are unique.

If the target does not exist, return -1.

Example:

Input:

[4, 5, 6, 7, 0, 1, 2]

Target: 0

Output:

4

The target 0 is located at index 4.

---

## Pattern

Modified Binary Search

### Memory Trick

**FIND SORTED HALF → CHECK TARGET → SHRINK SEARCH**

The array is rotated, so the entire array is not sorted. However, at least one half of the search range is always
sorted.

Use that sorted half to decide where the target can exist.

---

## Brute Force

Traverse the array and compare each element with the target.

If a match is found, return its index.

If no match is found, return -1.

### Complexity

Time: O (n)

Space: O (1)

---

## Optimized Approach

Use Modified Binary Search.

Maintain two pointers:

- left = beginning of the search range
- right = end of the search range

Calculate the middle index and check whether the target is found.

If the middle element equals the target, return the middle index.

Otherwise, determine which half is sorted.

### Case 1: Left Half Is Sorted

Check:

nums[left] <= nums[mid]

If true, the left half is sorted.

Now check whether the target lies between the leftmost element and the middle element.

If the target belongs to the left half, search there.

Otherwise, search the right half.

When searching the right half, move left to mid + 1.

Why mid + 1?

The middle element has already been checked, so we can safely skip it.

### Case 2: Right Half Is Sorted

If the left half is not sorted, the right half must be sorted.

Check whether the target lies between the middle element and the rightmost element.

If the target belongs to the right half, search there.

Otherwise, search the left half.

When searching the left half, move right to mid - 1.

Why mid - 1?

The middle element has already been checked, so we can safely skip it.

---

## Code

See:

src/main/kotlin/blind75/array/SearchInRotatedSortedArray.kt

---

## Dry Run

Input:

[4, 5, 6, 7, 0, 1, 2]

Target: 0

| Left | Right | Mid | Middle Value | Action                                                            |
|-----:|------:|----:|-------------:|-------------------------------------------------------------------|
|    0 |     6 |   3 |            7 | Left half is sorted. Target is not in it, so move left to mid + 1 |
|    4 |     6 |   5 |            1 | Left half is sorted. Target is in it, so move right to mid - 1    |
|    4 |     4 |   4 |            0 | Target found                                                      |

Answer: 4

---

## Why Does It Work?

Although the entire array is rotated, at least one half of the current search range is sorted.

The sorted half provides enough information to determine whether the target can exist inside it.

If the target belongs to that half, discard the other half.

Otherwise, discard the sorted half.

This eliminates approximately half of the remaining search range in each iteration.

Continue until the target is found or the search range becomes empty.

---

## Understanding Pointer Movement

### left = mid + 1

Use this when the target must be on the right side of the middle.

The middle element has already been checked, so start from the next index.

### right = mid - 1

Use this when the target must be on the left side of the middle.

The middle element has already been checked, so stop at the previous index.

### Loop Condition

Continue while:

left <= right

When left becomes greater than right, no elements remain to check.

Return -1.

---

## Complexity

Time: O (log n)

Space: O (1)

Binary search eliminates approximately half of the search range in each iteration.

---

## Interview Explanation

The brute-force approach checks every element and takes O (n) time.

I optimize it using modified binary search.

At each iteration, I calculate the middle index. If the target is not found there, I determine which half is sorted.

I check whether the target lies within that sorted half. If it does, I search that half; otherwise, I search the other
half.

This reduces the search range by approximately half each time, giving O (log n) time and O (1) space.

---

## Pattern Recognition

When you see:

"Search for a target in a rotated sorted array."

Think:

Modified Binary Search

The key question is:

**Which half is sorted, and can the target exist in that half?**

---

## Common Mistakes

### 1. Searching without identifying the sorted half

The entire array is not necessarily sorted after rotation.

Always identify which half is sorted before deciding where to search.

### 2. Using left = mid instead of left = mid + 1

The middle element has already been checked.

Use mid + 1 to avoid getting stuck.

### 3. Using right = mid instead of right = mid - 1

The middle element has already been checked.

Use mid - 1 when searching the left side.

### 4. Using the wrong loop condition

Use left <= right so the final remaining element is checked.

### 5. Forgetting the missing-target case

If the target does not exist, return -1.

---

## Recall

Without looking at the solution, remember:

1. Calculate mid.
2. Check whether nums[mid] equals target.
3. Identify the sorted half.
4. Check whether the target belongs to that half.
5. Eliminate the other half.
6. Repeat until found or the search range is empty.

### Final Memory Trick

**SORTED HALF → TARGET RANGE → ELIMINATE HALF**

Remember:

- Search right: left = mid + 1
- Search left: right = mid - 1

The goal is to eliminate half of the search range in every iteration.