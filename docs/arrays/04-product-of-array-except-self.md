# Product of Array Except Self

## Problem

Given an integer array `nums`, return an array where
each element is the product of all the elements
except itself.

You cannot use division.

Example:

Input:
[1, 2, 3, 4]

Output:
[24, 12, 8, 6]

Explanation:

For 1:
2 × 3 × 4 = 24

For 2:
1 × 3 × 4 = 12

For 3:
1 × 2 × 4 = 8

For 4:
1 × 2 × 3 = 6

---

## Pattern

Prefix Product + Suffix Product

### Memory Trick

LEFT → RIGHT → LEFT × RIGHT

Or:

PREFIX → SUFFIX

---

## Brute Force

For every element, calculate the product of all
other elements.

For example:

[1, 2, 3, 4]

For 1:
2 × 3 × 4 = 24

For 2:
1 × 3 × 4 = 12

For 3:
1 × 2 × 4 = 8

For 4:
1 × 2 × 3 = 6

### Complexity

Time: O (n²)
Space: O (1)

### Why O (n²)?

For every element, we scan the array again to
calculate the product of all other elements.

---

## Optimized Approach

Instead of calculating the product again and again,
split the problem into two parts:

LEFT × RIGHT

For every element:

Product Except Self =
Product of everything on the LEFT
×
Product of everything on the RIGHT

Example:

[1, 2, 3, 4]

For 3:

[1, 2] [3] [4]

Left product:
1 × 2 = 2

Right product:
4

Answer:
2 × 4 = 8

---

## Code

See:

src/main/kotlin/blind75/array/ProductOfArrayExceptSelf.kt

---

## First Pass — Left Product

Traverse from left to right.

Store the product of all elements
before the current index.

Input:

[1, 2, 3, 4]

Left products:

[1, 1, 2, 6]

Explanation:

Before 1:
Nothing → 1

Before 2:
1 → 1

Before 3:
1 × 2 → 2

Before 4:
1 × 2 × 3 → 6

---

## Second Pass — Right Product

Now traverse from right to left.

Calculate the product of all elements
after the current index.

For:

[1, 2, 3, 4]

Right products:

[24, 12, 4, 1]

Explanation:

After 1:
2 × 3 × 4 = 24

After 2:
3 × 4 = 12

After 3:
4 = 4

After 4:
Nothing → 1

---

## Dry Run

Input:

[1, 2, 3, 4]

### First Pass

LEFT products:

[1, 1, 2, 6]

### Second Pass

Multiply each left product by
the corresponding right product.

| Index | Value | Left Product | Right Product | Result |
|------:|------:|-------------:|--------------:|-------:|
|     0 |     1 |            1 |            24 |     24 |
|     1 |     2 |            1 |            12 |     12 |
|     2 |     3 |            2 |             4 |      8 |
|     3 |     4 |            6 |             1 |      6 |

Answer:

[24, 12, 8, 6]

---

## Why Does It Work?

For every element, we need the product
of everything except itself.

We can divide the array conceptually into:

LEFT | CURRENT | RIGHT

We don't need CURRENT.

So:

LEFT × RIGHT

The first pass calculates the LEFT product.

The second pass calculates the RIGHT product.

Combining them gives the product of every element
except the current element.

---

## Why Don't We Use Division?

A simple idea would be:

Total Product / Current Element

But the problem does not allow division.

Also, division does not work properly when
the array contains zero.

Example:

[1, 2, 0, 4]

The total product becomes 0.

The prefix + suffix approach handles zero
without any special logic.

---

## Handling Zero

Input:

[1, 2, 0, 4]

Output:

[0, 0, 8, 0]

For 0:

1 × 2 × 4 = 8

For every other element, the product includes 0.

Therefore:

[0, 0, 8, 0]

---

## Complexity

Time: O (n)

Space: O (1) extra space

### Why O (n)?

We traverse the array twice:

First pass:
LEFT → RIGHT

Second pass:
RIGHT → LEFT

Therefore:

O (n) + O (n) = O (n)

### Why O (1) Space?

We only use a few variables for the
running products.

The output array is required by the problem,
so it is not counted as extra space.

---

## Interview Explanation

The brute-force approach calculates the product
of all other elements for every index, which takes
O (n²) time.

We can optimize this using prefix and suffix products.

In the first pass, I calculate the product of all
elements to the left of each index.

Then I traverse from right to left and maintain the
product of all elements to the right.

I multiply the left product with the right product
to get the final answer.

This gives O (n) time and O (1) extra space,
excluding the output array.

---

## Pattern Recognition

When you see:

"Product of all elements except itself"

Think:

PREFIX + SUFFIX

Break the problem into:

LEFT | CURRENT | RIGHT

Ignore CURRENT.

Calculate:

LEFT × RIGHT

### General Pattern

LEFT → RIGHT

Then:

RIGHT → LEFT

---

## Common Mistakes

### Mistake 1 — Using Division

The problem specifically says not to use division.

Use prefix + suffix.

### Mistake 2 — Including the Current Element

The current element must be excluded.

Always think:

LEFT × RIGHT

### Mistake 3 — Thinking Prefix Means Sum

Prefix does not always mean sum.

Here:

Prefix = product of elements on the left.

### Mistake 4 — Forgetting the Empty Side

For the first element, there is nothing on the left.

Use:

1

For the last element, there is nothing on the right.

Use:

1

---

## Recall

Without looking at the solution, remember:

### Pattern

Prefix + Suffix

### Formula

LEFT PRODUCT × RIGHT PRODUCT

### Process

First Pass:
LEFT → RIGHT

Second Pass:
RIGHT → LEFT

### Complexity

Time: O (n)

Space: O (1) extra space

### Memory Trick

EXCEPT SELF = LEFT × RIGHT

PREFIX → SUFFIX

```