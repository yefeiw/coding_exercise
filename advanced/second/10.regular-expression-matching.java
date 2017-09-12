/*
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching
 *
 * algorithms
 * Hard (24.11%)
 * Total Accepted:    151.5K
 * Total Submissions: 628.3K
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
        if (s.length() == 0 || p.length() == 0) {
        	//TBD: visit it later
        	return false;
        }
        int m = s.length();
        int n = p.length();
        boolean dp[][] = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int j = 1; j <= n ; j++) {
        	if(p.charAt(j-1) =='*') {
        		dp[0][j] = dp[0][j-2];
        	}
        }
        for(int i = 1; i <= m; i++) {
        	for (int j = 1; j <= n; j++) {
        		if (s.charAt(i-1) == p.charAt(j-1)||p.charAt(j-1) =='.') {
        			dp[i][j] |= dp[i-1][j-1];
        		} else if (p.charAt(j-1) == '*') {
        			if (s.charAt(i-1) == p.charAt(j-2)|| p.charAt(j-2) == '.') {
        				dp[i][j] |= dp[i][j-2];
        			} else if (dp[i-1][j] == true) dp[i][j] = true;
        		} else {
        			//do nothing	
        		}

        	}
        }
        return dp[m][n];

    }
}
