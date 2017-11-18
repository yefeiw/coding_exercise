/*
 * [53] Maximum Subarray
 *
 * https://leetcode.com/problems/maximum-subarray
 *
 * algorithms
 * Easy (39.82%)
 * Total Accepted:    242.6K
 * Total Submissions: 609K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 *
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest sum.
 *
 *
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 *
 *
 * click to show more practice.
 *
 * More practice:
 *
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 *
 */
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxEndingHere = nums[0];
        int maxSoFar  = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere+nums[i],nums[i]);
            maxSoFar = Math.max(maxEndingHere,maxSoFar);
        }

        return maxSoFar;
    }
}
