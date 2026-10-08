package blind75.array

fun main() {

    val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)

    println("Brute Force: ${maxSubArray(nums)}")
    println("Kadane: ${maxSubArrayKadane(nums)}")
}

/*
Brute Force Approach

Time  : O(n²)
Space : O(1)

Try every possible subarray and keep track
of the maximum sum.
*/
fun maxSubArray(nums: IntArray): Int {

    var maxSum = Int.MIN_VALUE

    for (i in nums.indices) {

        var currentSum = 0

        for (j in i until nums.size) {

            currentSum += nums[j]

            maxSum = maxOf(maxSum, currentSum)
        }
    }

    return maxSum
}

/*
Kadane's Algorithm

Time  : O(n)
Space : O(1)

If current sum becomes negative,
discard it and start a new subarray.
*/
fun maxSubArrayKadane(nums: IntArray): Int {

    var currentSum = 0
    var maxSum = Int.MIN_VALUE

    for (num in nums) {

        currentSum += num

        maxSum = maxOf(maxSum, currentSum)

        if (currentSum < 0) {
            currentSum = 0
        }
    }

    return maxSum
}