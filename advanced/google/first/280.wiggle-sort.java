/*
 * [280] Wiggle Sort
 *
 * https://leetcode.com/problems/wiggle-sort/description/
 *
 * algorithms
 * Medium (58.93%)
 * Total Accepted:    45.2K
 * Total Submissions: 76.7K
 * Testcase Example:  '[3,5,2,1,6,4]'
 *
 * Given an unsorted array nums, reorder it in-place such that nums[0] <=
 * nums[1] >= nums[2] <= nums[3]....
 * 
 * Example:
 * 
 * 
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 * 
 */
class Solution {
    public void wiggleSort(int[] nums) {
       if (nums.length < 2) {
           return;
       } 
       for (int i = 0; i < nums.length - 1; i++) {
           if (shouldSwap(nums, i)) {
               swapWithNext(nums,i);
           }
       }
    }
    private boolean shouldSwap(int[] nums, int i) {
        if (i % 2 == 0) {
            return nums[i] > nums[i+1];
        } else  {
            return nums[i] < nums[i+1];
        }
    }

    private void swapWithNext(int[] nums, int i) {
        int buffer = nums[i];
        nums[i] = nums[i+1];
        nums[i+1] = buffer;
    }
}
