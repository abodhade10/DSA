# Maximum Subarray

## Problem

Given an integer array, find the contiguous subarray
with the largest sum.

Example:

Input:

[-2, 1, -3, 4, -1, 2, 1, -5, 4]

Output:

6

The subarray is:

[4, -1, 2, 1]

Sum = 6

---

## Pattern

Kadane's Algorithm

### Memory Trick

ADD → UPDATE MAX → IF NEGATIVE → RESET

The main idea:

Keep a running sum.

If the running sum becomes negative,
discard it and start a new subarray.

---

## Brute Force

Try every possible starting position and
calculate the sum of every subarray starting there.

Instead of calculating each subarray sum from scratch,
keep a running sum while extending the subarray.

### Complexity

Time: O (n²)

Space: O (1)

---

## Optimized Approach

Use Kadane's Algorithm.

Maintain two values:

- currentSum = sum of the current subarray
- maxSum = maximum sum found so far

For every number:

1. Add the number to currentSum.
2. Update maxSum.
3. If currentSum becomes negative, reset it to 0.

Why reset?

A negative sum will only reduce the sum of any
future subarray.

So it is better to discard that part and start fresh.

---

## Code

See:

src/main/kotlin/blind75/array/MaximumSubarray.kt

---

## Dry Run

Input:

[-2, 1, -3, 4, -1, 2, 1, -5, 4]

| Number | Current Sum | Max Sum |
|-------:|------------:|--------:|
|     -2 |           0 |      -2 |
|      1 |           1 |       1 |
|     -3 |           0 |       1 |
|      4 |           4 |       4 |
|     -1 |           3 |       4 |
|      2 |           5 |       5 |
|      1 |           6 |       6 |
|     -5 |           1 |       6 |
|      4 |           5 |       6 |

Answer: 6

The maximum subarray is:

[4, -1, 2, 1]

---

## Why Does It Work?

Suppose the current subarray has a negative sum.

For example:

[4, -1, -5]

The running sum becomes:

4 → 3 → -2

If we continue with another number, the negative
sum will reduce the result.

Therefore, when currentSum becomes negative,
we reset it to 0.

This means:

Keep the useful part → discard the harmful part.

At every position:

- currentSum represents the best sum of a subarray
  ending at the current position.
- maxSum represents the best subarray sum found anywhere.

---

## Important Edge Case

### All Numbers Are Negative

Example:

[-5, -2, -8]

Answer:

-2

This is why maxSum should start with:

Int.MIN_VALUE

If maxSum started with 0, the algorithm would
incorrectly return 0.

The answer must contain at least one element.

---

## Complexity

Time: O (n)

Space: O (1)

We only scan the array once and use two variables.

---

## Interview Explanation

The brute-force approach checks every possible
subarray and keeps its running sum, which takes O (n²).

We can optimize this using Kadane's Algorithm.

I maintain a current sum and a maximum sum.
For every element, I add it to the current sum
and update the maximum.

If the current sum becomes negative, I reset it to
zero because a negative prefix can only reduce the
sum of any future subarray.

This gives O (n) time and O (1) space.

---

## Pattern Recognition

When you see:

"Find the maximum sum of a contiguous subarray."

Think:

Kadane's Algorithm

The key question is:

Should I continue the current subarray or
start a new one?

If the current sum is negative:

RESET

Otherwise:

CONTINUE

---

## Common Mistakes

### 1. Returning 0 for an all-negative array

Wrong:

[-5, -2, -8] → 0

Correct:

[-5, -2, -8] → -2

---

### 2. Forgetting that the subarray must be contiguous

You cannot skip elements.

For example:

[4, -1, 2, 1]

is valid.

Taking 4, 2, and 1 while skipping -1 is not
the same subarray.

---

### 3. Resetting before updating maxSum

Always update maxSum before resetting currentSum.

Otherwise, an important negative value could be lost
in an all-negative case.

---

## Recall

Without looking at the solution, remember:

CURRENT SUM

→ ADD NUMBER

→ UPDATE MAX

→ IF NEGATIVE

→ RESET

### Final Memory Trick

NEGATIVE CURRENT SUM = START FRESH

Kadane = KEEP USEFUL SUM → DISCARD NEGATIVE SUM