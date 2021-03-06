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
if (x == 0.0) {
        	if (n  < 1) {
        		return Double.MAX_VALUE;
        	} else {
        		return 0;
        	}
        }
        if (n == 0) return 1;
        //caution: negatives
        boolean negate = n < 0;
        if (negate) {
        	n = -n;
        }
        double result=  helper(x, n);
        if (negate) {
        	return 1/result;
        } else {
        	return result;
        }
    }

    private double helper(double x, int n) {
        if (n == 0) {
            if (x == 0) return -1;
            else return 1;
        }

        double buffer = helper(x,n/2);

        if (n % 2 == 0) {
            return buffer * buffer;
        } else {
            return buffer * buffer*x;
        }

    }
}
