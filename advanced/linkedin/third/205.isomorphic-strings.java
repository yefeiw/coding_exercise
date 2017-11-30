/*
 * [205] Isomorphic Strings
 *
 * https://leetcode.com/problems/isomorphic-strings
 *
 * algorithms
 * Easy (34.27%)
 * Total Accepted:    121K
 * Total Submissions: 353K
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
        Map<Character, Character> forwardIndex = new HashMap();
        Map<Character, Character> reverseIndex = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char value = t.charAt(i);

            if (forwardIndex.containsKey(key)) {
                if (forwardIndex.get(key) != value) {
                    return false;
                }
            } else {
                if (reverseIndex.containsKey(value)) {
                    return false;
                }

                forwardIndex.put(key,value);
                reverseIndex.put(value,key);
            }
        }

        return true;
    }
}
