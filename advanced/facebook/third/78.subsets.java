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
        List<List<Integer>> ret =  new ArrayList<>();
        if (nums.length == 0) {
            return ret;
        }
        helper(ret, nums, 0, new ArrayList<>());
        return ret;
    }

    //util function
    private void helper(List<List<Integer>> ret, int[] nums, int pos, List<Integer> stack) {
        ret.add(new ArrayList<>(stack));
        if (pos >= nums.length) {
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            stack.add(nums[i]);
            helper(ret, nums, i+1, stack);
            stack.remove(stack.size() -1);
        }
    }
}
