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
        val actual = lengthOfLongestSubstring(input)

        println("Input: \"$input\"")
        println("Expected: $expected")
        println("Actual: $actual")
        println("Test Result: ${if (actual == expected) "PASS" else "FAIL"}")
        println()
    }
}

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

        // Add current character to the window
        seen.add(s[right])

        // Update the maximum window length
        val currentLength = right - left + 1
        maxLength = maxOf(maxLength, currentLength)
    }

    return maxLength
}

