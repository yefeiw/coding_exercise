/*
 * [313] Super Ugly Number
 *
 * https://leetcode.com/problems/super-ugly-number/description/
 *
 * algorithms
 * Medium (38.96%)
 * Total Accepted:    49.6K
 * Total Submissions: 127.3K
 * Testcase Example:  '12\n[2,7,13,19]'
 *
 * Write a program to find the nth super ugly number.
 * 
 * Super ugly numbers are positive numbers whose all prime factors are in the
 * given prime list primes of size k.
 * 
 * Example:
 * 
 * 
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32 
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first
 * 12 
 * ⁠            super ugly numbers given primes = [2,7,13,19] of size 4.
 * 
 * Note:
 * 
 * 
 * 1 is a super ugly number for any given primes.
 * The given numbers in primes are in ascending order.
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 * 
 * 
 */
class Solution {
    /**
     * This problem maps to the ugly number problem. 
     * The idea is exactly the same except that this time we have a variable list of length.
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        //which number is the current smallest.
       int[] dp = new int[n];
       // the number of each ugly prime factor.
       int[] idxPrime = new int[primes.length];
       dp[0] = 1;
       
       for (int i = 1; i < n; i++) {
           int min = Integer.MAX_VALUE;
           for(int j = 0; j < primes.length; j++) {
               if (primes[j] * dp[idxPrime[j]] < min) {
                   min = primes[j] * dp[idxPrime[j]];
               }
           }
           //Here the min is determined.
           dp[i] = min;
           //Find which number contributed to the min and advance it.
           for (int j = 0; j < primes.length; j++) {
               if (primes[j] * dp[idxPrime[j]] == min) {
                   idxPrime[j]++;
               }
           }
       }

       //In the end, we still want the last recorded ugly number
       return dp[n-1];
    }
}
