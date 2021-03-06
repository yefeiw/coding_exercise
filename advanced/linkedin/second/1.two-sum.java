/*
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum
 *
 * algorithms
 * Easy (35.67%)
 * Total Accepted:    663.8K
 * Total Submissions: 1.9M
 * Testcase Example:  '[3,2,4]\n6'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 *
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map  = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (map.containsKey(target - val)) {
                int[] ret = new int[2];
                ret[0] = map.get(target -val);
                ret[1] = i;
                return ret;
            }
            map.put(val,i);
        }
        return new int[]{-1,-1};
    }
}
