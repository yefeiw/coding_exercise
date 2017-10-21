/*
 * [80] Remove Duplicates from Sorted Array II
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii
 *
 * algorithms
 * Medium (36.13%)
 * Total Accepted:    126K
 * Total Submissions: 348.8K
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
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
          return 0;
        }
        int j = 1;
        int cnt = 0;
        for (int i = 1; i< nums.length; i++) {
          if ((i > 0) && nums[i] == nums[i-1]) {
            cnt++;
          } else {
            cnt = 0;
          }
          //BUG: key point here is to make sure that assignments happens before incrementing j
          nums[j] = nums[i];
          if (cnt < 2) {
            j++;
          }

        }

        return j;
    }
}
