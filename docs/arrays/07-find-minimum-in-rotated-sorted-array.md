# Find Minimum in Rotated Sorted Array

## Problem

Given a sorted array that has been rotated, find the minimum element.

Example:

Input:

[3, 4, 5, 1, 2]

Output:

1

The original sorted array was:

[1, 2, 3, 4, 5]

After rotation:

[3, 4, 5, 1, 2]

---

## Pattern

Binary Search

### Memory Trick

**CHECK MID → CHOOSE SIDE → SHRINK SEARCH**

Instead of checking every element, use the sorted structure to eliminate half of the search range in each step.

---

## Brute Force

Traverse the entire array and keep track of the smallest element.

### Complexity

Time: O (n)

Space: O (1)

---

## Optimized Approach

Use Binary Search.

Maintain two pointers:

- `left` = beginning of the search range
- `right` = end of the search range

Calculate the middle index and compare the middle element with the rightmost element.

### Case 1: Middle element > Rightmost element

The minimum is in the right half.

Move:

`left = mid + 1`

Example:

[3, 4, 5, 1, 2]

Middle element = 5

Rightmost element = 2

Since 5 > 2, the minimum is to the right of the middle.

### Case 2: Middle element <= Rightmost element

The minimum is at the middle or in the left half.

Move:

`right = mid`

Why not `mid - 1`?

Because the middle element itself could be the minimum.

Continue until `left == right`.

That position contains the minimum element.

---

## Code

See:

`src/main/kotlin/blind75/array/FindMinimumInRotatedSortedArray.kt`

---

## Dry Run

Input:

[3, 4, 5, 1, 2]

| Left | Right | Mid | nums[mid] | nums[right] | Action               |
|-----:|------:|----:|----------:|------------:|----------------------|
|    0 |     4 |   2 |         5 |           2 | Move left to mid + 1 |
|    3 |     4 |   3 |         1 |           2 | Move right to mid    |
|    3 |     3 |   - |         - |           - | Search complete      |

Answer: 1

---

## Why Does It Work?

The array was originally sorted but then rotated.

The rotation creates a point where the order wraps around, and the minimum is at that point.

Comparing the middle element with the rightmost element tells us which side contains the minimum.

- If the middle element is greater, the minimum must be on the right.
- Otherwise, the minimum is at the middle or on the left.

Each step removes half of the search range.

When both pointers meet, the minimum has been found.

---

## Complexity

Time: O (log n)

Space: O (1)

Binary search eliminates approximately half the remaining elements in every iteration.

---

## Interview Explanation

The brute-force solution scans the entire array and takes O (n) time.

I optimize it using binary search. I compare the middle element with the rightmost element to decide which half contains
the minimum.

If the middle element is greater, I search the right half. Otherwise, I keep the middle element as a possible answer and
search the left half.

When both pointers meet, that element is the minimum.

The optimized solution takes O (log n) time and O (1) space.

---

## Pattern Recognition

When you see:

"Find the minimum in a rotated sorted array."

Think:

**Binary Search**

Look for an ordering property that helps you eliminate half of the array.

---

## Common Mistakes

### 1. Moving right to mid - 1

The middle element could itself be the minimum.

Use `right = mid`.

### 2. Using left < right incorrectly

When `left == right`, the search is complete.

Return the element at that index.

### 3. Using a linear scan

A linear scan works, but it takes O (n) time. The optimized solution should use binary search.

### 4. Forgetting the single-element case

For an array containing only one element, both pointers start at the same index, and that element is the answer.

---

## Recall

Without looking at the solution, remember:

**MID > RIGHT → SEARCH RIGHT**

**MID <= RIGHT → KEEP MID, SEARCH LEFT**

Continue until:

**LEFT == RIGHT**

### Final Memory Trick

**ROTATED SORTED ARRAY → BINARY SEARCH → FIND THE ROTATION POINT**