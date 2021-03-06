/*
 * [325] Maximum Size Subarray Sum Equals k
 *
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k
 *
 * algorithms
 * Medium (42.57%)
 * Total Accepted:    34.7K
 * Total Submissions: 81.6K
 * Testcase Example:  '[1,-1,5,-2,3]\n3'
 *
 * 
 * Given an array nums and a target value k, find the maximum length of a
 * subarray that sums to k. If there isn't one, return 0 instead.
 * 
 * 
 * 
 * ⁠   Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit
 * signed integer range.
 * 
 * 
 * 
 * ⁠   Example 1:
 * 
 * 
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the
 * longest)
 * 
 * 
 * 
 * ⁠   Example 2:
 * 
 * 
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * 
 * 
 * 
 * ⁠   Follow Up:
 * ⁠   Can you do it in O(n) time?
 * 
 */
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
    	if (nums.length == 0) {
    		return 0;
    	}
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
        	prefixSum[i] = prefixSum[i-1] + nums[i];
        }
        int ret = 0;//returning value;
        Map<Integer, Integer> map =  new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
        	int cand = prefixSum[i];
        	if (cand == k) {
        		ret = Math.max(ret, i+1);
        	} else {
        		if (map.containsKey(cand - k)) {
        			ret = Math.max(ret, i - map.get(cand - k) );
        		}
        	}
        	if (!map.containsKey(cand)) {
        		map.put(cand,i);
        	}
        }
        return ret;

    }
}
