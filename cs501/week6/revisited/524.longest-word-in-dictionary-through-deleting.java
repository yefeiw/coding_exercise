/*
 * [524] Longest Word in Dictionary through Deleting
 *
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting
 *
 * algorithms
 * Medium (42.84%)
 * Total Accepted:    13.3K
 * Total Submissions: 31K
 * Testcase Example:  '"abpcplea"\n["ale","apple","monkey","plea"]'
 *
 *
 * Given a string and a string dictionary, find the longest string in the
 * dictionary that can be formed by deleting some characters of the given
 * string. If there are more than one possible results, return the longest word
 * with the smallest lexicographical order. If there is no possible result,
 * return the empty string.
 *
 * Example 1:
 *
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 *
 *
 *
 *
 * Example 2:
 *
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 *
 *
 *
 * Note:
 *
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 *
 *
 */
class Solution {
    public String findLongestWord(String s, List<String> d) {
        String ret = "";
        for (String cand : d) {
          if (isSubsequence(cand,s)) {
            if (cand.length() > ret.length() || (cand.length() == ret.length() && ret.compareTo(cand) > 0)) {
              ret = cand;
            }
          }
        }
        return ret;
    }

    private boolean isSubsequence(String cand, String target) {
      int j = 0;
      for (int i = 0; j < cand.length() && i < target.length(); i++) {
        if ( cand.charAt(j) == target.charAt(i)) {
          j++;
        }
      }
      return (j == cand.length());
    }
}
