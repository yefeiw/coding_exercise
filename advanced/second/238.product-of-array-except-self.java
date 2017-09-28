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
        if (nums.length < 2) {
        	return nums;
        }
        int product = 1;
        int[] ret =  new int[nums.length];
        Arrays.fill(ret, 1);
        for (int i = 0; i < nums.length-1; i++) {
        	product = product * nums[i];
        	ret[i+1] = product;
        }
        product = 1;
        for(int i = nums.length -1 ; i > 0;  i--) {
        	product = product * nums[i];
        	ret[i-1] *= product;
        }
        return ret;
    }
}
