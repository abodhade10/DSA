package blind75.array

fun main() {

    val nums = intArrayOf(1, 2, 3, 4)

    println(productExceptSelfOptimized(nums).contentToString())
}

/*
Brute Force Approach

Time  : O(n²)
Space : O(1)

For every element, calculate the product
of all other elements.
*/
fun productExceptSelf(nums: IntArray): IntArray {

    val result = IntArray(nums.size)

    for (i in nums.indices) {

        var product = 1

        for (j in nums.indices) {

            if (i != j) {
                product *= nums[j]
            }
        }

        result[i] = product
    }

    return result
}

/*
Optimized Approach

Pattern: Prefix + Suffix

Time  : O(n)
Space : O(1) extra space

First pass:
Store product of elements on the left.

Second pass:
Multiply by product of elements on the right.
*/
fun productExceptSelfOptimized(nums: IntArray): IntArray {

    val result = IntArray(nums.size)

    // Product of elements on the left
    var leftProduct = 1

    for (i in nums.indices) {

        result[i] = leftProduct

        leftProduct *= nums[i]
    }

    // Product of elements on the right
    var rightProduct = 1

    for (i in nums.lastIndex downTo 0) {

        result[i] *= rightProduct

        rightProduct *= nums[i]
    }

    return result
}


