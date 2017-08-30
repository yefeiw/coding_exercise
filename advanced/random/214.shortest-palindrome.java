/*
 * [214] Shortest Palindrome
 *
 * https://leetcode.com/problems/shortest-palindrome
 *
 * algorithms
 * Hard (24.24%)
 * Total Accepted:    42.1K
 * Total Submissions: 173.6K
 * Testcase Example:  '"aacecaaa"'
 *
 * 
 * Given a string S, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * 
 * For example: 
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 * 
 * Credits:Special thanks to @ifanchu for adding this problem and creating all
 * test cases. Thanks to @Freezen for additional test cases.
 */
class Solution {
    public String shortestPalindrome(String s) {
    	String buffer = s + "#" + new StringBuilder(s).reverse().toString();
        int[] prefix = new int[buffer.length()];
        int border = 0;
        for(int i = 1; i < prefix.length; i++) {
        	while(border > 0 && buffer.charAt(i) != buffer.charAt(border)) {
        		border = prefix[border -1];
        	}
        	if (buffer.charAt(i) == buffer.charAt(border)) {
        		border++;
        	} else {
        		border = 0;
        	}
        	prefix[i] = border;
        }
        for(int i = 0; i < prefix.length; i++) {
        	System.out.printf("%d ",prefix[i]);
        }
        int lastValue =  prefix[prefix.length-1];
        return new StringBuilder(s.substring(lastValue)).reverse().toString() + s;
    }
}
