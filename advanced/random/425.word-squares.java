class Solution {
	class TrieNode {
        	char value;
        	Map<Character, TrieNode> children;
        	boolean isEnd;
        	public TrieNode(char value) {
        		this.value = value;
        		this.isEnd = false;
        		this.children = new HashMap<Character,TrieNode>();
        	}
        }
        class Trie {
        	TrieNode root;
        	public Trie() {
        		root = new TrieNode('\0');
        	}
        	public void insert(String word) {
        		TrieNode iter = root;
        		for (int i = 0; i < word.length(); i++) {
        			if (!iter.children.containsKey(word.charAt(i))) {
        				iter.children.put(word.charAt(i),new TrieNode(word.charAt(i)));
        			}
      				iter = iter.children.get(word.charAt(i));
        		}
        	}
        	public boolean search(String word) {
        		TrieNode iter = root;
        		for(int i = 0; i < word.length(); i++) {
        			char cand =  word.charAt(i);
        			if (!iter.children.containsKey(cand)) {
        				return false;
        			}
        			iter = iter.children.get(cand);
        		}
        	}
        	return true;
        }
    public List<List<String>> wordSquares(String[] words) {
    	List<List<String> ret =  new ArrayList<>();
    	if (words.length == 0 || words.length < words[0].length()) {
    		return ret;
    	}
    	for()


    }
}