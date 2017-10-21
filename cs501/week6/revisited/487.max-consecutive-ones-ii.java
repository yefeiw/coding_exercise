/*
 * [487] Max Consecutive Ones II
 *
 * https://leetcode.com/problems/max-consecutive-ones-ii
 *
 * algorithms
 * Medium (45.34%)
 * Total Accepted:    8.1K
 * Total Submissions: 17.9K
 * Testcase Example:  '[1,0,1,1,0,1]'
 *
 *
 * Given a binary array, find the maximum number of consecutive 1s in this
 * array if you can flip at most one 0.
 *
 *
 * Example 1:
 *
 * Input: [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the the maximum number of
 * consecutive 1s.
 * ⁠   After flipping, the maximum number of consecutive 1s is 4.
 *
 *
 *
 * Note:
 *
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed
 * 10,000
 *
 *
 *
 * Follow up:
 * What if the input numbers come in one by one as an infinite stream? In other
 * words, you can't store all numbers coming from the stream as it's too large
 * to hold in memory. Could you solve it efficiently?
 *
 */
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 0) {
          return 0;
        }
        int fast = 0;
        int slow = 0;

        boolean used = false;
        int pos = -1;
        int ret = 0;
        while(fast < nums.length) {
           if (nums[fast] == 1 || used ==  false) {
             if (nums[fast] == 0) {
               pos = fast;
               used = true;
             }
             fast++;
             //BUG: the reason for not adding one here is that, the ret is updated after calling fast++;
             ret = Math.max(ret, fast - slow);
           } else {
             slow = pos+1;
             used = false;
           }
        }
        return ret;
    }
}
