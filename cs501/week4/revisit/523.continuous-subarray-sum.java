/*
 * [523] Continuous Subarray Sum
 *
 * https://leetcode.com/problems/continuous-subarray-sum
 *
 * algorithms
 * Medium (22.92%)
 * Total Accepted:    20.3K
 * Total Submissions: 88.8K
 * Testcase Example:  '[23,2,4,6,7]\n6'
 *
 * 
 * Given a list of non-negative numbers and a target integer k, write a
 * function to check if the array has a continuous subarray of size at least 2
 * that sums up to the multiple of k, that is, sums up to n*k where n is also
 * an integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up
 * to 6.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5
 * and sums up to 42.
 * 
 * 
 * 
 * Note:
 * 
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit
 * integer.
 * 
 * 
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length == 0) {
        	return false;
        }
        if(k == 0) {
        	return countZero(nums);
        }
        int[] prefixSum = new int[nums.length];
        int runningSum = 0;
        for(int i = 0; i < nums.length; i++) {
        	runningSum +=nums[i];
        	prefixSum[i] = runningSum % k;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
        	if (set.contains(prefixSum[i])) {
        		return true;
        	}
        	if (prefixSum[i] == 0 && i > 0) {
        		return true;
        	}
        	set.add(prefixSum[i]);
        }
        return false;

    }
    //util functions
    public boolean countZero(int[] nums) {
    	int maxLen = 0;
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] == 0) {
    			maxLen++;
    			if (maxLen > 1) {
    				return true;
    			}
    		} else {
    			maxLen = 0;
    		}
    	}
    	return false;
    }
}
