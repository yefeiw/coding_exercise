/*
 * [34] Search for a Range
 *
 * https://leetcode.com/problems/search-for-a-range
 *
 * algorithms
 * Medium (31.41%)
 * Total Accepted:    157.2K
 * Total Submissions: 500.4K
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * Given an array of integers sorted in ascending order, find the starting and
 * ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 *
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[]{-1,-1};
        if (nums.length == 0) {
            return ret;
        }
        int first = searchFirst(nums,target);
        if(first == -1) {
            return ret;
        }
        int second = searchLast(nums,target);

        ret[0] =  first;
        ret[1] = second;
        return ret;
    }

    //util functions
    private int searchFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length -1;

        while(start < end - 1) {
            int mid = start + (end - start) / 2;
            int cand = nums[mid];
            if (cand >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
    private int searchLast(int[] nums, int target) {
        int start = 0;
        int end = nums.length -1;

        while(start < end - 1) {
            int mid = start + (end - start) / 2;
            int cand = nums[mid];
            if (cand > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        return -1;
    }
}
