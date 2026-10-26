package blind75.string

fun main() {

    println(lengthOfLongestSubstringBruteForce("abcabcbb")) // 3
    println(lengthOfLongestSubstringOptimize("abcabcbb"))  // 3
}

/**
 * Brute Force
 * Time  : O(n³)
 * Space : O(min(n, k))
 *
 * Why O(n³)?
 * There are O(n²) possible substrings, and checking
 * each substring for duplicate characters takes O(n).
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
 *
 * Why O(n)?
 * Each character is added to and removed from the
 * HashSet at most once.
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
