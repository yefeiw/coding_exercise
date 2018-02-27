import java.util.Set;

/*
 * [212] Word Search II
 *
 * https://leetcode.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (24.48%)
 * Total Accepted:    60.7K
 * Total Submissions: 247.3K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n["oath","pea","eat","rain"]'
 *
 * 
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * 
 * 
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board = 
 * 
 * [
 * ⁠ ['o','a','a','n'],
 * ⁠ ['e','t','a','e'],
 * ⁠ ['i','h','k','r'],
 * ⁠ ['i','f','l','v']
 * ]
 * 
 * 
 * Return ["eat","oath"].
 * 
 * 
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * 
 * 
 * click to show hint.
 * 
 * You would need to optimize your backtracking to pass the larger test. Could
 * you stop backtracking earlier?
 * 
 * If the current candidate does not exist in all words' prefix, you could stop
 * backtracking immediately. What kind of data structure could answer such
 * query efficiently? Does a hash table work? Why or why not? How about a Trie?
 * If you would like to learn how to implement a basic trie, please work on
 * this problem: Implement Trie (Prefix Tree) first.
 * 
 */
class Solution {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        TrieNode() {
            children = new HashMap();
            isEnd = false;
        }

        void AddWord(String word) {
            TrieNode iter = this;
            for (int i = 0; i < word.length(); i++) {
                char cand = word.charAt(i);
                if (!iter.children.containsKey(cand)) {
                    iter.children.put(cand, new TrieNode());

                }
                iter = iter.children.get(cand);
            }
            iter.isEnd = true;
        }

        void AddWords(String[] words) {
            for (String word : words) {
                AddWord(word);
            }
        }
    }

    Set<String> visited;

    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0) {
            //invalid board, return;
            return new ArrayList();
        }
        int m = board.length;
        int n = board[0].length;
        List<String> ret = new ArrayList();
        visited = new HashSet();
        TrieNode trie = new TrieNode();
        trie.AddWords(words);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                StringBuilder sb = new StringBuilder();
                matchHelper(trie, board, i, j, sb, ret);
            }

        }
        return ret;
    }

    private void matchHelper(TrieNode iter, char[][] board,int i, int j, StringBuilder sb, List<String> ret) {
    //    System.out.println("searching with "+sb.toString()); 
        if (iter == null) {
            //how is that possible?
            return;
        }
        if (iter.isEnd) {
            if (!visited.contains(sb.toString())) {
                ret.add(sb.toString());
                visited.add(sb.toString());
            }
            //BUG: here we should not return at once.
           //instead, we should mark isEnd to false and then continue;
            iter.isEnd = false;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (!iter.children.containsKey(board[i][j])) {
            return;
        }
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        sb.append(board[i][j]);
        char c = board[i][j];
      TrieNode child = iter.children.get(board[i][j]);
      //BUG: if we don't do it here, then we will not be able to mark visited easily.
      board[i][j] = '#'; 
        for (int d = 0; d < dx.length; d++) {
            int newX = i + dx[d];
            int newY = j + dy[d];
            
            matchHelper(child, board, newX, newY, sb,ret);
        
        }
       board[i][j] = c;
        sb.deleteCharAt(sb.length()-1);
    
    }
}