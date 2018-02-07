/*
 * [162] Find Peak Element
 *
 * https://leetcode.com/problems/find-peak-element/description/
 *
 * algorithms
 * Medium (38.44%)
 * Total Accepted:    139.9K
 * Total Submissions: 362.9K
 * Testcase Example:  '[1,2,3,1]'
 *
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 *
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 *
 * You may imagine that num[-1] = num[n] = -∞.
 *
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 *
 * click to show spoilers.
 *
 * Note:
 * Your solution should be in logarithmic complexity.
 *
 *
 * Credits:Special thanks to @ts for adding this problem and creating all test
 * cases.
 */
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length-1] > nums[nums.length - 2]) {
            return nums.length -1;
        }
        return helper(nums, 1, nums.length-2);
    }

    private int helper(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (isPeak(nums,start)) return start;
        if (isPeak(nums,end)) return end;

        int mid = start + (end - start) / 2;
        if (isPeak(nums,mid)) return mid;
        if (nums[mid-1] > nums[mid]) {
            return helper(nums,start,mid);
        } else {
            return helper(nums,mid,end);
        }
    }

    private boolean isPeak(int[] nums, int pos){
        return nums[pos-1]<nums[pos] && nums[pos+1]< nums[pos];
    }

}
