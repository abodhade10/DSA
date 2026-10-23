package blind75.array

fun main() {
    val height = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)

    println("Brute Force: ${maxAreaBruteForce(height)}")
    println("Optimized: ${maxArea(height)}")

}

// Approach 1: Brute Force
// Time: O(n²)
// Space: O(1)
fun maxAreaBruteForce(height: IntArray): Int {
    var maxArea = 0

    for (i in 0 until height.size - 1) {

        for (j in i + 1 until height.size) {
            val width = j - i
            val minHeight = minOf(height[i], height[j])
            val area = width * minHeight
            maxArea = maxOf(maxArea, area)
        }
    }
    return maxArea

}

// Approach 2: Optimized — Two Pointers
// Time: O(n)
// Space: O(1)
fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.lastIndex
    var maxArea = 0

    while (left < right) {

        // Calculate the width between the two lines
        val width = right - left

        // The shorter line determines the water height
        val minHeight = minOf(height[left], height[right])

        // Calculate the current container area
        val area = width * minHeight

        // Update the maximum area
        maxArea = maxOf(maxArea, area)

        // Move the shorter line to try to find a taller one
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }

    return maxArea


}

