public class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board,word.toCharArray(),i,j,0)) {
                    return true;
                }
            }
        }
        //reaching here means nothing has been found.
        return false;
    }
    private boolean isValid(char[][] board, int i, int j) {
        return (i >= 0 && i < board.length && j >= 0 && j < board[0].length);
    }
    private boolean helper(char[][] board, char[]word, int i, int j, int level) {
        //@param level: what is the current level to tell if we have used up our choices.
        if(level == word.length) {
            //here we have reached the end and no conflict has been found, return true;
            return true;
        }
        if (!isValid(board, i, j) || word[level] != board[i][j]) {
            //found conflict, return false;
            return false;
        }
        //important: temporarily overwrite input to mark the current letter as visited and also to save some time.
        char cur =  board[i][j];
        board[i][j] = '*';
        int[] deltax = {0,1,0,-1};
        int[] deltay = {1,0,-1,0};
        for (int d = 0; d < deltax.length; d++) {
            int newi = i+deltax[d];
            int newj = j+deltay[d];
            if (helper(board, word, newi, newj, level+1)) {
                board[i][j] = cur;
                return true;
            }
        }
        //reaching here means nothing is found
        board[i][j] = cur;
        return false;
    }
    

}
