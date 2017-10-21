/*
 * [548] Split Array with Equal Sum
 *
 * https://leetcode.com/problems/split-array-with-equal-sum
 *
 * algorithms
 * Medium (36.83%)
 * Total Accepted:    3K
 * Total Submissions: 8.1K
 * Testcase Example:  '[1,2,1,2,1,2,1]'
 *
 *
 * Given an array with n integers, you need to find if there are triplets  (i,
 * j, k) which satisfies following conditions:
 *
 * ⁠0 < i, i + 1 < j, j + 1 < k < n - 1
 * ⁠Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n -
 * 1) should be equal.
 *
 * where we define that subarray (L, R) represents a slice of the original
 * array starting from the element indexed L to the element indexed R.
 *
 *
 * Example:
 *
 * Input: [1,2,1,2,1,2,1]
 * Output: True
 * Explanation:
 * i = 1, j = 3, k = 5.
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) = 1
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 *
 *
 *
 * Note:
 *
 * ⁠1 <= n <= 2000.
 * ⁠Elements in the given array will be in range [-1,000,000, 1,000,000].
 *
 */
class Solution {
    public boolean splitArray(int[] nums) {
         if (nums.length < 7) {
           return false;
         }
         //pseudo-contants
         int m = nums.length;
         int[] prefixSum = new int[m];
         computePrefixSum(nums,prefixSum);
         //idea, instead of searching for one position, we need a set with multiple positions.
         for (int j = 3; j < m-3; j++) {
           //assume j is the point
           Set<Integer> sums = new HashSet<>();
           for(int i = 1; i < j-1; i++) {
             if (prefixSum[j - 1] - prefixSum[i] == prefixSum[i-1]) {
               //found i, add it to the set
               sums.add(prefixSum[i-1]);
             }
           }
           for(int k = j+1; k < m-1; k++) {
             if (prefixSum[m-1] - prefixSum[k] == prefixSum[k-1] - prefixSum[j] && sums.contains(prefixSum[m-1] - prefixSum[k])) {
               //found one such element
               return true;
             }
           }
         }
         //reaching here means nothing is found, return false;
         return false;
    }
    //util functions
    void computePrefixSum(int[] input, int[] output) {
      int rollingSum = 0;
      for (int i = 0; i < input.length; i++) {
        rollingSum += input[i];
        output[i] += rollingSum;
      }
    }
}
