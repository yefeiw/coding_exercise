/*
 * [75] Sort Colors
 *
 * https://leetcode.com/problems/sort-colors
 *
 * algorithms
 * Medium (38.08%)
 * Total Accepted:    175.1K
 * Total Submissions: 459.8K
 * Testcase Example:  '[0]'
 *
 * 
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * 
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * 
 * 
 * 
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * 
 * 
 * click to show follow up.
 * 
 * 
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 * 
 * 
 */
class Solution {
    public void sortColors(int[] nums) {
        int left = 0; int right = nums.length -1;
        for (int i = 0; i <= right; ) {
        	if (nums[i] == 0){
        		swap(nums,i,left);
        		left++;
        		i++;
        	} else if (nums[i] == 1) {
        		//do nothing
        		i++;
        	} else {
        		//nums[i] == 2
        		swap(nums,i,right);
        		right--;
        	}
        }
    }
    //util functions
    private void swap(int[] nums, int i, int j) {
    	if (i == j) return;
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }
}
