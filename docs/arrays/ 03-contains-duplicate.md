# Contains Duplicate

## Problem

Given an integer array `nums`, determine whether
any value appears at least twice.

Return `true` if a duplicate exists.

Otherwise, return `false`.

Example:

Input:
[1, 2, 3, 1]

Output:
true

Because `1` appears twice.

---

## Pattern

HashSet

### Memory Trick

SEE → CHECK → ADD

---

## Brute Force

Compare every element with every other element.

For each element, check whether the same value
appears later in the array.

If two values are equal, return `true`.

If no duplicate is found after checking all pairs,
return `false`.

### Complexity

Time: O(n²)
Space: O(1)

---

## Optimized Approach

Instead of comparing every pair, use a HashSet
to keep track of values we have already seen.

For every number:

1. Check whether the number already exists in the HashSet.
2. If it exists, a duplicate is found.
3. If it does not exist, add it to the HashSet.
4. Continue to the next number.

If we finish the array without finding a duplicate,
return `false`.

---

## Code

See:

src/main/kotlin/blind75/array/ContainsDuplicate.kt

---

## Dry Run

Input:

[1, 2, 3, 1]

| Number | Seen Before? | HashSet |
|-------:|:------------:|:--------|
| 1      | No           | {1} |
| 2      | No           | {1, 2} |
| 3      | No           | {1, 2, 3} |
| 1      | Yes          | {1, 2, 3} |

When we reach the second `1`, it is already
present in the HashSet.

Therefore:

Answer: true

---

## Why Does It Work?

The HashSet stores every value that we have
already encountered.

When we process a new number, there are only
two possibilities:

### Number is not in the HashSet

This is the first time we have seen it.

Add it to the HashSet.

### Number is already in the HashSet

We have seen the same value before.

Therefore, a duplicate exists.

Return `true`.

This lets us detect duplicates in a single pass.

---

## Complexity

Time: O(n) average

Space: O(n)

### Why O(n)?

We process each element only once.

HashSet lookup and insertion take O(1) average time.

Therefore:

n × O(1) = O(n)

### Why O(n) Space?

In the worst case, all values are unique.

The HashSet then needs to store all `n` values.

---

## HashSet vs HashMap

This is an important interview concept.

### HashSet

Use HashSet when you only need to know:

"Have I seen this value?"

Example:

Contains Duplicate

### HashMap

Use HashMap when you need additional information
about the value.

For example:

Value → Index

Value → Frequency

Two Sum uses a HashMap because we need to store
the index along with the number.

### Memory Trick

HashSet → Existence

HashMap → Information

---

## Kotlin Optimization

There are two ways to use the HashSet.

### contains() + add()

First check whether the value exists.

If it exists → duplicate.

Otherwise → add it.

### add() directly

HashSet `add()` returns:

true → value was newly added

false → value already exists

Therefore, if `add()` returns `false`,
we know that a duplicate exists.

This is a useful Kotlin-specific shortcut.

---

## Edge Cases

### Empty Array

Input:

[]

Answer:

false

There are no elements to duplicate.

### Single Element

Input:

[5]

Answer:

false

One element cannot be duplicated.

### All Unique

Input:

[1, 2, 3, 4]

Answer:

false

### Duplicate

Input:

[1, 2, 3, 1]

Answer:

true

### All Same

Input:

[5, 5, 5, 5]

Answer:

true

---

## Alternative Approach

Another approach is to sort the array.

After sorting, duplicate values become adjacent.

Example:

Before:

[3, 1, 2, 1]

After sorting:

[1, 1, 2, 3]

Now compare adjacent elements.

If two adjacent values are equal,
a duplicate exists.

### Complexity

Time: O(n log n)

Space: O(1) extra space
(depending on the sorting implementation)

However, sorting modifies the input array.

The HashSet approach is preferred when we want
O(n) average time without modifying the input.

---

## Interview Explanation

The brute-force solution compares every pair of
elements, which takes O(n²) time.

We can optimize this using a HashSet.

While iterating through the array, I keep track
of all values that I have already seen.

For every number, if it already exists in the
HashSet, I know that a duplicate exists and
return `true`.

Otherwise, I add it to the HashSet and continue.

This gives O(n) average time and O(n) space.

---

## Pattern Recognition

When you see:

"Does a duplicate exist?"

"Have I seen this value before?"

"Are all elements unique?"

"Check whether an element already appeared."

Think:

HASHSET

### General Pattern

CURRENT VALUE

↓

Have I seen it before?

↓

YES → Duplicate

NO → Store it

---

## Recall

Without looking at the solution, remember:

### Pattern

HashSet

### Process

SEE → CHECK → ADD

### Complexity

Brute Force:

O(n²) time  
O(1) space

Optimized:

O(n) average time  
O(n) space

### Key Difference

HashSet → Need existence

HashMap → Need additional information