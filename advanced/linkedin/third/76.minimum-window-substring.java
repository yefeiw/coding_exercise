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
        int fast = 0;
        int slow = 0;
        int minLength = Integer.MAX_VALUE;

        int m = s.length();
        int n = t.length();

        String ret = "";

        int[] map = new int[256];
        for(int i = 0; i < n; i++) {
            map[t.charAt(i)]++;
        }
        int required = n;
        while(fast < m) {
            while(fast < m && required > 0) {
                if (map[s.charAt(fast)] > 0) {
                    required--;
                }
                map[s.charAt(fast)]--;
                fast++;
            }
            while(slow <= fast && required == 0){
                int length = fast - slow;
                if (length < minLength) {
                    ret = s.substring(slow, fast);
                    minLength = length;
                }
                if (map[s.charAt(slow)] == 0) {
                    required++;
                }
                map[s.charAt(slow)]++;
                slow++;
            }
        }

        return ret;

    }
}
