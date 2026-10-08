package blind75.array

fun main() {
    val nums = intArrayOf(2, 3, -2, 4)

    println("Brute Force: ${maxProduct(nums)}")
    println("Optimized: ${maxProductOptimized(nums)}")
}

/*
Brute Force Approach
Time  : O(n²)
Space : O(1)
*/
fun maxProduct(nums: IntArray): Int {
    var maxProduct = Int.MIN_VALUE

    for (i in nums.indices) {
        var product = 1

        for (j in i until nums.size) {
            product *= nums[j]
            maxProduct = maxOf(maxProduct, product)
        }
    }

    return maxProduct
}

/*
Optimized Approach
Pattern: Track Maximum + Minimum
Time  : O(n)
Space : O(1)
*/
fun maxProductOptimized(nums: IntArray): Int {

    var currentMax = nums[0]
    var currentMin = nums[0]
    var maxProduct = nums[0]

    for (i in 1 until nums.size) {

        val num = nums[i]

        // Negative number can turn min into max
        if (num < 0) {
            val temp = currentMax
            currentMax = currentMin
            currentMin = temp
        }

        // Calculate current maximum and minimum
        currentMax = maxOf(num, currentMax * num)
        currentMin = minOf(num, currentMin * num)

        // Update overall maximum
        maxProduct = maxOf(maxProduct, currentMax)
    }

    return maxProduct
}