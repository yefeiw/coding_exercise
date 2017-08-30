/*
 * [459] Repeated Substring Pattern
 *
 * https://leetcode.com/problems/repeated-substring-pattern
 *
 * algorithms
 * Easy (38.03%)
 * Total Accepted:    35.7K
 * Total Submissions: 93.8K
 * Testcase Example:  '"abab"'
 *
 * Given a non-empty string check if it can be constructed by taking a
 * substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only
 * and its length  will not exceed 10000. 
 * 
 * Example 1:
 * 
 * Input: "abab"
 * 
 * Output: True
 * 
 * Explanation: It's the substring "ab" twice.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "aba"
 * 
 * Output: False
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: "abcabcabcabc"
 * 
 * Output: True
 * 
 * Explanation: It's the substring "abc" four times. (And the substring
 * "abcabc" twice.)
 * 
 * 
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int[] prefix = new int[s.length()];
        int border = 0;
        for(int i = 1; i < prefix.length; i++) {
        	while(border > 0 && s.charAt(i) != s.charAt(border)) {
        		border = prefix[border -1];
        	}
        	if (s.charAt(i) == s.charAt(border)) {
        		border++;
        	} else {
        		border = 0;
        	}
        	prefix[i] = border;
        }
        for(int i = 0; i < prefix.length; i++) {
        	System.out.printf("%d ",prefix[i]);
        }
        return (prefix[s.length()-1] > 0 && (s.length() % (s.length() -prefix[s.length()-1]) == 0));
    }
}
