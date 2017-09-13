/*
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets
 *
 * algorithms
 * Medium (41.07%)
 * Total Accepted:    180.1K
 * Total Submissions: 438.4K
 * Testcase Example:  '[1,2,3]'
 *
 * 
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * 
 * For example,
 * If nums = [1,2,3], a solution is:
 * 
 * 
 * 
 * [
 * ⁠ [3],
 * ⁠ [1],
 * ⁠ [2],
 * ⁠ [1,2,3],
 * ⁠ [1,3],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length == 0) {
        	return ret;
        }
        List<Integer> stack =  new ArrayList<>();
        helper(nums,0,stack,ret);
        return ret;
    }

    private void helper(int[] nums, int start, List<Integer> stack, List<List<Integer>> ret){
    	//idea: other than this line, this is a usual backtracking solution.
    	// the only difference sources from the thinking that how should we return all elements.
   		ret.add(new ArrayList<>(stack));
    	for(int i = start; i < nums.length; i++) {
    		stack.add(nums[i]);
    		helper(nums, i+1, stack,ret);
    		stack.remove(stack.size() - 1);
    	}
    }
}
