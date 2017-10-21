/*
 * [80] Remove Duplicates from Sorted Array II
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii
 *
 * algorithms
 * Medium (36.13%)
 * Total Accepted:    126K
 * Total Submissions: 348.7K
 * Testcase Example:  '[]'
 *
 * 
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * 
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * 
 * 
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new
 * length.
 * 
 */
class Solution {
	//Note; this is the official solution from Jiuzhang about this kind of problems.
	//Remember the strategy and make sure we go over the examples for the idea of the problem.
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
        	return nums.length;	
        }
        int j = 0;
        int repeat = 1;
        for(int i = 1; i < nums.length; i++) {
        	if (nums[i] != nums[j]) {
        		nums[++j] = nums[i];
        		repeat = 1;	
        	} else {
        		if (repeat < 2) {
        			//BUGWARNING: need to increment j before doing assignments
        			nums[++j] = nums[i];
        			repeat++;
        		}
        	}
        }
        return j+1;
    }
}
