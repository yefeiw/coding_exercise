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

        int[] map = new int[256];
        int required = t.length();

        for(int i  = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        int start = 0;
        int end = 0;
        String ret = "";
        int minLength = Integer.MAX_VALUE;
        while(end < s.length()) {
            while(end < s.length() && required > 0) {
                char cand = s.charAt(end);
                if (map[cand] > 0) {
                    required--;
                }
                end++;
                map[cand]--;
            }
            while(start <= end && required == 0) {
                char cand = s.charAt(start);
                if(map[cand] == 0) {
                    int curLength = end - start;
                    if (curLength < minLength) {
                        minLength = curLength;
                        ret = s.substring(start,end);
                    }
                    required++;
                }
                start++;
                map[cand]++;
            }
        }
        return ret;

    }
}
