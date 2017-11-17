/*
 * [698] Partition to K Equal Sum Subsets
 *
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets
 *
 * algorithms
 * Medium (36.98%)
 * Total Accepted:    5.7K
 * Total Submissions: 15.4K
 * Testcase Example:  '[4,3,2,3,5,2,1]\n4'
 *
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into k non-empty subsets whose sums are all
 * equal.
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3),
 * (2,3) with equal sums.
 *
 *
 *
 * Note:
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 *
 */
class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
   public boolean canPartitionKSubsets(int[] nums, int k) {
       if (nums == null || nums.length == 0 || k == 0) return false;
       Arrays.sort(nums);
       int sum = 0;
       for (int num : nums) sum += num;
       if (sum % k != 0 || sum < k) return false;
       sum = sum / k;
       return possible(nums, sum, new int[k], nums.length - 1);
   }

   boolean possible(int[] nums, int sum, int[] p, int idx) {
       //System.out.println(idx);
       if (idx == -1) {
           // for (int s : p) System.out.print(s + " ");
           //System.out.println();
           for (int s : p) if (s != sum) return false;
           return true;
       }

       int num = nums[idx];

       for (int i = 0; i < p.length; i++) {
           if (p[i] + num <= sum) {
               p[i] += num;
               if (possible(nums, sum, p, idx - 1)) return true;
               p[i] -= num;
           }
       }
       return false;
   }
}
