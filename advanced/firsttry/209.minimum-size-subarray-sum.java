/*
 * [209] Minimum Size Subarray Sum
 *
 * https://leetcode.com/problems/minimum-size-subarray-sum
 *
 * algorithms
 * Medium (30.70%)
 * Total Accepted:    89.6K
 * Total Submissions: 291.8K
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
        int fast = 0;
        int slow = 0;
        int rollingSum = 0;
        int ret = Integer.MAX_VALUE;
        //two pointers
        while(fast < nums.length) {
	        while(fast < nums.length && rollingSum < s) {
	        	rollingSum += nums[fast++];

	        }
	        //System.out.printf("fast is at %d\n",fast);
	       
	        while(slow <= fast && rollingSum >= s) {
	        	ret = Math.min(ret, fast - slow);
	        	rollingSum -=nums[slow++];
	        }
        	//System.out.printf("slow is at %d\n",slow);
        }
        return (ret == Integer.MAX_VALUE ? 0 : ret);
    }

}
