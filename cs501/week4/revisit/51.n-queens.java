public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret =  new ArrayList<List<String>>();
        if (n < 1) {
            return ret;
        }
        int[] board = new int[n];
        helper(board, 0,ret);
        return ret;
    }
    //Now that we want all solutions, there is nothing other than an
    //exhaustive search
    void helper(int[] board, int level, List<List<String>> ret ) {
        if (level == board.length) {
            //reaching here with no conflict found, add result to the
            //ret and return
            List<String> sol =  generateSolution(board);
            ret.add(sol);
            return;
        }
        //backtracking
        for(int i = 0; i < board.length; i++) {
            board[level] = i;
            boolean validate = true;
            for (int j  = 0; j < level; j++) {
                if (board[j] == board[level] || Math.abs(board[level]-board[j]) == Math.abs(level - j)) {
                    //conflict, don't calculate
                    validate = false;
                    break;
                }
            }
            if(validate) helper(board, level+1, ret);
        }
        //return
    }
    private List<String> generateSolution(int[] board) {
        //assumption: board is a valid solution
        List<String> ret =  new ArrayList<String>();
        for(int i  = 0; i < board.length; i++) {
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < board.length; j++) {
                if (j != board[i]) sb.append(".");
                else sb.append("Q");
            }
            ret.add(sb.toString());
        }
        return ret;
    }

 
}
