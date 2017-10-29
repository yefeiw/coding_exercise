/*
 * [367] Valid Perfect Square
 *
 * https://leetcode.com/problems/valid-perfect-square
 *
 * algorithms
 * Easy (38.43%)
 * Total Accepted:    56.4K
 * Total Submissions: 146.7K
 * Testcase Example:  '16'
 *
 * Given a positive integer num, write a function which returns True if num is
 * a perfect square else False.
 *
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 *
 * Example 1:
 *
 * Input: 16
 * Returns: True
 *
 *
 *
 * Example 2:
 *
 * Input: 14
 * Returns: False
 *
 *
 *
 * Credits:Special thanks to @elmirap for adding this problem and creating all
 * test cases.
 */
class Solution {
    public boolean isPerfectSquare(int num) {
            int start  = 1;
            int end = num;
            while(start < end - 1) {
                int mid = start + (end - start) / 2;
                if ((double)mid > (double)num / mid) {
                    end = mid;
                } else if ((double)mid < (double)num / mid) {
                    start = mid;
                } else {
                    return true;
                }
            }
            if ((double)start == (double)num / start || (double)end == (double)num / end) {
                return true;
            } else {
                return false;
            }

    }
}
