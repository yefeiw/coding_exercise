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
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length == 0) {
            return ret;
        }
        Arrays.sort(nums);
        List<Integer> stack = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackHelper(nums,used,stack,ret);
        return ret;
    }

    //util functions
    private void backtrackHelper(int[] nums, boolean[] used, List<Integer> stack, List<List<Integer>> ret) {
        if (stack.size() == nums.length) {
            ret.add(new ArrayList<>(stack));
            return;
        }
        for(int i = 0; i < nums.length;i++) {
            if (used[i] ==  true) continue;
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            stack.add(nums[i]);
            backtrackHelper(nums,used,stack,ret);
            stack.remove(stack.size() -1);
            used[i] = false;

        }
    }

    private void swap(List<Integer> input, int i, int j) {
        int temp  = input.get(i);
        input.set(i,input.get(j));
        input.set(j,temp);
    }
}
