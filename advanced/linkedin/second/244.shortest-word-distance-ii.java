/*
 * [244] Shortest Word Distance II
 *
 * https://leetcode.com/problems/shortest-word-distance-ii
 *
 * algorithms
 * Medium (38.23%)
 * Total Accepted:    23.6K
 * Total Submissions: 61.7K
 * Testcase Example:  '["WordDistance","shortest","shortest"]\n[[["practice","makes","perfect","coding","makes"]],["coding","practice"],["makes","coding"]]'
 *
 * This is a follow up of Shortest Word Distance. The only difference is now
 * you are given the list of words and your method will be called repeatedly
 * many times with different parameters. How would you optimize it?
 *
 * Design a class which receives a list of words in the constructor, and
 * implements a method that takes two words word1 and word2 and return the
 * shortest distance between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
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
class WordDistance {
    Map<String, List<Integer>> map;

    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String key = words[i];
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            List<Integer> list = map.get(key);
            list.add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int idx1 = 0;
        int idx2 = 0;

        int ret = Integer.MAX_VALUE;

        while(idx1 < list1.size() && idx2 < list2.size()) {
            int pos1 = idx1 < list1.size() ? list1.get(idx1) : list1.get(list1.size() -1);
            int pos2 = idx2 < list2.size() ? list2.get(idx2) : list2.get(list2.size() -1);
            ret = Math.min(ret, Math.abs(pos1 - pos2));
            if (pos1 < pos2) {
                idx1++;
            } else {
                idx2++;
            }
        }
        return ret;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
