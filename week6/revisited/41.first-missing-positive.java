/*
 * [41] First Missing Positive
 *
 * https://leetcode.com/problems/first-missing-positive
 *
 * algorithms
 * Hard (25.60%)
 * Total Accepted:    110.3K
 * Total Submissions: 431K
 * Testcase Example:  '[]'
 *
 *
 * Given an unsorted integer array, find the first missing positive integer.
 *
 *
 *
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 *
 *
 *
 * Your algorithm should run in O(n) time and uses constant space.
 *
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i < nums.length) {
          //System.out.printf("i is %d, nums[i] is %d\n",i, nums[i]);
          //if A[i] != i+1` and A[A[i]-1] != A[i]
          //BUG: we need to make sure that the first comparison is in the front.
          if (nums[i] != i+1 && nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
            //swap
            //BUG: Have done this multiple times, when there are nums[i] as index, we need to make sure that the
            //     index is the last one changed.
            int temp  = nums[nums[i]-1];
            nums[nums[i]-1] = nums[i];
            nums[i] = temp;
          } else {
            i++;
          }
        }
        //second iteration: see who is the first missing one.
        for (i  = 0; i < nums.length; i++) {
          if (nums[i] != i+1) {
            return i+1;
          }
        }
        return nums.length+1;

    }
}
