class Solution {
	//static variable result. assuming single thread execution.
	private int result;

    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 0) {
        	return 0;
        }
        result = 0;
        dfs(nums,0,0,S);
        return result;
    }
    void dfs(int[] nums, int curSum, int start, int target) {
    	if (start == nums.length) {
    		if (curSum == target) {
    			result++;
    		}
    		return;
    	}
    	dfs(nums, curSum+nums[start], start+1, target);
    	dfs(nums, curSum-nums[start], start+1, target);
    }
}