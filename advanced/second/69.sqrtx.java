/*
 * [69] Sqrt(x)
 *
 * https://leetcode.com/problems/sqrtx
 *
 * algorithms
 * Easy (27.90%)
 * Total Accepted:    172.6K
 * Total Submissions: 618.8K
 * Testcase Example:  '0'
 *
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 */
class Solution {
    public int mySqrt(int x) {
        if (x < 0) {
        	return -1;
        } else if (x < 2) {
        	return x;
        }
        int start = 1; int end = x;
        while(start < end -1) {
        	int mid = start + (end - start) / 2;
        	if (x / mid < mid) {
        		//mid * mid > x, mid is too large
        		end = mid;
        	} else {
        		start  = mid;
        	}
        }
        if (x / end >= end) {
        	return end;
        } else return start;
    }
}
