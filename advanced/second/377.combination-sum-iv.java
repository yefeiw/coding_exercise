/*
 * [377] Combination Sum IV
 *
 * https://leetcode.com/problems/combination-sum-iv
 *
 * algorithms
 * Medium (42.07%)
 * Total Accepted:    43.7K
 * Total Submissions: 103.7K
 * Testcase Example:  '[1,2,3]\n4'
 *
 * ⁠Given an integer array with all positive numbers and no duplicates, find
 * the number of possible combinations that add up to a positive integer
 * target.
 * 
 * Example:
 * 
 * nums = [1, 2, 3]
 * target = 4
 * 
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 * 
 * 
 * 
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers? 
 * 
 * Credits:Special thanks to @pbrother for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {
        	return 0;
        }
        //This is exactly the same as the coin change problem.
        int[] dp = new int[target+1];
        for(int num : nums) {
        	if (num <= target) {
        		dp[num] = 1;
        	}
        }
        for(int i = 1; i <= target; i++) {
        	for (int num : nums) {
        		if (i > num) {
        			dp[i] += dp[i-num];
        		}
        	}
        }
        return dp[target];
    }
}
