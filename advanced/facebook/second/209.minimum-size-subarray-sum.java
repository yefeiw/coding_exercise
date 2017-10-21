/*
 * [209] Minimum Size Subarray Sum
 *
 * https://leetcode.com/problems/minimum-size-subarray-sum
 *
 * algorithms
 * Medium (30.89%)
 * Total Accepted:    91.9K
 * Total Submissions: 297.6K
 * Testcase Example:  '7\n[2,3,1,2,4,3]'
 *
 * 
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
 * one, return 0 instead.
 * 
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * 
 * click to show more practice.
 * 
 * More practice:
 * 
 * If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log n).
 * 
 * 
 * Credits:Special thanks to @Freezen for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
        	return 0;
        }
        int fast = 0; int slow = 0;
        int ret = Integer.MAX_VALUE;
        	int bufSum = 0;

        while(fast < nums.length) {
        	while(fast < nums.length && bufSum < s) {
        		bufSum +=nums[fast];
        		fast++;
        	}
        	while(slow <=fast && bufSum >= s) {
        		bufSum -= nums[slow];
        		ret = Math.min(ret, fast - slow);
        		slow++;
        	}
        }
        return (ret == Integer.MAX_VALUE) ? 0 : ret;
    }
}
