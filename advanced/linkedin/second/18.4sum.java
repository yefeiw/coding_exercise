/*
 * [18] 4Sum
 *
 * https://leetcode.com/problems/4sum
 *
 * algorithms
 * Medium (27.03%)
 * Total Accepted:    133.4K
 * Total Submissions: 493.6K
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S such
 * that a + b + c + d = target? Find all unique quadruplets in the array which
 * gives the sum of target.
 *
 * Note: The solution set must not contain duplicate quadruplets.
 *
 *
 *
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 * ⁠ [-1,  0, 0, 1],
 * ⁠ [-2, -1, 1, 2],
 * ⁠ [-2,  0, 0, 2]
 * ]
 *
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i < nums.length;) {
            int cur = nums[i];
            List<List<Integer>> results = threeSum(nums,i+1, target -nums[i]);
            for(List<Integer> result : results) {
                result.add(0,nums[i]);
                ret.add(result);
            }
            while(i < nums.length && nums[i] == cur) i++;
        }
        return ret;
    }
    //utils which is going to take long
    private List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> ret = new ArrayList<>();

        for (int i = start; i < nums.length;) {
            int cur = nums[i];
            List<List<Integer>> results = twoSum(nums,i+1, target - nums[i]);
            for(List<Integer> result : results) {
                result.add(0,nums[i]);
                ret.add(result);
            }
            while (i < nums.length && nums[i] == cur) i++;
        }
        return ret;
    }

    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> ret =new ArrayList<>();

        int left = start;
        int right = nums.length-1;
        while(left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                List<Integer> result = new ArrayList<>();
                result.add(nums[left]);
                result.add(nums[right]);
                ret.add(result);
                int cur = nums[left];
                while(left < right && nums[left] == cur) left++;
                right--;
            }
        }
        return ret;
    }
}
