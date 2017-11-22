/*
 * [647] Palindromic Substrings
 *
 * https://leetcode.com/problems/palindromic-substrings
 *
 * algorithms
 * Medium (55.75%)
 * Total Accepted:    21.5K
 * Total Submissions: 38.6K
 * Testcase Example:  '"abc"'
 *
 *
 * Given a string, your task is to count how many palindromic substrings in
 * this string.
 *
 *
 *
 * The substrings with different start indexes or end indexes are counted as
 * different substrings even they consist of same characters.
 *
 *
 * Example 1:
 *
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *
 *
 * Example 2:
 *
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 *
 * Note:
 *
 * The input string length won't exceed 1000.
 *
 *
 */
class Solution {
    public int countSubstrings(String s) {
            if (s.length() == 0) {
                return 0;
            }
            //idea: dynamic programming
            //dp[i][j] -> substring(i,j+1) is palindrome;
            boolean[][] dp = new boolean[s.length()][s.length()];
            //cnt -> the returning value, how many palindromes exist
            int cnt = 0;
            for (int i = s.length() -1; i>=0; i--) {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i < 3 || dp[i+1][j-1] == true) {
                            dp[i][j] = true;
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
    }
}
