/*
 * [494] Target Sum
 *
 * https://leetcode.com/problems/target-sum
 *
 * algorithms
 * Medium (43.54%)
 * Total Accepted:    26.9K
 * Total Submissions: 61.7K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 
 * You are given a list of non-negative integers, a1, a2, ..., an, and a
 * target, S. Now you have 2 symbols + and -. For each integer, you should
 * choose one from + and - as its new symbol.
 * ⁠
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to
 * target S.  
 * 
 * 
 * Example 1:
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array is positive and will not exceed 20. 
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * 
 * 
 */
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 0) {
        	return 0;
        }
         return dfs(nums, 0,0,S);
    }

    private int dfs(int[] nums, int pos, int sum, int target) {
    	if(pos == nums.length) {
    		if(sum == target) {
    			return 1;
    		} else {
    			return 0;
    		}
    	}
    	return dfs(nums, pos+1, sum+nums[pos],target) + dfs(nums,pos+1, sum-nums[pos],target);
    }
}
