/*
 * [348] Design Tic-Tac-Toe
 *
 * https://leetcode.com/problems/design-tic-tac-toe
 *
 * algorithms
 * Medium (46.01%)
 * Total Accepted:    16.2K
 * Total Submissions: 35.3K
 * Testcase Example:  '["TicTacToe","move","move","move","move","move","move","move"]\n[[3],[0,0,1],[0,2,2],[2,2,1],[1,1,2],[2,0,1],[1,0,2],[2,1,1]]'
 *
 * Design a Tic-tac-toe game that is played between two players on a n x n
 * grid.
 *
 *
 * You may assume the following rules:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical,
 * or diagonal row wins the game.
 *
 *
 *
 * Example:
 *
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 *
 *
 *
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 *
 */
class TicTacToe {
    int[] rows;
    int[] cols;
    int diag;
    int antidiag;
    int size;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diag = 0;
        antidiag = 0;
        size = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        rows[row]+= (player == 1) ? 1 : -1;
        cols[col]+= (player == 1) ? 1 : -1;
        if (row == col) {
          diag += (player == 1) ? 1 : -1;
        }
        if (row + col + 1 == size) {
          antidiag+= (player == 1) ? 1 : -1;
        }
        //judgements
        if (rows[row] == size) return 1;
        if (rows[row] == -size) return 2;
        if (cols[col] == size) return 1;
        if (cols[col] == -size) return 2;
        if (diag == size) return 1;
        if (diag == -size) return 2;
        if (antidiag == size) return 1;
        if (antidiag == -size) return 2;

        return 0;

    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
