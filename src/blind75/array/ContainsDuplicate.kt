package blind75.array

fun main(){
    println(containsDuplicateOptimize(intArrayOf(1, 2, 3, 1)))
}

/**
 * Brute Force
 * Time  : O(n²)
 * Space : O(1)
 *
 * Why O(n²)?
 * Because there are two loops.
 */
fun containsDuplicate(nums: IntArray): Boolean{
    for(i in nums.indices){

        for (j in i+1 until nums.size){
            if (nums[j] == nums[i]){
                return true
            }
        }
    }
    return false
}

fun containsDuplicateOptimize(nums: IntArray): Boolean{

    var seen = HashSet<Int>()

    for (num in nums){

        if (seen.contains(num)){
            return true
        }
        seen.add(num)
    }

    return false
}

fun containsDuplicateOptimized(nums: IntArray): Boolean {

    val seen = HashSet<Int>()

    for (num in nums) {

        if (!seen.add(num)) {
            return true
        }
    }

    return false
}


