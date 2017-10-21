/*
 * [90] Subsets II
 *
 * https://leetcode.com/problems/subsets-ii
 *
 * algorithms
 * Medium (36.50%)
 * Total Accepted:    119.9K
 * Total Submissions: 328.4K
 * Testcase Example:  '[1,2,2]'
 *
 * 
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * 
 * For example,
 * If nums = [1,2,2], a solution is:
 * 
 * 
 * 
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 */
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length == 0) {
        	return ret;
        }
        //the reason to sort is the aggregate the similar numbers.
        Arrays.sort(nums);
        List<Integer> stack = new ArrayList<>();
        helper(nums, stack, ret, 0);
        return ret;
    }
    //util functions
    private void helper(int[] nums, List<Integer> stack, List<List<Integer>> ret, int start) {
    	//firstly push back
    	ret.add(new ArrayList<>(stack));
    	for(int i = start; i < nums.length; ) {
    		int ref = nums[i];
    		stack.add(ref);
    		helper(nums,stack,ret, i+1);
    		stack.remove(stack.size() -1);
    		while(i < nums.length && nums[i] == ref) {
    			i++;
    		}
    	}
    }
}
