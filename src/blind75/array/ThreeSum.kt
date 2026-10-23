package blind75.array

fun main() {
    val nums = intArrayOf(-1, 0, 1, 2, -1, -4)

    println("Brute Force: ${threeSumBruteForce(nums)}")

    val nums2 = intArrayOf(-1, 0, 1, 2, -1, -4)
    println("Optimized: ${threeSum(nums2)}")
}

// Approach 1: Brute Force
// Time: O(n³), excluding result-handling overhead
// Space: Depends on the number of unique triplets
fun threeSumBruteForce(nums: IntArray): List<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    val n = nums.size

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            for (k in j + 1 until n) {

                if (nums[i] + nums[j] + nums[k] == 0) {
                    // Sort each triplet to avoid duplicates
                    result.add(
                        listOf(nums[i], nums[j], nums[k]).sorted()
                    )
                }
            }
        }
    }

    return result.toList()
}

// Approach 2: Optimized — Sorting + Two Pointers
// Time: O(n²)
// Auxiliary space: O(1), excluding output and sorting
fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    val result = mutableListOf<List<Int>>()

    for (i in 0 until nums.size - 2) {

        // Skip duplicate fixed numbers
        if (i > 0 && nums[i] == nums[i - 1]) continue

        // Three numbers cannot sum to zero if all are positive
        if (nums[i] > 0) break

        var left = i + 1
        var right = nums.lastIndex

        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]

            when {
                sum == 0 -> {
                    result.add(
                        listOf(nums[i], nums[left], nums[right])
                    )

                    left++
                    right--

                    // Skip duplicate left values
                    while (left < right &&
                        nums[left] == nums[left - 1]
                    ) {
                        left++
                    }

                    // Skip duplicate right values
                    while (left < right &&
                        nums[right] == nums[right + 1]
                    ) {
                        right--
                    }
                }

                sum < 0 -> left++  // Increase the sum

                else -> right--    // Decrease the sum
            }
        }
    }

    return result
}