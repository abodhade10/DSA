package blind75.array

import kotlin.math.max



fun main(){
    val input = intArrayOf(7,1,5,3,6,4)
    println(maxProfitOptimize(input))
}

/**
 * Brute Force
 * Time  : O(n²)
 * Space : O(1)
 * Why?
 * For every buying day, we check all possible selling days.
 */
fun maxProfit(prices: IntArray): Int {
    var maxProfit = 0

    for (i in prices.indices) {
        for (j in i + 1 until prices.size) {
            val profit = prices[j] - prices[i]
            maxProfit = max(maxProfit, profit)
        }
    }
    return maxProfit
}

/**
 * Time  : O(n)
 * Space : O(1)
 *
 * We scan the array only once and use only two variables.
 */
fun maxProfitOptimize(prices: IntArray): Int {

    // Cheapest price seen so far
    var minPrice = Int.MAX_VALUE

    // Maximum profit found so far
    var maxProfit = 0

    for (price in prices) {

        // Step 1: Find the cheapest buying price
        minPrice = minOf(minPrice, price)

        // Step 2: Calculate profit if we sell today
        val currentProfit = price - minPrice

        // Step 3: Keep the maximum profit
        maxProfit = maxOf(maxProfit, currentProfit)
    }

    return maxProfit
}