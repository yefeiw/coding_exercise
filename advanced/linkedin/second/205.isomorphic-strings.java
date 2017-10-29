/*
 * [205] Isomorphic Strings
 *
 * https://leetcode.com/problems/isomorphic-strings
 *
 * algorithms
 * Easy (34.14%)
 * Total Accepted:    117.9K
 * Total Submissions: 345.4K
 * Testcase Example:  '"egg"\n"add"'
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same
 * character but a character may map to itself.
 *
 * For example,
 * Given "egg", "add", return true.
 *
 * Given "foo", "bar", return false.
 *
 * Given "paper", "title", return true.
 *
 * Note:
 * You may assume both s and t have the same length.
 */
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() == 0) {
            return t.length() == 0;
        }
        Map<Character, Character> fwdIdx = new HashMap<>();
        Map<Character, Character> bwdIdx = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char first = s.charAt(i);
            char second = t.charAt(i);
            if (fwdIdx.containsKey(first) && fwdIdx.get(first) != second) {
                return false;
            }
            if (bwdIdx.containsKey(second) && bwdIdx.get(second) != first) {
                return false;
            }
            if (!fwdIdx.containsKey(first)) {
                fwdIdx.put(first, second);
            }
            if (!bwdIdx.containsKey(second)) {
                bwdIdx.put(second,first);
            }
        }
        return true;
    }
}
