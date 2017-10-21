/*
 * [33] Search in Rotated Sorted Array
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array
 *
 * algorithms
 * Medium (32.11%)
 * Total Accepted:    191K
 * Total Submissions: 595K
 * Testcase Example:  '[]\n5'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 */
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length < 1) {
        	return -1;
        }
        int start = 0;
        int end = nums.length -1 ;
        int ref = nums[end];
        if (ref == target) return end;
        while (start < end - 1) {
        	int mid =  start + (end - start) / 2;
        	int cand = nums[mid];
        	if (cand < target) {
        		//note: only a small subset of the condition will cause hte binary search to go the otherwise.
        		//therefore, this condition judgement is enough
        		if (cand < ref && target > ref) {
        			end  = mid;
        		} else {
        			start =mid;
        		}
        	} else if (cand > target) {
        		if (cand > ref && target < ref) {
        			start = mid;
        		} else {
        			end = mid;
        		}
        	} else {
        		return mid;
        	}
        }
        if(nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1	;
    }
}
