/*
 * [79] Word Search
 *
 * https://leetcode.com/problems/word-search
 *
 * algorithms
 * Medium (26.95%)
 * Total Accepted:    138.3K
 * Total Submissions: 513.2K
 * Testcase Example:  '["ABCE","SFCS","ADEE"]\n"ABCCED"'
 *
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * 
 * 
 * For example,
 * Given board = 
 * 
 * [
 * ⁠ ['A','B','C','E'],
 * ⁠ ['S','F','C','S'],
 * ⁠ ['A','D','E','E']
 * ]
 * 
 * 
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * 
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
        	return false;
        }
        char start = word.charAt(0);
        for(int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		if (board[i][j] == start) {
        			 if (true == dfsHelper(board, i, j, 0, word)) {
        			 	return true;
        			 }
        		}
        	}
        }
        //reaching here meaning we cannot find the word.
        return false;
    }

    private boolean dfsHelper(char[][] board, int i, int j, int level, String word) {
    	if (level >= word.length()) {
    		return true;
    	}
    	if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
    		return false;
    	}
    	boolean result = false;
    	//idea; backtrackHelper: mark grid temporarily unavailable.
    	char temp  = board[i][j];
    	if (board[i][j] != word.charAt(level)) {
    		return false;
    	}
    	board[i][j] = '#';
    	//idea: use the property of || to skip over unncessary cost
    	result = dfsHelper(board, i+1, j, level+1, word)
    	|| dfsHelper(board, i, j+1, level+1, word)
    	|| dfsHelper(board, i-1, j, level+1, word)
    	|| dfsHelper(board, i, j-1, level+1, word);
    	board[i][j] = temp;
    	return result;
    }
}
