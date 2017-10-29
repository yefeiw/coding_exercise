/*
 * [238] Product of Array Except Self
 *
 * https://leetcode.com/problems/product-of-array-except-self
 *
 * algorithms
 * Medium (49.29%)
 * Total Accepted:    111K
 * Total Submissions: 225.3K
 * Testcase Example:  '[0,0]'
 *
 *
 * Given an array of n integers where n > 1, nums, return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 *
 * Solve it without division and in O(n).
 *
 * For example, given [1,2,3,4], return [24,12,8,6].
 *
 * Follow up:
 * Could you solve it with constant space complexity? (Note: The output array
 * does not count as extra space for the purpose of space complexity analysis.)
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums.length < 1) {
            return new int[0];
        }
        int m = nums.length;
        int[] ret =  new int[m];
        Arrays.fill(ret, 1);
        int rollingProduct  = nums[0];

        for (int i = 1; i < m; i++) {
            ret[i] *= rollingProduct;
            rollingProduct *= nums[i];
        }

        rollingProduct = nums[m-1];
        for (int i = m-2; i >= 0; i--) {
            ret[i] *= rollingProduct;
            rollingProduct *= nums[i];
        }

        return ret;
    }
}
