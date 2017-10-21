/*
 * [127] Word Ladder
 *
 * https://leetcode.com/problems/word-ladder
 *
 * algorithms
 * Medium (19.46%)
 * Total Accepted:    132.6K
 * Total Submissions: 681.5K
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
        if (wordList.size() == 0) {
        	return 0;
        }
        Set<String> dict = new HashSet<String>();
        for(String word : wordList) {
        	dict.add(word);
        }
        //set for visted word
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        //BUGWARNING; dont' EVER add endword to the dict!!!!!!!
        int len = 1;
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		String front = queue.remove();
        		if (front.equals(endWord)) {
        			return len;
        		}
        		addNeighbors(front.toCharArray(), dict, visited, queue,endWord);
        		
        	}
        	len++;
        }
        //reaching here means it's not found.
        return 0;
    }
    //util functions
    private void addNeighbors(char[] front, Set<String> dict, Set<String> visited, Deque<String> queue, String endWord) {
    	for (int i = 0; i < front.length; i++) {
    		char temp = front[i];
    		for(char c = 'a'; c <='z'; c++) {
    			if (c == temp) continue;
    			front[i] = c;
    			String key = new String(front);
    			if (visited.contains(key)) continue;
    			if (dict.contains(key)) {
    				visited.add(key);
    				queue.add(key);
    			}
    		}
    		front[i] = temp;
    	}
    }
}
