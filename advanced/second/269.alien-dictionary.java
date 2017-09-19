/*
 * [269] Alien Dictionary
 *
 * https://leetcode.com/problems/alien-dictionary
 *
 * algorithms
 * Hard (24.41%)
 * Total Accepted:    29.2K
 * Total Submissions: 119.7K
 * Testcase Example:  '["wrt","wrf","er","ett","rftt"]'
 *
 * 
 * There is a new alien language which uses the latin alphabet. 
 * However, the order among letters are unknown to you. 
 * You receive a list of non-empty words from the dictionary, where words are
 * sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 * 
 * 
 * 
 * Example 1:
 * Given the following words in dictionary,
 * 
 * [
 * ⁠ "wrt",
 * ⁠ "wrf",
 * ⁠ "er",
 * ⁠ "ett",
 * ⁠ "rftt"
 * ]
 * 
 * 
 * 
 * The correct order is: "wertf".
 * 
 * 
 * Example 2:
 * Given the following words in dictionary,
 * 
 * [
 * ⁠ "z",
 * ⁠ "x"
 * ]
 * 
 * 
 * 
 * The correct order is: "zx".
 * 
 * 
 * Example 3:
 * Given the following words in dictionary,
 * 
 * [
 * ⁠ "z",
 * ⁠ "x",
 * ⁠ "z"
 * ]
 * 
 * 
 * 
 * The order is invalid, so return "".
 * 
 * Note:
 * 
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in
 * the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is
 * fine.
 * 
 * 
 */
class Solution {
	public String alienOrder(String[] words) {
		if (words.length == 0) {
			return "";
		} else if (words.length == 1) {
			return words[0];
		}
		Map<Character, Integer> inDegree = new HashMap<>();
		Map<Character,List<Character>> adj = new HashMap<>();
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				if (!inDegree.containsKey(word.charAt(i))) {
					inDegree.put(word.charAt(i),0);
				}
			}
		}
		for(int i = 1; i < words.length; i++) {
			char[] pair = compareWords(words[i-1],words[i]);
			char smaller = pair[0];
			char larger = pair[1];
			if (smaller != larger) {
				inDegree.put(larger,inDegree.getOrDefault(larger,0)+1);
				List<Character> list = adj.getOrDefault(smaller, new ArrayList<>());
				list.add(larger);
				adj.put(smaller, list);
			}
		}
		StringBuilder sb = new StringBuilder();
		Deque<Character> queue =  new ArrayDeque<>();
		for(char c : inDegree.keySet()) {
			if (inDegree.get(c) == 0) {
				queue.add(c);
			}
		}
		while(!queue.isEmpty()) {
			char front = queue.remove();
			sb.append(front);
			if (adj.containsKey(front)) {
				for (char next : adj.get(front)) {
					inDegree.put(next, inDegree.get(next) - 1);
					if (inDegree.get(next) == 0) {
						queue.add(next);
					}
				}
			}
		}
		if (sb.length() != inDegree.keySet().size()) {
			return "";
		} else {
			return sb.toString();
		}
	}
    //util functions
	private char[] compareWords(String smaller, String larger) {
		char[] ret = new char[2];
		for (int i = 0; i < smaller.length(); i++) {
			if (smaller.charAt(i)!= larger.charAt(i)) {
				ret[0] = smaller.charAt(i);
				ret[1] = larger.charAt(i);
				return ret;
			}
		}
		return ret;
	}
}
