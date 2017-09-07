/*
 * [377] Combination Sum IV
 *
 * https://leetcode.com/problems/combination-sum-iv
 *
 * algorithms
 * Medium (41.98%)
 * Total Accepted:    41.7K
 * Total Submissions: 99.4K
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
    // public int combinationSum4(int[] nums, int target) {
    //     //hint:only asking for number of solutions.
    //     //IDEA: for usual backtracking solutions, we only change the set of chosen numbers.
    //     // here, since only the number of solutions is interested, we can change the target itself instead.
    //     if(target == 0) {
    //     	return 1;
    //     }
    //     int ans = 0;
    //     for(int i = 0; i < nums.length; i++) {
    //     	if (nums[i] <= target) {
    //     		ans += combinationSum4 (nums, target -  nums[i]);
    //     	}
    //     }
    //     return ans;

    // }
    public int combinationSum(int[] nums, int target) {
       return combinationSum4DP(nums,target);
    }
    public int combinationSum4DP(int[] nums, int target) {
        //IDEA: dynamic programming
        if (target == 0){
        	return 1;
        }
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
        	for (int j = 0; j < nums.length; j++) {
        		if (i - nums[j] >= 0) {
        			dp[i] += dp[i-nums[j]];
        		}
        	}
        }
        return dp[target];
    }
    public int combinationSum4MemSearch(int[] nums, int target) {
        //IDEA: memorized search.
        //what to remember?
        if (target == 0){
        	return 1;
        }
        int[] mem = new int[target+1];
        //special conditions!!!!!
        Arrays.fill(mem, -1);
        mem[0] = 1;
        return helper(nums, target, mem);
    }
    private int helper(int[] nums, int target, int[] mem) {
    	if (mem[target] != -1) {
    		return mem[target];
    	}
    	int ans = 0;
    	for(int i = 0;i < nums.length; i++) {
    		if (nums[i] <= target) {
    			ans += helper(nums, target - nums[i],mem);
    		}
    	}
    	mem[target] = ans;
    	return ans;
    }

}
