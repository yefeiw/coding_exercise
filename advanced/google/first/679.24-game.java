/*
 * [679] 24 Game
 *
 * https://leetcode.com/problems/24-game/description/
 *
 * algorithms
 * Hard (38.85%)
 * Total Accepted:    4.4K
 * Total Submissions: 11.2K
 * Testcase Example:  '[4,1,8,7]'
 *
 *
 * You have 4 cards each containing a number from 1 to 9.  You need to judge
 * whether they could operated through *, /, +, -, (, ) to get the value of
 * 24.
 *
 *
 * Example 1:
 *
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 *
 *
 *
 * Example 2:
 *
 * Input: [1, 2, 1, 2]
 * Output: False
 *
 *
 *
 * Note:
 *
 * The division operator / represents real division, not integer division.  For
 * example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers.  In particular, we cannot use -
 * as a unary operator.  For example, with [1, 1, 1, 1] as input, the
 * expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together.  For example, if the input is [1,
 * 2, 1, 2], we cannot write this as 12 + 12.
 *
 *
 *
 */
class Solution {
    public boolean judgePoint24(int[] nums) {
        return backtrackHelper(nums,new boolean[4],0);
    }


    private boolean backtrackHelper(int[] nums, boolean[] used, double curValue) {
     int key = decode(used);
     //System.out.println("key="+key);
     if (key == 0xF)  {
       return Math.abs(curValue - 24) < 0.001;
     }

    for (int i = 0;i < 4; i++) {
      if (used[i]) {
        continue;
      }

      used[i] = true;
      //need to try each operations
      if (key == 0)  {
        boolean result = backtrackHelper(nums,used,nums[i]);
        if (result == true) return true;
      } else {
        boolean result = false;
        result |= backtrackHelper(nums, used, curValue + nums[i]);
        result |= backtrackHelper(nums, used, curValue - nums[i]);
        result |= backtrackHelper(nums, used, nums[i] - curValue);
        result |= backtrackHelper(nums, used, curValue * nums[i]);
        result |= backtrackHelper(nums, used, curValue / nums[i]);
        if (curValue != 0) {
            result |= backtrackHelper(nums, used, nums[i] / curValue);
        }
        if (result == true) {
          return true;
        }
      }
      used[i] = false;
    }
    //reaching here means there are no true values found;
    return false;
  }

  private int decode(boolean[] used) {
      int ret = 0;
      for (int i = 0; i < used.length; i++) {
          ret *= 2;
          if (used[i] == true) ret += 1;
      }
      return ret;
  }
}
