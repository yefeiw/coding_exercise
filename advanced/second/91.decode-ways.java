/*
 * [91] Decode Ways
 *
 * https://leetcode.com/problems/decode-ways
 *
 * algorithms
 * Medium (19.63%)
 * Total Accepted:    129.4K
 * Total Submissions: 659.3K
 * Testcase Example:  '""'
 *
 * 
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 
 * 
 * Given an encoded message containing digits, determine the total number of
 * ways to decode it.
 * 
 * 
 * 
 * For example,
 * Given encoded message "12",
 * it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * 
 * 
 * The number of ways decoding "12" is 2.
 * 
 */
class Solution {
	public int numDecodings(String s) {
		if (s.length() == 0) {
			return s.length();
		}
        //dp[i]; decode ways from 0 to i
		int[] dp = new int [s.length()+1];
		dp[0] = 1;
		for(int i = 1; i <=s.length(); i++) {
			if (s.charAt(i-1) != '0')dp[i] = dp[i-1];
			if (i > 1) {
				int cand = (s.charAt(i-2) - '0')*10+(s.charAt(i-1) - '0');
				if (cand >= 10 && cand <= 26) {
					dp[i] += dp[i-2];
				}
			}
		}
		return dp[s.length()];
	}
}
