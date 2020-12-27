/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (27.16%)
 * Total Accepted:    492.6K
 * Total Submissions: 1.8M
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*' where: 
 * 
 * 
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore,
 * it matches "aab".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a
 * previous valid character to match.
 * 
 * 
 */
class Solution {
    public boolean isMatch(String s, String p) {
       boolean[][] dp = new boolean[s.length()+1][p.length()+1];
       dp[0][0] = true;
       for (int j = 1; j <= p.length(); j++) {
           if(p.charAt(j-1) == '*') {
               dp[0][j] = dp[0][j-2];
           }
       }
       for (int i = 1; i <=s.length(); i++) {
           for (int j = 1; j <= p.length(); j++) {
               char sc = s.charAt(i-1);
               char pc = p.charAt(j-1);
               if (pc == '.') {
                   dp[i][j] = dp[i-1][j-1];
               } else if (pc == '*') {
                   char pp = p.charAt(j-2);
                   dp[i][j] |= dp[i][j-2];
                   if (sc == pp || pp == '.') {
                       dp[i][j] |= dp[i-1][j];
                   }
               } else {
                   dp[i][j] = dp[i-1][j-1] && (sc == pc);
               }
           }
       }
       return dp[s.length()][p.length()];
    }
}
