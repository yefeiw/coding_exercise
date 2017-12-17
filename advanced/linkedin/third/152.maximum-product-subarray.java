/*
 * [152] Maximum Product Subarray
 *
 * https://leetcode.com/problems/maximum-product-subarray
 *
 * algorithms
 * Medium (26.24%)
 * Total Accepted:    120K
 * Total Submissions: 457.5K
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
            return 0;
        }
        int maxProduct = nums[0];
        int maxTillNow = nums[0];
        int minTillNow = nums[0];
        for(int i = 1; i < nums.length; i++) {
            //BUG: make sure that we don't overwrite maxTillNow and minTillNow right in the middle of the calculation.
            int curMax = maxTillNow;
            int curMin = minTillNow;
            maxTillNow = Math.max(nums[i], Math.max(curMax * nums[i], curMin * nums[i]));
            minTillNow = Math.min(nums[i], Math.min(curMax * nums[i], curMin * nums[i]));
            maxProduct = Math.max(maxProduct,maxTillNow);
        }

        return maxProduct;

    }
}
