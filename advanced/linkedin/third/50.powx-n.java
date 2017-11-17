/*
 * [50] Pow(x, n)
 *
 * https://leetcode.com/problems/powx-n
 *
 * algorithms
 * Medium (26.25%)
 * Total Accepted:    166K
 * Total Submissions: 632.3K
 * Testcase Example:  '8.88023\n3'
 *
 * Implement pow(x, n).
 *
 */
class Solution {
    public double myPow(double x, int n) {
        double base = x;
        double result =1;
        if (n < 0) {
            base = 1/base;
        }
        //BUG: what this is going todo?
        if (n == Integer.MIN_VALUE) {
            n++;
            result = base;
        }
        n = Math.abs(n);
        while(n > 0) {
            if (n % 2 != 0) {
                result = result * base;
            }
            base *= base;
            n /= 2;
        }

        return result;
    }
}
