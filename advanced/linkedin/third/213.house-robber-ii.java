/*
 * [213] House Robber II
 *
 * https://leetcode.com/problems/house-robber-ii
 *
 * algorithms
 * Medium (34.29%)
 * Total Accepted:    65.9K
 * Total Submissions: 192.2K
 * Testcase Example:  '[]'
 *
 * Note: This is an extension of House Robber.
 *
 * After robbing those houses on that street, the thief has found himself a new
 * place for his thievery so that he will not get too much attention. This
 * time, all houses at this place are arranged in a circle. That means the
 * first house is the neighbor of the last one. Meanwhile, the security system
 * for these houses remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 *
 * Credits:Special thanks to @Freezen for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums,0,nums.length-2), rob(nums,1,nums.length-1));
    }

    private int rob(int[] nums, int start, int end) {
        int include = 0;
        int exclude  = 0;
        for (int i = start; i <=end; i++) {
            int inc = include;
            int exc = exclude;
            include = exc + nums[i];
            exclude = Math.max(exc,inc);
        }
        return Math.max(include, exclude);
    }
}
