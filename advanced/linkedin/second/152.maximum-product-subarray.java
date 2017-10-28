/*
 * [152] Maximum Product Subarray
 *
 * https://leetcode.com/problems/maximum-product-subarray
 *
 * algorithms
 * Medium (26.06%)
 * Total Accepted:    116.2K
 * Total Submissions: 445.8K
 * Testcase Example:  '[-2]'
 *
 *
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest product.
 *
 *
 *
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 *
 */
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int ret = nums[0];
        int rollingMax = nums[0];
        int rollingMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = Math.max(nums[i],Math.max(rollingMax * nums[i], rollingMin * nums[i]));
            int curMin = Math.min(nums[i],Math.min(rollingMin * nums[i], rollingMax * nums[i]));
            ret = Math.max(ret, curMax);
            rollingMax = curMax;
            rollingMin = curMin;
        }

        return ret;
    }
}
