/*
 * [245] Shortest Word Distance III
 *
 * https://leetcode.com/problems/shortest-word-distance-iii
 *
 * algorithms
 * Medium (50.38%)
 * Total Accepted:    21.5K
 * Total Submissions: 42.6K
 * Testcase Example:  '["a","b"]\n"a"\n"b"'
 *
 * This is a follow up of Shortest Word Distance. The only difference is now
 * word1 could be the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in
 * the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 *
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 *
 *
 *
 * Note:
 * You may assume word1 and word2 are both in the list.
 *
 */
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
            if (!word1.equals(word2)) {
                return shortestDiffWordDistance(words,word1,word2);
            } else {
                return shortestSameWordDistance(words,word1);
            }
    }

    //util functions
    private int shortestDiffWordDistance (String[] words, String word1,String word2) {
        int pos1 = -1;
        int pos2 = -1;

        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            boolean found = false;
            if (words[i].equals(word1)) {
                pos1 = i;
                found = true;
            }
            if (words[i].equals(word2)) {
                pos2 = i;
                found = true;
            }

            if (found && pos1 >=0 && pos2 >= 0) {
                ret =Math.min(ret, Math.abs(pos1 - pos2));
            }
        }

        return ret;
    }

    private int shortestSameWordDistance(String[] words, String word) {
        int pos = -1;
        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                if (pos >= 0) {
                    ret = Math.min(ret, i - pos);
                }
                pos = i;
            }
        }

        return ret;
    }
}
