package blind75.array

fun main(){
    val nums = intArrayOf(2,7,11,15)
    val target = 9
    println(twoSumOptimized(nums, target).contentToString())
}


/*Brute Force approach
* Time  : O(n²)
Space : O(1)
Why O(n²)?
For every element, we potentially check every other element.
* */
fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices){
        for (j in i+1 until nums.size){
            if (nums[i] + nums[j] == target){
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf()
}

/**
 * Optimized way
 * Time  : O(n)
 * Space : O(n)
 *
 * Why O(n)?
 * We traverse the array once.
 * HashMap lookup is O(1) average.
 */
fun twoSumOptimized(nums: IntArray, target: Int): IntArray {

    // Stores:
    // number -> index
    val map = HashMap<Int, Int>()

    for (i in nums.indices) {

        val current = nums[i]

        // Number required to make target
        val required = target - current

        // Check if required number was already seen
        if (map.containsKey(required)) {

            return intArrayOf(
                map[required]!!,
                i
            )
        }

        // Store current number and its index
        map[current] = i
    }

    // No solution found
    return intArrayOf()
}