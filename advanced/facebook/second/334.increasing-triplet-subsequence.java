/*
 * [334] Increasing Triplet Subsequence
 *
 * https://leetcode.com/problems/increasing-triplet-subsequence
 *
 * algorithms
 * Medium (39.45%)
 * Total Accepted:    46K
 * Total Submissions: 116.6K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 
 * Given an unsorted array return whether an increasing subsequence of length 3
 * exists or not in the array.
 * 
 * 
 * Formally the function should:
 * Return true if there exists i, j, k  
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 
 * else return false.
 * 
 * 
 * 
 * Your algorithm should run in O(n) time complexity and O(1) space
 * complexity.
 * 
 * 
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * 
 * 
 * Given [5, 4, 3, 2, 1],
 * return false.
 * 
 * 
 * Credits:Special thanks to @DjangoUnchained for adding this problem and
 * creating all test cases.
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        //since there are only three numbers, find those three numbers.
        if (nums.length < 3) {
        	return false;
        }
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
        	if (nums[i] <= firstMin) {
        		//key hint, firstMin < secondMin still holds!!!
        		firstMin = nums[i];
        	} else if (nums[i] <= secondMin) {
        		secondMin = nums[i];
        	} else {
        		return true;
        	}
        }
        //reaching here means nothing is found, and thus return false;
        return false;
    }
}
