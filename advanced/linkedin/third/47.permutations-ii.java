/*
 * [47] Permutations II
 *
 * https://leetcode.com/problems/permutations-ii
 *
 * algorithms
 * Medium (33.43%)
 * Total Accepted:    136.4K
 * Total Submissions: 408.1K
 * Testcase Example:  '[1,1,2]'
 *
 *
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 *
 *
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 *
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 *
 *
 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        backtrackHelper(nums,result, visited, new ArrayList<>());
        return result;
    }

    private void backtrackHelper(int[] nums, List<List<Integer>> result, boolean[] visited,
    List<Integer> stack) {
        if (stack.size() == nums.length) {
            result.add(new ArrayList(stack));
            return;
        }
        for (int i = 0; i < nums.length;) {
            if (visited[i]) {
                i++;
                continue;
            }
            visited[i] = true;
            stack.add(nums[i]);
            backtrackHelper(nums,result, visited,stack);
            stack.remove(stack.size() -1);
            visited[i] = false;
            int cur = nums[i];
            while(i < nums.length && nums[i] == cur) i++;
        }
    }
}
