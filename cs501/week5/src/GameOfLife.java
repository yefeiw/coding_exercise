import java.util.*;

class Solution {
    public void gameOfLife(int[][] board) {
        if (null == board || 0 == board.length || 0 == board[0].length) {
            //can't process, return
            return;
        }
        //get updated status
        for (int i = 0; i < board.length; i++ ) {
            for (int j = 0; j < board[0].length; j++) {
                int newStatus = updatecell(board, i,j);
                board[i][j] += (newStatus << 16);
            }
        }
        //upate the board according to the new status.
        for (int i = 0; i < board.length; i++ ) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 16;
            }
        }
    }
    public int updatecell(int [][] board, int i, int j) {
        int[] deltax = {-1,0,1,1,1,0,-1,-1};
        int[] deltay = {1,1,1,0,-1,-1,-1,0};
        int liveNeighbor = 0;
        int curStatus = board[i][j] %(1<<16);
        for (int d = 0; d < deltax.length; d++) {
            int x = i+ deltax[d];
            int y = j+ deltay[d];
            if (x < 0 || x >= board.length) continue;
            if (y < 0 || y >= board[0].length) continue;
            if (board[x][y] % (1 << 16)  == 1) {
                liveNeighbor++;
            }
        }
        if (1 == curStatus) {
            return (liveNeighbor == 2 || liveNeighbor == 3) ? 1 : 0;
        } else {
            return (liveNeighbor == 3) ?  1 :  0;
        }

    }
}
public class GameOfLife{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int[][] input = testInput.generateRandomMatrix(testIteration,2);
        testInput.printMatrix(input);
        System.out.println("----- Input -----");
        sol.gameOfLife(input);
        System.out.println("+++++ Output +++++");
        testInput.printMatrix(input);
    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
