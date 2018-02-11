/*
 * [215] Kth Largest Element in an Array
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (40.19%)
 * Total Accepted:    181.7K
 * Total Submissions: 452K
 * Testcase Example:  '[1]\n1'
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 * Credits:Special thanks to @mithmatt for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return select(nums,nums.length - k,0,nums.length-1);
    }

    private int select(int[] nums, int k, int start, int end) {
        int index = partition(nums,start,end);
        if (index == k) {
            return nums[index];
        } else if (index > k) {
            return select(nums,k,start,index-1);
        } else  {
            return select(nums,k,index+1,end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int fwd = start;
        int bwd = end;
        while (fwd < bwd) {
            if (nums[fwd] <= pivot) {
                fwd++;
            } else  {
                swap(nums,fwd,bwd);
                bwd--;
            }
        }
        return fwd;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
