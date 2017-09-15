/*
 * [211] Add and Search Word - Data structure design
 *
 * https://leetcode.com/problems/add-and-search-word-data-structure-design
 *
 * algorithms
 * Medium (22.93%)
 * Total Accepted:    57.7K
 * Total Submissions: 251.8K
 * Testcase Example:  '["WordDictionary","addWord","addWord","addWord","search","search","search","search"]\n[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]'
 *
 * 
 * Design a data structure that supports the following two operations:
 * 
 * 
 * void addWord(word)
 * bool search(word)
 * 
 * 
 * 
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or .. A . means it can represent any one
 * letter.
 * 
 * 
 * For example:
 * 
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 
 * 
 * 
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * 
 * 
 * click to show hint.
 * 
 * You should be familiar with how a Trie works. If not, please work on this
 * problem: Implement Trie (Prefix Tree) first.
 * 
 */
class WordDictionary {

	class TrieNode {
		public Map<Character, TrieNode> map;
		public boolean isEnd;
		public TrieNode() {
			map = new HashMap<>();
			isEnd = false;
		}
	}

	TrieNode root;
	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode(); 
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode iter = root;
		for(int i = 0; i < word.length(); i++) {
			char key = word.charAt(i);
			if (!iter.map.containsKey(key)) {
				iter.map.put(key, new TrieNode());
			}
			iter = iter.map.get(key);
		}
		iter.isEnd = true;
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		return helper(word, 0, root); 
	}

    //util functions
	private boolean helper(String word, int pos, TrieNode root) {
		if (pos == word.length()) {
			return (root.isEnd);
		}
		TrieNode iter=  root;
		char key = word.charAt(pos);
		if (key == '.') {
			for (TrieNode cand : iter.map.values()) {
				if (helper(word, pos+1, cand) == true) {
					return true;
				}
			}
			return false;
		} else {
			if (!iter.map.containsKey(key)) {
				return false;
			}
			return helper(word, pos+1, iter.map.get(key));
		}


	}
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
