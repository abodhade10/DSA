# Container With Most Water

## Problem

Given an integer array `height`, each element represents the height of a vertical line.

Choose two lines that, together with the x-axis, form a container that holds the maximum amount of water.

Return the maximum water area.

**Example**

Input: `height = [1,8,6,2,5,4,8,3,7]`

Output: `49`

## Pattern

- Brute Force: Nested Loops
- Optimized: Two Pointers

## Memory Trick

`AREA → UPDATE MAX → MOVE SHORTER LINE`

Remember:

- Width = `right - left`
- Water height = `min(height[left], height[right])`
- Area = `width × water height`

---

## Approach 1: Brute Force

### Idea

Check every possible pair of lines and calculate the water area between them.

### Steps

1. Select the first line using the outer loop.
2. Select every line after it using the inner loop.
3. Calculate the width between the two lines.
4. Find the smaller of the two heights.
5. Calculate the area.
6. Update the maximum area if the current area is larger.

### Dry Run

Input: `[1,8,6,2,5,4,8,3,7]`

| Left index | Right index | Width | Minimum height | Area |
|-----------:|------------:|------:|---------------:|-----:|
|          0 |           1 |     1 |              1 |    1 |
|          0 |           2 |     2 |              1 |    2 |
|          1 |           2 |     1 |              6 |    6 |
|          1 |           8 |     7 |              7 |   49 |

The algorithm checks all possible pairs. The maximum area found is `49`.

### Complexity

- Time: O (n²)
- Auxiliary space: O (1)

### Drawback

Checking every possible pair becomes slow for large arrays.

---

## Approach 2: Optimized — Two Pointers

### Idea

Start with the widest possible container by placing one pointer at each end of the array.

Calculate the area, update the maximum, and move the pointer pointing to the shorter line.

### Steps

1. Initialize `left = 0`.
2. Initialize `right = last index`.
3. Calculate the width: `right - left`.
4. Calculate the water height using the smaller line.
5. Calculate the area and update the maximum.
6. If the left line is shorter, move `left` one position right.
7. Otherwise, move `right` one position left.
8. Repeat until `left` is no longer less than `right`.

### Dry Run

Input: `[1,8,6,2,5,4,8,3,7]`

| Left index | Right index | Heights | Area | Maximum |
|-----------:|------------:|---------|-----:|--------:|
|          0 |           8 | 1, 7    |    8 |       8 |
|          1 |           8 | 8, 7    |   49 |      49 |
|          1 |           7 | 8, 3    |   18 |      49 |
|          1 |           6 | 8, 8    |   40 |      49 |

The process continues by moving the shorter line at each step. No later pair exceeds `49`.

Final answer: `49`

### Why Do We Move the Shorter Line?

The shorter line limits the amount of water the container can hold.

Moving the taller line inward reduces the width but cannot increase the limiting height beyond the shorter line already
present. Therefore, moving the taller line cannot improve the area.

Moving the shorter line gives us a chance to find a taller line. This may compensate for the reduced width.

### Why Does It Work?

The initial pointers provide the maximum possible width. Whenever we move the shorter line, we eliminate pairs that
cannot improve the area with that shorter line. This allows us to find the maximum without checking every pair.

### Complexity

- Time: O (n), because each pointer moves only inward.
- Auxiliary space: O (1), because only a few variables are used.

---

## Comparison

| Feature         | Brute Force         | Optimized                    |
|-----------------|---------------------|------------------------------|
| Technique       | Nested loops        | Two pointers                 |
| Time            | O(n²)               | O(n)                         |
| Auxiliary space | O(1)                | O(1)                         |
| Checks          | Every pair          | Efficiently eliminates pairs |
| Recommended     | Basic understanding | Interviews                   |

## Interview Explanation

“I would first consider brute force by checking every pair of lines and calculating the area, which takes O (n²) time.

To optimize it, I use two pointers, one at each end. I calculate the area using the shorter height and the distance
between the pointers. I update the maximum area and move the pointer pointing to the shorter line. This works in O (n)
time and O (1) auxiliary space.”

## Common Mistakes

- Using the taller height instead of the shorter height.
- Calculating width incorrectly; it is `right - left`.
- Forgetting to update the maximum area.
- Moving the taller pointer instead of the shorter one.
- Using `left <= right` instead of `left < right`.

## Recall

1. Start pointers at both ends.
2. Calculate width.
3. Use the shorter height.
4. Calculate area.
5. Update maximum.
6. Move the shorter pointer.
7. Stop when the pointers meet.

**One-line memory trick:**

`AREA → UPDATE MAX → MOVE SHORTER LINE`

## Code Reference

- Kotlin: `src/main/kotlin/blind75/array/ContainerWithMostWater.kt`
- Markdown: `docs/arrays/10-container-with-most-water.md`