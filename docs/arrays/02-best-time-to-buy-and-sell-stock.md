# Best Time to Buy and Sell Stock

## Problem

Given an array where prices[i] represents the stock
price on day i, find the maximum profit.

You can buy once and sell once.

Example:

Input:
[7, 1, 5, 3, 6, 4]

Output:
5

Buy at 1 and sell at 6.

Profit = 6 - 1 = 5

---

## Pattern

Running Minimum / One Pass

### Memory Trick

MIN → PROFIT → MAX

---

## Brute Force

Try every possible buying day and selling day.

### Complexity

Time: O(n²)
Space: O(1)

---

## Optimized Approach

Instead of checking every previous price,
keep track of the minimum price seen so far.

For every price:

1. Update minimum price.
2. Calculate current profit.
3. Update maximum profit.

---

## Code

See:

src/main/kotlin/blind75/array/BestTimeToBuyAndSellStock.kt

---

## Dry Run

Input:

[7, 1, 5, 3, 6, 4]

| Price | Min Price | Current Profit | Max Profit |
|------:|----------:|---------------:|-----------:|
| 7     | 7         | 0              | 0          |
| 1     | 1         | 0              | 0          |
| 5     | 1         | 4              | 4          |
| 3     | 1         | 2              | 4          |
| 6     | 1         | 5              | 5          |
| 4     | 1         | 3              | 5          |

Answer: 5

---

## Why Does It Work?

At every position, minPrice represents the
cheapest price seen before or at the current day.

Therefore:

currentProfit = currentPrice - minPrice

maxProfit stores the best profit found so far.

---

## Complexity

Time: O(n)

Space: O(1)

---

## Interview Explanation

The brute-force solution checks every possible
buying and selling combination, giving O(n²).

We can optimize it to O(n) by maintaining the
minimum price seen so far. For every current price,
we calculate the profit if we sell today and keep
the maximum profit.

Only two variables are required, so space is O(1).

---

## Pattern Recognition

When you see:

"Find the maximum result where something must
happen before the current element."

Think:

BEST PREVIOUS VALUE → CURRENT VALUE → BEST RESULT

For this problem:

Minimum price → Current price → Maximum profit

---

## Recall

Without looking at the solution, remember:

MIN → PROFIT → MAX