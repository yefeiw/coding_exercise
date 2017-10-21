/*
 * [410] Split Array Largest Sum
 *
 * https://leetcode.com/problems/split-array-largest-sum
 *
 * algorithms
 * Hard (37.72%)
 * Total Accepted:    15.5K
 * Total Submissions: 41K
 * Testcase Example:  '[7,2,5,10,8]\n2'
 *
 * Given an array which consists of non-negative integers and an integer m, you
 * can split the array into m non-empty continuous subarrays. Write an
 * algorithm to minimize the largest sum among these m subarrays.
 * 
 * 
 * Note:
 * If n is the length of array, assume the following constraints are
 * satisfied:
 * 
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 
 * 
 * 
 * Examples: 
 * 
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * 
 * Output:
 * 18
 * 
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * 
 * 
 */
class Solution {
    public int splitArray(int[] nums, int m) {
        if (nums.length == 0) {
        	return 0;
        }
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int num : nums) {
        	max = Math.max(max,num);
        	sum += num;
        }
        int start = max;
        int end = sum;
        while (start < end - 1) {
        	int mid = start + (end - start) / 2;
        	if (split(nums,mid) <= m) {
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        //since we are minimizing, try start first
        if (split(nums,start) <= m) return start;
        else return end;
    }
    //util functions
    private int split(int[] nums, int threshold) {
    	int curSum = 0;
    	int cnt = 1;
    	for (int i = 0; i < nums.length; i++) {
    		if (curSum + nums[i] > threshold) {
    			cnt++;
    			curSum  = nums[i];
    		} else {
    			curSum = curSum + nums[i];
    		}
    	}
    	return cnt;
    }
}
