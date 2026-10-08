# Two Sum

## Problem

Given an integer array `nums` and an integer `target`,
find two numbers whose sum equals the target.

Return the **indices** of those two numbers.

Each input has exactly one solution.

Example:

Input:

`nums = [2, 7, 11, 15]`

`target = 9`

Output:

`[0, 1]`

Because:

`nums[0] + nums[1] = 2 + 7 = 9`

---

## Pattern

HashMap / Complement Lookup

### Memory Trick

**TARGET → CURRENT → REQUIRED → CHECK → STORE**

Or simply:

> **Complement + HashMap**

---

## Brute Force

Try every possible pair of elements.

For every element, check the elements after it.

If the sum of the two elements equals the target,
return their indices.

### Complexity

Time: O(n²)

Space: O(1)

### Why O(n²)?

For every element, we potentially check every other
element.

Therefore, nested loops result in O(n²) time.

---

## Optimized Approach

Instead of checking every pair, use a HashMap.

The HashMap stores:

**number → index**

For every current number, calculate the number
required to reach the target.

### Formula

**required = target - current**

Then check whether `required` already exists
in the HashMap.

If it exists:

- We found the required pair.
- Return the stored index and current index.

If it does not exist:

- Store the current number and its index.
- Continue to the next element.

---

## Code

See:

`src/main/kotlin/blind75/array/TwoSum.kt`

---

## Dry Run

Input:

`nums = [2, 7, 11, 15]`

`target = 9`

Initially:

`map = {}`

### Step 1

Current:

`2`

Required:

`9 - 2 = 7`

Check map:

`7` does not exist.

Store:

`2 → 0`

Map:

`{2 → 0}`

---

### Step 2

Current:

`7`

Required:

`9 - 7 = 2`

Check map:

`2` exists.

Stored index:

`0`

Current index:

`1`

Therefore:

`[0, 1]`

Answer:

`[0, 1]`

---

## Why Does It Work?

For every current number, instead of searching
through the entire remaining array, we calculate
exactly what number is needed.

For example:

Current = `7`

Target = `9`

Required:

`9 - 7 = 2`

If `2` was already seen, we immediately know that:

`2 + 7 = 9`

The HashMap lets us find that number in
O(1) average time.

---

## Why Do We Store the Index?

The problem asks for the **indices**, not just
the values.

Therefore, the HashMap stores:

**number → index**

Example:

`2 → 0`

`7 → 1`

This allows us to return the required indices
when the complement is found.

---

## Important Point: Check Before Store

For every element:

**Calculate → Check → Store**

We check for the complement **before** storing
the current number.

This also correctly handles duplicate values.

Example:

`nums = [3, 3]`

`target = 6`

First `3`:

- Required = `3`
- Not found
- Store `3 → 0`

Second `3`:

- Required = `3`
- Found at index `0`
- Return `[0, 1]`

---

## Complexity

Time: O(n) average

Space: O(n)

### Why O(n)?

We traverse the array only once.

For each element:

- Calculate the required value → O(1)
- HashMap lookup → O(1) average
- HashMap insertion → O(1) average

Therefore:

**O(n)**

### Why O(n) Space?

In the worst case, we may store almost every
element in the HashMap.

---

## HashMap vs HashSet

This is an important distinction.

### HashSet

Use HashSet when you only need to know:

> "Have I seen this value?"

Example:

**Contains Duplicate**

### HashMap

Use HashMap when you need:

> "Have I seen this value, and where/in what form?"

For Two Sum, we need:

**number → index**

Therefore, we use a HashMap.

### Memory Trick

> **Existence → HashSet**

> **Value + Information → HashMap**

---

## Edge Cases

### Solution at the Beginning

`[2, 7, 11, 15]`

Target:

`9`

Answer:

`[0, 1]`

---

### Solution at the End

`[1, 2, 3, 7]`

Target:

`10`

The pair is:

`3 + 7 = 10`

Answer:

`[2, 3]`

---

### Duplicate Values

`[3, 3]`

Target:

`6`

Answer:

`[0, 1]`

---

### Negative Numbers

`[-3, 4, 2, 5]`

Target:

`-1`

Pair:

`-3 + 2 = -1`

Answer:

`[0, 2]`

The HashMap approach works with negative numbers
as well.

---

## Common Mistakes

### Mistake 1 — Using Two Loops

This works but gives O(n²).

The HashMap solution reduces this to O(n) average.

### Mistake 2 — Storing Only the Number

We need the index as well.

Store:

**number → index**

Not just the number.

### Mistake 3 — Checking the Current Number Against Itself

The complement should be checked against
previously seen elements.

This is why we:

**CHECK → STORE**

---

## Interview Explanation

The brute-force approach checks every possible pair,
which takes O(n²) time.

We can optimize this using a HashMap.

While iterating through the array, I calculate the
required value using `target - current`.

Then I check whether that required value has already
been seen in the HashMap.

If it exists, I return its stored index along with
the current index.

Otherwise, I store the current number and its index
and continue.

This gives O(n) average time and O(n) space.

---

## Pattern Recognition

When you see:

> "Find two numbers that add up to a target."

Think:

**COMPLEMENT + HASHMAP**

General pattern:

**TARGET - CURRENT = REQUIRED**

Then:

**CHECK → STORE**

---

## Recall

Without looking at the solution, remember:

### Pattern

**HashMap + Complement**

### Formula

**Required = Target - Current**

### Process

**CALCULATE → CHECK → STORE**

### HashMap

**Number → Index**

### Complexity

Brute Force:

O(n²) time  
O(1) space

Optimized:

O(n) average time  
O(n) space

### Final Memory Trick

> **Two Sum = Complement + HashMap**

> **Target → Current → Required → Check → Store**