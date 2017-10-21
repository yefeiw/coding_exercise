/*
 * [289] Game of Life
 *
 * https://leetcode.com/problems/game-of-life
 *
 * algorithms
 * Medium (36.99%)
 * Total Accepted:    53.2K
 * Total Submissions: 143.8K
 * Testcase Example:  '[[]]'
 *
 *
 * According to the Wikipedia's article: "The Game of Life, also known simply
 * as Life, is a cellular automaton devised by the British mathematician John
 * Horton Conway in 1970."
 *
 *
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or
 * dead (0). Each cell interacts with its eight neighbors (horizontal,
 * vertical, diagonal) using the following four rules (taken from the above
 * Wikipedia article):
 *
 *
 *
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by
 * under-population.
 * Any live cell with two or three live neighbors lives on to the next
 * generation.
 * Any live cell with more than three live neighbors dies, as if by
 * over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if
 * by reproduction.
 *
 *
 *
 *
 * Write a function to compute the next state (after one update) of the board
 * given its current state.
 *
 *
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at
 * the same time: You cannot update some cells first and then use their updated
 * values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the
 * board is infinite, which would cause problems when the active area
 * encroaches the border of the array. How would you address these problems?
 *
 *
 *
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 */
class Solution {
    public void gameOfLife(int[][] board) {
        if(board.length == 0 || board[0].length == 0) {
          return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0;i < m; i++) {
          for (int j = 0; j < n ;j++) {
            int nextState = getNextState(board,i,j);
            board[i][j] += (nextState << 16);
          }
        }
        for (int i = 0; i < m ; i++) {
          for (int j = 0; j < n; j++) {
            board[i][j] = board[i][j] >> 16;
          }
        }
    }

    //util functions
    private int getNextState(int[][] board, int x, int y) {
      int m = board.length;
      int n = board[0].length;

      int[] dx = {-1,0,1,1,1,0,-1,-1};
      int[] dy = {1,1,1,0,-1,-1,-1,0};

      int numNeighbors = dx.length;

      int numLivingNeighbors = 0;

      for (int d = 0; d < numNeighbors; d++) {
        int newX = x + dx[d];
        int newY = y + dy[d];
        if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
          continue;
        }
        int status = board[newX][newY] & 0xffff;
        numLivingNeighbors += status;
      }
      if (board[x][y] == 1) {
      return (numLivingNeighbors < 2 || numLivingNeighbors > 3) ? 0 : 1;
    } else {
      return (numLivingNeighbors == 3) ? 1 : 0;
    }

    }
}
