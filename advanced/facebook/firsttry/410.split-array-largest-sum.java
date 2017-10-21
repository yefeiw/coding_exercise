/*
 * [410] Split Array Largest Sum
 *
 * https://leetcode.com/problems/split-array-largest-sum
 *
 * algorithms
 * Hard (37.25%)
 * Total Accepted:    14.4K
 * Total Submissions: 38.6K
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
        int sum = 0;
        int max = 0;
        for (int num : nums) {
        	sum += num;
        	max = Math.max(max, num);
        }
        if (nums.length < m) {
        	return sum;
        }
        //idea is binary search and linear verification
        int start = max; int end = sum;
        while(start < end - 1) {
        	int mid = start + (end - start)  / 2;
        	int cand = verify(nums, mid);
        	if (cand <= m) {
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        if (verify(nums,start) <= m) return start;
        else return end;
    }
    private int verify (int[] nums, int target) {
    	int localSum = 0;
    	int cnt = 1;
    	for (int i = 0; i< nums.length; i++) {
    		if (localSum + nums[i] > target) {
    			localSum = nums[i];
    			cnt++;
    		} else {
    			localSum += nums[i];
    		}
    	}
    	return cnt;
    }
}
