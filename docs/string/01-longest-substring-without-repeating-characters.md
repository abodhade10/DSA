# LeetCode 3: Longest Substring Without Repeating Characters

- **Problem:** [LeetCode 3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- **Pattern:** Sliding Window + HashSet
- **Difficulty:** Medium
- **Class Name:** `LongestSubstringWithoutRepeatingCharacters`
- **Code File:** `LongestSubstringWithoutRepeatingCharacters.kt`

## 1. Problem Statement

Given a string `s`, find the length of the longest substring without repeating characters.

A substring is a contiguous sequence of characters.

### Examples

| Input        | Output | Explanation               |
|--------------|-------:|---------------------------|
| `"abcabcbb"` |      3 | `"abc"`                   |
| `"bbbbb"`    |      1 | `"b"`                     |
| `"pwwkew"`   |      3 | `"wke"`                   |
| `""`         |      0 | Empty string              |
| `"abcdef"`   |      6 | All characters are unique |
| `"abba"`     |      2 | `"ab"` or `"ba"`          |

## 2. Pattern: Sliding Window

Use two pointers, `left` and `right`, to maintain a window containing unique characters.

1. Move the `right` pointer through the string.
2. Use a `HashSet` to track characters in the current window.
3. If a duplicate appears, remove characters from the left until the duplicate is removed.
4. Add the current character to the set.
5. Update the maximum window length.

## 3. Kotlin Solution

**File:** `LongestSubstringWithoutRepeatingCharacters.kt`

```kotlin
class LongestSubstringWithoutRepeatingCharacters {

    fun lengthOfLongestSubstring(s: String): Int {
        val seen = mutableSetOf<Char>()
        var left = 0
        var maxLength = 0

        for (right in s.indices) {

            // Remove characters until the duplicate is gone
            while (s[right] in seen) {
                seen.remove(s[left])
                left++
            }

            // Add the current character
            seen.add(s[right])

            // Calculate the current window length
            val currentLength = right - left + 1

            // Update the maximum length
            maxLength = maxOf(maxLength, currentLength)
        }

        return maxLength
    }
}

fun main() {
    val solution = LongestSubstringWithoutRepeatingCharacters()

    val testCases = listOf(
        "" to 0,
        "abcabcbb" to 3,
        "bbbbb" to 1,
        "pwwkew" to 3,
        "abcdef" to 6,
        "abba" to 2
    )

    for ((input, expected) in testCases) {
        val actual = solution.lengthOfLongestSubstring(input)

        println("Input: \"$input\"")
        println("Expected: $expected")
        println("Actual: $actual")
        println(
            "Test Result: ${
                if (actual == expected) "PASS" else "FAIL"
            }"
        )
        println()
    }
}
```

## 4. Dry Run

**Input:** `s = "abcabcbb"`

| Right | Character | Window  | Max Length |
|------:|:---------:|---------|-----------:|
|     0 |     a     | `"a"`   |          1 |
|     1 |     b     | `"ab"`  |          2 |
|     2 |     c     | `"abc"` |          3 |
|     3 |     a     | `"bca"` |          3 |
|     4 |     b     | `"cab"` |          3 |
|     5 |     c     | `"abc"` |          3 |
|     6 |     b     | `"cb"`  |          3 |
|     7 |     b     | `"b"`   |          3 |

**Final Output:** `3`

## 5. Test Results

| Test Case    | Expected | Actual | Result |
|--------------|---------:|-------:|--------|
| `""`         |        0 |      0 | PASS   |
| `"abcabcbb"` |        3 |      3 | PASS   |
| `"bbbbb"`    |        1 |      1 | PASS   |
| `"pwwkew"`   |        3 |      3 | PASS   |
| `"abcdef"`   |        6 |      6 | PASS   |
| `"abba"`     |        2 |      2 | PASS   |

## 6. Complexity Analysis

- **Time Complexity:** `O(n)`  
  Each character is added to and removed from the set at most once.

- **Space Complexity:** `O(min(n, k))`  
  The set stores only the characters in the current window, where `k` is the size of the character set.

## 7. Interview Explanation

"I use a sliding window and a set to keep track of unique characters. The right pointer expands the window. When a
duplicate appears, I move the left pointer forward and remove characters until the duplicate is gone. I update the
maximum window length at every step. Since each character enters and leaves the set at most once, the time complexity is
O (n)."

## 8. Key Takeaway

Use the **Sliding Window** pattern when you need to find a longest or shortest substring that satisfies a condition.

Use a **HashSet** when you need to track unique characters efficiently.
