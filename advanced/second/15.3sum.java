/*
 * [15] 3Sum
 *
 * https://leetcode.com/problems/3sum
 *
 * algorithms
 * Medium (21.66%)
 * Total Accepted:    240.1K
 * Total Submissions: 1.1M
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a
 * + b + c = 0? Find all unique triplets in the array which gives the sum of
 * zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is:
 * [
 * ⁠ [-1, 0, 1],
 * ⁠ [-1, -1, 2]
 * ]
 * 
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
        	return ans;
        }
        //firstly, sort the array
        Arrays.sort(nums);
        //then, traverse the array and get the sums.
        for (int i = 0; i < nums.length -2; ) {
        	int start = nums[i];
        	int left= i+1;
        	int right = nums.length -1;
        	while(left < right) {
        		int curSum = nums[left] + nums[right];
        		if (curSum > 0-start) {
        			right --;
        		} else if (curSum < 0-start) {
        			left++;
        		} else {
        			//they are equal
        			int ref = nums[left];
        			List<Integer> cand = new ArrayList<>();
        			cand.add(start);
        			cand.add(nums[left])	;
        			cand.add(nums[right]);
        			ans.add(cand);
        			while(left < right && nums[left] == ref) left++;
        		}
        	}
        	while( i < nums.length && nums[i] == start) {
        		i++;
        	}
        }
        return ans;
    }
}
