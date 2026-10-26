# 01. Longest Substring Without Repeating Characters

-
**LeetCode:** [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- **Pattern:** Sliding Window
- **Difficulty:** Medium
- **Class/Program:** `LongestSubstringWithoutRepeatingCharacters.kt`

## Problem

Given a string `s`, find the length of the longest substring without repeating characters.

### Examples

| Input        | Output |
|--------------|-------:|
| `"abcabcbb"` |      3 |
| `"bbbbb"`    |      1 |
| `"pwwkew"`   |      3 |
| `""`         |      0 |
| `"abba"`     |      2 |

## 1. Brute Force

### Approach

- Generate every possible substring.
- Use a `HashSet` to check whether each substring contains duplicate characters.
- If all characters are unique, update the maximum length.

### Code

```kotlin
fun lengthOfLongestSubstringBruteForce(s: String): Int {
    var maxLength = 0

    for (i in s.indices) {
        for (j in i until s.length) {
            val seen = HashSet<Char>()
            var isUnique = true

            for (k in i..j) {
                if (!seen.add(s[k])) {
                    isUnique = false
                    break
                }
            }

            if (isUnique) {
                maxLength = maxOf(maxLength, j - i + 1)
            }
        }
    }

    return maxLength
}
```

### Complexity

- **Time:** `O(n³)`
- **Space:** `O(min(n, k))`

### Why O (n³)?

There are `O(n²)` possible substrings. Checking each substring can take up to `O(n)` time.

## 2. Optimized Approach — Sliding Window

### Approach

- Maintain a window using two pointers: `left` and `right`.
- Use a `HashSet` to track characters in the current window.
- If a duplicate is found, remove characters from the left until the duplicate is gone.
- Update the maximum window length after adding each character.

### Code

```kotlin
fun lengthOfLongestSubstringOptimize(s: String): Int {
    val seen = HashSet<Char>()
    var left = 0
    var maxLength = 0

    for (right in s.indices) {
        while (seen.contains(s[right])) {
            seen.remove(s[left])
            left++
        }

        seen.add(s[right])

        maxLength = maxOf(
            maxLength,
            right - left + 1
        )
    }

    return maxLength
}
```

### Complexity

- **Time:** `O(n)`
- **Space:** `O(min(n, k))`

### Why O (n)?

Each character enters the set once and leaves the set at most once. Therefore, both pointers move forward at most `n`
times.

## 3. Complete Kotlin Program

**File:** `LongestSubstringWithoutRepeatingCharacters.kt`

```kotlin
package blind75.string

fun main() {
    println(lengthOfLongestSubstringBruteForce("abcabcbb")) // 3
    println(lengthOfLongestSubstringOptimize("abcabcbb"))  // 3
}

/**
 * Brute Force
 * Time  : O(n³)
 * Space : O(min(n, k))
 */
fun lengthOfLongestSubstringBruteForce(s: String): Int {
    var maxLength = 0

    for (i in s.indices) {
        for (j in i until s.length) {
            val seen = HashSet<Char>()
            var isUnique = true

            for (k in i..j) {
                if (!seen.add(s[k])) {
                    isUnique = false
                    break
                }
            }

            if (isUnique) {
                maxLength = maxOf(maxLength, j - i + 1)
            }
        }
    }

    return maxLength
}

/**
 * Optimized - Sliding Window
 * Time  : O(n)
 * Space : O(min(n, k))
 */
fun lengthOfLongestSubstringOptimize(s: String): Int {
    val seen = HashSet<Char>()
    var left = 0
    var maxLength = 0

    for (right in s.indices) {
        while (seen.contains(s[right])) {
            seen.remove(s[left])
            left++
        }

        seen.add(s[right])
        maxLength = maxOf(maxLength, right - left + 1)
    }

    return maxLength
}
```

## 4. Test Cases

| Input        | Expected | Brute Force | Optimized |
|--------------|---------:|------------:|----------:|
| `""`         |        0 |           0 |         0 |
| `"abcabcbb"` |        3 |           3 |         3 |
| `"bbbbb"`    |        1 |           1 |         1 |
| `"pwwkew"`   |        3 |           3 |         3 |
| `"abcdef"`   |        6 |           6 |         6 |
| `"abba"`     |        2 |           2 |         2 |

## 5. Dry Run

**Input:** `"abcabcbb"`

| Right | Character | Current Window | Max Length |
|------:|:---------:|----------------|-----------:|
|     0 |     a     | `a`            |          1 |
|     1 |     b     | `ab`           |          2 |
|     2 |     c     | `abc`          |          3 |
|     3 |     a     | `bca`          |          3 |
|     4 |     b     | `cab`          |          3 |
|     5 |     c     | `abc`          |          3 |
|     6 |     b     | `cb`           |          3 |
|     7 |     b     | `b`            |          3 |

**Output:** `3`

## 6. Interview Explanation

"I can solve this using brute force by generating every substring and checking whether its characters are unique, but
that takes O (n³) time.

To optimize it, I use a sliding window with a HashSet. When a duplicate appears, I move the left pointer and remove
characters until the window becomes unique again. This reduces the time complexity to O (n)."

## Key Takeaway

- **Brute force:** Generate and validate all substrings.
- **Optimized:** Sliding Window + HashSet.
- **Main lesson:** Avoid rechecking every substring when you can maintain a valid window.
