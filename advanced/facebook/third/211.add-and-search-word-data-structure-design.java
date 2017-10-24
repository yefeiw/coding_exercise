/*
 * [211] Add and Search Word - Data structure design
 *
 * https://leetcode.com/problems/add-and-search-word-data-structure-design
 *
 * algorithms
 * Medium (23.74%)
 * Total Accepted:    62.9K
 * Total Submissions: 265.2K
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
        public boolean isEnd;
        public Map<Character,TrieNode> children;
        public TrieNode() {
            this.isEnd = false;
            this.children  = new HashMap<>();
        }
    }
    //data structure;
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode iter = root;
        for (int i = 0;i < word.length(); i++) {
            char c  = word.charAt(i);
            if (!iter.children.containsKey(c)) {
                iter.children.put(c, new TrieNode());
            }
            iter = iter.children.get(c);
        }
        iter.isEnd =  true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfsHelper(root, word, 0);
    }

    //util functions
    private boolean dfsHelper(TrieNode iter, String word, int pos) {
        if (pos >= word.length()) {
            return iter.isEnd;
        }
        char c = word.charAt(pos);
        if (c == '.') {
            boolean result = false;
            for (char key : iter.children.keySet()) {
                result |= dfsHelper(iter.children.get(key),word,pos+1);
            }
            return result;
        } else {
            if (!iter.children.containsKey(c)) {
                return false;
            }
            return dfsHelper(iter.children.get(c),word,pos+1);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
