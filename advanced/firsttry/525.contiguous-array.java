/*
 * [525] Contiguous Array
 *
 * https://leetcode.com/problems/contiguous-array
 *
 * algorithms
 * Medium (39.54%)
 * Total Accepted:    13.2K
 * Total Submissions: 33.4K
 * Testcase Example:  '[0,1]'
 *
 * Given a binary array, find the maximum length of a contiguous subarray with
 * equal number of 0 and 1. 
 * 
 * 
 * Example 1:
 * 
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of
 * 0 and 1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal
 * number of 0 and 1.
 * 
 * 
 * 
 * Note:
 * The length of the given binary array will not exceed 50,000.
 * 
 */
class Solution {
    public int findMaxLength(int[] nums) {
        int[] prefixSum = new int[nums.length+1];
        //Key idea: convert the 0 - 1 array into -1 - 1 array. 
        //and then it will be the usual sum == 0 answer
        for (int i = 0; i < nums.length; i++) {
        	prefixSum[i+1] = prefixSum[i] + ((nums[i] == 0) ? -1 : 1);
        }
		Map<Integer, Integer> map = new HashMap<>();
		int ans = 0;
		for (int i = 0; i <= nums.length; i++) {
			if (map.containsKey(prefixSum[i])) {
				ans = Math.max(ans, i - map.get(prefixSum[i]));
			} else {
				map.put(prefixSum[i], i);
			}
		}       	
        return ans;
    }
}
