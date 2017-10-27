/*
 * [127] Word Ladder
 *
 * https://leetcode.com/problems/word-ladder
 *
 * algorithms
 * Medium (19.57%)
 * Total Accepted:    139.5K
 * Total Submissions: 712.8K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 *
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 *
 *
 *
 * For example,
 *
 *
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 *
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 *
 *
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 *
 *
 *
 * UPDATE (2017/1/20):
 * The wordList parameter had been changed to a list of strings (instead of a
 * set of strings). Please reload the code definition to get the latest
 * changes.
 *
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty()) {
            return 0;
        }

        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }

        Deque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        int levelCnt = 1;
        Set<String> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String front = queue.remove();
                StringBuilder sb = new StringBuilder(front);
                for(int j = 0; j < front.length(); j++) {
                    char curChar = front.charAt(j);
                    for (char substitute = 'a'; substitute <= 'z'; substitute++) {
                        if (substitute == curChar) {
                            continue;
                        }
                        sb.setCharAt(j, substitute);

                        if (set.contains(sb.toString()) && !visited.contains(sb.toString())) {
                            //System.out.println("adding " +sb.toString());
                            //BUG: each transformed word must appear in the dictionary except beginWord.
                            //Therefore, if the endWord is not in the dicionary we can't accept it.
                            //TODO: check it at the beginning of the code.
                            if (sb.toString().equals(endWord)) {
                                return levelCnt+1;
                            }
                            queue.add(sb.toString());
                            visited.add(sb.toString());
                        }
                    }
                    sb.setCharAt(j, curChar);
                }
            }
            levelCnt++;
        }
        //reaching here means that the queue is already empty but we still could not find anything.
        return 0;
    }
}
