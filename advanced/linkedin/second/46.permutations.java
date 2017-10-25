/*
 * [46] Permutations
 *
 * https://leetcode.com/problems/permutations
 *
 * algorithms
 * Medium (44.88%)
 * Total Accepted:    192.3K
 * Total Submissions: 428.4K
 * Testcase Example:  '[1,2,3]'
 *
 *
 * Given a collection of distinct numbers, return all possible permutations.
 *
 *
 *
 * For example,
 * [1,2,3] have the following permutations:
 *
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 *
 *
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length == 0) {
            return ret;
        }

        boolean[] visited = new boolean[nums.length];
        backtrackHelper(nums,visited,new ArrayList<>(),ret);
        return ret;
    }

    //util functions
    private void backtrackHelper(int[] nums, boolean[] visited, List<Integer> stack, List<List<Integer>> ret) {
        if (stack.size() >= nums.length) {
            List<Integer> result = new ArrayList<>(stack);
            ret.add(result);
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            stack.add(nums[i]);
            backtrackHelper(nums,visited,stack,ret);
            stack.remove(stack.size() -1);
            visited[i] = false;
        }
    }
}
