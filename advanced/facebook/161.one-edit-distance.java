/*
 * [161] One Edit Distance
 *
 * https://leetcode.com/problems/one-edit-distance
 *
 * algorithms
 * Medium (31.23%)
 * Total Accepted:    36.1K
 * Total Submissions: 115.7K
 * Testcase Example:  '""\n""'
 *
 * Given two strings S and T, determine if they are both one edit distance
 * apart.
 */
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        for (int i = 0; i < Math.min(m,n); i++) {
        	if (s.charAt(i) != t.charAt(i)) {
        		if (m == n) return (s.substring(i+1).equals(t.substring(i+1)));
        		else  if (m < n) {
        			return (s.substring(i).equals(t.substring(i+1)));
        		} else {
        			return (s.substring(i+1).equals(t.substring(i)));
        		}
        	}
        }
        return Math.abs(m-n) == 1;
    }
}
