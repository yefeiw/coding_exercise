/*
 * [161] One Edit Distance
 *
 * https://leetcode.com/problems/one-edit-distance
 *
 * algorithms
 * Medium (31.31%)
 * Total Accepted:    37.4K
 * Total Submissions: 119.4K
 * Testcase Example:  '""\n""'
 *
 * Given two strings S and T, determine if they are both one edit distance
 * apart.
 */
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (Math.abs(m-n) > 1) return false;
        StringBuilder longer = (m > n) ? new StringBuilder(s) : new StringBuilder(t);
        StringBuilder shorter = (m > n) ? new StringBuilder(t) : new StringBuilder(s);
        int numEdit = 0;
        for(int i = 0; i < shorter.length(); i++) {
        	if (longer.charAt(i) != shorter.charAt(i)) {
        		//if this is the second diff, quit.
        		if (numEdit > 0) {
        			return false;
        		}
        		//if the lengths are equal, replace longer with shorter
        		if (longer.length() == shorter.length()) {
        			longer.setCharAt(i,shorter.charAt(i));
        		} else {
        		//else, remove i from longer
        			longer.deleteCharAt(i);
        			//BUGWARNING: don't advance shorter
        			i--;
        		}
        		numEdit++;
        	}
        }
        if (numEdit == 0 && longer.length() > shorter.length()) {
        	return true;
        }
        if (numEdit == 1 && longer.length() == shorter.length()) {
        	return true;
        }
        return false;
    }
}
