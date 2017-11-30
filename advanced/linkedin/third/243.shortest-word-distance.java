/*
 * [243] Shortest Word Distance
 *
 * https://leetcode.com/problems/shortest-word-distance
 *
 * algorithms
 * Easy (52.96%)
 * Total Accepted:    37.5K
 * Total Submissions: 70.8K
 * Testcase Example:  '["a","b"]\n"a"\n"b"'
 *
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 *
 *
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are
 * both in the list.
 *
 */
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int ret = Integer.MAX_VALUE;
        int pos1 = -1;
        int pos2 = -1;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) pos1 = i;
            if (words[i].equals(word2)) pos2 = i;

            if(pos1 >= 0 && pos2 >= 0) {
                int cand = Math.abs(pos1 - pos2);
                if (cand < ret) {
                    ret = cand;
                }
            }
        }

        return ret;
    }
}
