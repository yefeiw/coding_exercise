/*
* [10] Regular Expression Matching
*
* https://leetcode.com/problems/regular-expression-matching
*
* algorithms
* Hard (24.22%)
* Total Accepted:    161.1K
* Total Submissions: 665.2K
* Testcase Example:  '"aa"\n"a"'
*
* Implement regular expression matching with support for '.' and '*'.
*
*
* '.' Matches any single character.
* '*' Matches zero or more of the preceding element.
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
* isMatch("aa", "a*") → true
* isMatch("aa", ".*") → true
* isMatch("ab", ".*") → true
* isMatch("aab", "c*a*b") → true
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
    for (int i = 1; i <=n; i++) {
      if (p.charAt(i-1) == '*') {
        dp[0][i] = dp[0][i-2];
      }
    }

    //main recursion
    for (int i = 1; i <= m ; i++) {
      for (int j = 1; j <= n; j++) {
        if (p.charAt(j-1) == '*') {
          //* match
          //firstly if there are zero occurences
          if (j > 1) dp[i][j] |= dp[i][j-2];
          //secondly if there are one or more occurences
          if (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {
            dp[i][j] |= dp[i-1][j];
          }
        } else  {
          if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
            dp[i][j] |= dp[i-1][j-1];
          }
        }
      }
    }

    //return
    return dp[m][n];

  }
}
