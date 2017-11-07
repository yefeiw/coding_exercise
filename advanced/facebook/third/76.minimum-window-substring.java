/*
 * [76] Minimum Window Substring
 *
 * https://leetcode.com/problems/minimum-window-substring
 *
 * algorithms
 * Hard (26.03%)
 * Total Accepted:    122.5K
 * Total Submissions: 470.6K
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
        if (s.length()  == 0 || t.length() == 0) {
            return "";
        }
        int[] map = new int[256];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        };
        String cand = "";
        int length = Integer.MAX_VALUE;
        int satisfied = t.length();
        //sliding windows two points approach
        int fast = 0; int slow = 0;
        while(fast < s.length()) {
            while(fast < s.length() && satisfied > 0) {
                if (map[s.charAt(fast)] > 0) {
                    satisfied--;
                }
                map[s.charAt(fast)]--;
                fast++;
            }
            while(slow <=fast && satisfied == 0) {
                if (fast - slow < length) {
                    cand = s.substring(slow, fast);
                    length = fast - slow;
                }
                if (map[s.charAt(slow)] == 0) {
                    satisfied ++;
                }
                map[s.charAt(slow)]++;
                slow++;
            }
        }

        return cand;

    }
}
