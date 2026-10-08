# Maximum Product Subarray

## Problem

Given an integer array, find the contiguous subarray
with the largest product.

Example:

Input:

[2, 3, -2, 4]

Output:

6

The subarray is:

[2, 3]

Product = 2 × 3 = 6

---

## Pattern

Dynamic Programming / Track Maximum + Minimum

### Memory Trick

MAX + MIN → MULTIPLY → UPDATE

The important idea is:

With multiplication, we must track both the
maximum and minimum product.

Why?

Because:

negative × negative = positive

A minimum negative product can become the maximum
when multiplied by another negative number.

---

## Brute Force

Try every possible starting position and calculate
the product of every contiguous subarray.

Keep a running product while extending the subarray.

### Complexity

Time: O (n²)

Space: O (1)

---

## Optimized Approach

Maintain two values:

- currentMax = maximum product ending at current position
- currentMin = minimum product ending at current position

For every number, there are three possibilities:

1. Start a new subarray with the current number.
2. Extend the previous maximum product.
3. Extend the previous minimum product.

Therefore, the new maximum is based on:

- current number
- currentMax × current number
- currentMin × current number

And the new minimum is calculated from the same
three possibilities.

---

## Why Do We Need currentMin?

This is the most important part of the problem.

Suppose:

currentMax = 5

currentMin = -10

Current number = -2

Then:

5 × -2 = -10

-10 × -2 = 20

The previous minimum becomes the new maximum.

So:

MIN × NEGATIVE → MAX

This is why we cannot track only the maximum.

---

## Negative Number

When the current number is negative,
the previous maximum and minimum effectively
switch roles.

Example:

currentMax = 6
currentMin = -12

Current number = -2

Products:

6 × -2 = -12

-12 × -2 = 24

The minimum product becomes the maximum.

Therefore, when the number is negative,
we swap currentMax and currentMin before
calculating the new values.

---

## Step-by-Step Example

Input:

[2, 3, -2, 4]

### Start

currentMax = 2

currentMin = 2

maxProduct = 2

---

### Number = 3

Possible products:

3

2 × 3 = 6

Therefore:

currentMax = 6

currentMin = 3

maxProduct = 6

---

### Number = -2

The number is negative.

The maximum and minimum can switch roles.

After considering the possible products:

-2

6 × -2 = -12

3 × -2 = -6

Therefore:

currentMax = -2

currentMin = -12

maxProduct = 6

---

### Number = 4

Possible products:

4

-2 × 4 = -8

-12 × 4 = -48

Therefore:

currentMax = 4

currentMin = -48

maxProduct = 6

---

Final Answer:

6

---

## Dry Run

Input:

[2, 3, -2, 4]

| Number | Current Max | Current Min | Max Product |
|-------:|------------:|------------:|------------:|
|      2 |           2 |           2 |           2 |
|      3 |           6 |           3 |           6 |
|     -2 |          -2 |         -12 |           6 |
|      4 |           4 |         -48 |           6 |

Answer: 6

---

## Why Does It Work?

At every position, we need to know the maximum
and minimum product of a subarray ending at
that position.

The maximum is needed because it may continue
to produce a larger positive product.

The minimum is needed because it may become the
maximum after multiplying by a negative number.

For every number:

NUMBER

→ EXTEND MAX

→ EXTEND MIN

→ OR START NEW

Then we keep the best maximum found so far.

---

## Maximum Product vs Maximum Subarray

Maximum Subarray uses addition.

For addition:

Negative current sum is harmful.

Therefore:

CURRENT SUM < 0 → RESET

Maximum Product Subarray uses multiplication.

For multiplication:

Negative product is not always harmful.

Because:

NEGATIVE × NEGATIVE = POSITIVE

Therefore:

PRODUCT → TRACK MAX + MIN

---

## Complexity

Time: O (n)

Space: O (1)

We scan the array only once and use a constant
amount of extra space.

---

## Important Edge Cases

### 1. All Negative Numbers

Input:

[-2, -3, -4]

The best subarray is:

[-2, -3]

Product = 6

Answer = 6

---

### 2. Zero

Input:

[-2, 0, -1]

Zero breaks the current product chain.

Answer = 0

---

### 3. Single Element

Input:

[-5]

Answer = -5

Do not initialize the answer with 0.

Initialize it using the first element so that
negative answers are handled correctly.

---

## Interview Explanation

The brute-force approach checks every contiguous
subarray and calculates its product, which takes O (n²).

To optimize it, I maintain the maximum and minimum
product ending at the current index.

I need both because a negative number can turn the
minimum product into the maximum.

For every number, I consider starting a new subarray,
extending the current maximum, or extending the current
minimum.

If the current number is negative, maximum and minimum
can switch roles.

This gives O (n) time and O (1) space.

---

## Pattern Recognition

When you see:

"Find the maximum product of a contiguous subarray."

Think:

TRACK MAX + MIN

Because:

Negative × Negative = Positive

### Key Question

Can a negative value become useful later?

For product problems:

YES.

Therefore, keep both maximum and minimum.

---

## Common Mistakes

### 1. Tracking only the maximum

This fails because:

MIN × NEGATIVE = MAX

---

### 2. Resetting negative products

Do not reset a negative product like Kadane's algorithm.

A negative product may become positive later.

---

### 3. Ignoring zero

Zero can break the product chain and can itself
be the answer.

---

### 4. Initializing answer with 0

For:

[-5]

The answer is -5, not 0.

Initialize using the first element.

---

## Recall

Without looking at the solution, remember:

NUMBER

→ CHECK NEGATIVE

→ SWAP MAX/MIN

→ CALCULATE NEW MAX

→ CALCULATE NEW MIN

→ UPDATE ANSWER

### Final Memory Trick

MAXIMUM PRODUCT

=

MAX + MIN

Because:

NEGATIVE × NEGATIVE = POSITIVE

### One-Line Recall

"Track both max and min because a negative number
can turn the minimum product into the maximum."

```