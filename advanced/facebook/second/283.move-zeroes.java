/*
 * [283] Move Zeroes
 *
 * https://leetcode.com/problems/move-zeroes
 *
 * algorithms
 * Easy (50.16%)
 * Total Accepted:    213.2K
 * Total Submissions: 425K
 * Testcase Example:  '[0,1,0,3,12]'
 *
 * 
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * 
 * 
 * For example, given nums  = [0, 1, 0, 3, 12], after calling your function,
 * nums should be [1, 3, 12, 0, 0].
 * 
 * 
 * 
 * Note:
 * 
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * 
 * 
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 */
class Solution {
    public void moveZeroes(int[] nums) {
    	if(nums.length < 2) {
    		return;
    	}
        int index = 0;
        for ( int i = 0; i < nums.length; i++) {
        	if (nums[i] != 0) {
        		swap(nums,i,index);
        		index++;
        	}
        }
    }
    //util functions
    void swap(int[] nums, int i, int j) {
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }
}
