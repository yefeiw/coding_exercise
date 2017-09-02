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
 */ 

public class WordDictionary {
public class TrieNode {
	public Map<Character, TrieNode> children;
	public boolean isEnd;
	public TrieNode () {
		this.children =  new HashMap<Character, TrieNode>();
		this.isEnd = false;
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
		for (int i =0 ; i < word.length(); i++) {
			char cand = word.charAt(i);
			if (!iter.children.containsKey(cand)) {
				iter.children.put(cand, new TrieNode());
			}
			iter =  iter.children.get(cand);
		}
		iter.isEnd = true;
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		return dfsHelper(word, 0, root);
	}

	private boolean dfsHelper(String word, int pos, TrieNode iter) {
		if (pos >= word.length()) {
			return iter.isEnd;
		}
		char cand = word.charAt(pos);
		if (cand == '.') {
			for (char c : iter.children.keySet()) {
				if (dfsHelper(word, pos+1, iter.children.get(c))) {
					return true;
				}
			}
			return false;
		} else {
			if (!iter.children.containsKey(cand)) {
				return false;
			}
			return dfsHelper(word, pos+1, iter.children.get(cand));
		}
	}
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
