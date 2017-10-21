/*
 * [44] Wildcard Matching
 *
 * https://leetcode.com/problems/wildcard-matching
 *
 * algorithms
 * Hard (20.21%)
 * Total Accepted:    100.7K
 * Total Submissions: 498.3K
 * Testcase Example:  '"aa"\n"a"'
 *
 * Implement wildcard pattern matching with support for '?' and '*'.
 *
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 *
 */
class Solution {
    public boolean isMatch(String s, String p) {
        //pseudo-contants
        int m = s.length();
        int n = p.length();

        //dynamic programming
        boolean[][] dp = new boolean[m+1][n+1];
        //initialize
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {

          if (p.charAt(j-1) == '*') {
            dp[0][j] |= dp[0][j-1];
          }
        }
        //main recursion
        for (int i = 1; i <= m; i++) {
          for (int j = 1; j <= n; j++) {
            if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) =='?') {
              dp[i][j] |= dp[i-1][j-1];
            } else if (p.charAt(j-1) == '*') {
              dp[i][j] |= dp[i-1][j];
              dp[i][j] |= dp[i][j-1];
            }
          }
        }

        return dp[m][n];
    }
}
