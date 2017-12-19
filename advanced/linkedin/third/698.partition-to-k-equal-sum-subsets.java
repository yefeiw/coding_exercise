/*
 * [698] Partition to K Equal Sum Subsets
 *
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets
 *
 * algorithms
 * Medium (36.98%)
 * Total Accepted:    5.7K
 * Total Submissions: 15.4K
 * Testcase Example:  '[4,3,2,3,5,2,1]\n4'
 *
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into k non-empty subsets whose sums are all
 * equal.
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3),
 * (2,3) with equal sums.
 *
 *
 *
 * Note:
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 *
 */
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }


    }
}
