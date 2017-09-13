/*
 * [76] Minimum Window Substring
 *
 * https://leetcode.com/problems/minimum-window-substring
 *
 * algorithms
 * Hard (25.49%)
 * Total Accepted:    111.5K
 * Total Submissions: 437.3K
 * Testcase Example:  '"a"\n"a"'
 *
 * 
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * 
 * 
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * 
 * 
 * Minimum window is "BANC".
 * 
 * 
 * 
 * Note:
 * If there is no such window in S that covers all characters in T, return the
 * empty string "".
 * 
 * 
 * If there are multiple such windows, you are guaranteed that there will
 * always be only one unique minimum window in S.
 * 
 */
class Solution {
	public String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		int[] map = new int[128];
		for(char c : t.toCharArray()) {
			map[c]++;
		}
		int begin = 0; int end = 0;
		int required = t.length();
		String ans ="";
		int minLength = Integer.MAX_VALUE;
		while(end < s.length()) {
			while(end < s.length() && required > 0){
				char c = s.charAt(end);
				if (map[c] > 0) {
					required --;
				}
				end++;
				map[c]--;
			}
			while(begin < end && required  == 0) {
				
				char c = s.charAt(begin);
				if (map[c] == 0) {
					if (end - begin < minLength) {
					minLength = end - begin;
					ans = s.substring(begin, end);
				}
					required++;
				}
				map[c]++;
				begin++;
			}

		}
		return ans;
	}
}
