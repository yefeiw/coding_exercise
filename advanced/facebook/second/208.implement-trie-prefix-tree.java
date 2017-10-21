/*
 * [208] Implement Trie (Prefix Tree)
 *
 * https://leetcode.com/problems/implement-trie-prefix-tree
 *
 * algorithms
 * Medium (28.55%)
 * Total Accepted:    83.6K
 * Total Submissions: 292.7K
 * Testcase Example:  '["Trie","search"]\n[[],["a"]]'
 *
 * 
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * 
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * 
 */
class Trie {
    class TrieNode {
        public Map<Character, TrieNode> children;
        public boolean isEnd;
        public TrieNode(){
            children = new HashMap<>();
            isEnd = false;
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
       root =new TrieNode(); 
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
       TrieNode iter = root;
       for(int i = 0; i < word.length(); i++) {
            char key = word.charAt(i);
            if (!iter.children.containsKey(key)) {
                iter.children.put(key, new TrieNode());
            }
            iter = iter.children.get(key);
       }
       iter.isEnd = true; 
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
       TrieNode iter = root;
       for (int i = 0; i < word.length(); i++) {
        char key = word.charAt(i);
        if (!iter.children.containsKey(key)) {
            return false;
        }
        iter = iter.children.get(key);
       } 
       return (iter.isEnd == true);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode iter = root;
        for (int i = 0; i < prefix.length(); i++) {
            char key = prefix.charAt(i);
            if (!iter.children.containsKey(key)) {
                return false;
            }
            iter = iter.children.get(key);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
