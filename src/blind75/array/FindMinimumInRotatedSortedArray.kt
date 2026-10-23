package blind75.array

fun main() {
    val nums = intArrayOf(3, 4, 5, 1, 2)
    println(findMin(nums))
}

/*
Brute Force
Time  : O(n)
Space : O(1)
*/
fun findMinBruteForce(nums: IntArray): Int {
    var minimum = nums[0]

    for (num in nums) {
        minimum = minOf(minimum, num)
    }

    return minimum
}

/*
Optimized: Binary Search
Time  : O(log n)
Space : O(1)
*/
fun findMin(nums: IntArray): Int {
    var left = 0
    var right = nums.lastIndex

    while (left < right) {
        val mid = (left + right) / 2

        if (nums[mid] > nums[right]) {
            // Minimum is in the right half
            left = mid + 1
        } else {
            // Minimum is at mid or in the left half
            right = mid
        }
    }

    return nums[left]
}