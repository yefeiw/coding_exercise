/*
 * [204] Count Primes
 *
 * https://leetcode.com/problems/count-primes
 *
 * algorithms
 * Easy (26.65%)
 * Total Accepted:    137K
 * Total Submissions: 513.8K
 * Testcase Example:  '0'
 *
 * Description:
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Credits:Special thanks to @mithmatt for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,true);
        for (int i = 2; i <n; i++) {
            if (isPrime[i]) {
                for (int j = 2; i * j <= n; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
        int ret = 0;
        for (int i = 2; i <n; i++) {
            if(isPrime[i]) ret++;
        }

        return ret;
    }
}
