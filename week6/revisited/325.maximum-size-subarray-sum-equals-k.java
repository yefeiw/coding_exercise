/*
 * [325] Maximum Size Subarray Sum Equals k
 *
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k
 *
 * algorithms
 * Medium (42.57%)
 * Total Accepted:    34.7K
 * Total Submissions: 81.6K
 * Testcase Example:  '[1,-1,5,-2,3]\n3'
 *
 *
 * Given an array nums and a target value k, find the maximum length of a
 * subarray that sums to k. If there isn't one, return 0 instead.
 *
 *
 *
 * ⁠   Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit
 * signed integer range.
 *
 *
 *
 * ⁠   Example 1:
 *
 *
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the
 * longest)
 *
 *
 *
 * ⁠   Example 2:
 *
 *
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 *
 *
 *
 * ⁠   Follow Up:
 * ⁠   Can you do it in O(n) time?
 *
 */
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int[] prefixSum = new int[nums.length];
        int rollingSum = 0;
        //build prefix sum array;
        for(int i = 0; i < nums.length; i++) {
          rollingSum += nums[i];
          prefixSum[i] = rollingSum;
        }
        //return value
        int ret = 0;
        //setup HashMap for index and run for index;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for (int i = 0; i < nums.length; i++) {
          if (map.containsKey(prefixSum[i] - k)){
              ret = Math.max(ret, i - map.get(prefixSum[i] - k));
          }
          if (!map.containsKey(prefixSum[i])) {
            map.put(prefixSum[i],i);
          }
        }
        return ret;
    }
}
