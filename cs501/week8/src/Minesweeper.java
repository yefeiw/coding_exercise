import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:
// Normal BFS will do the job. No need to think of harder algorithms.
// Note on the logic of string removal. StringBuffer will be very dangerous.
// Note especially when borrowing help from the solution. Remember to check the code flow before hitting submit button!

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        
        dfs(board, x, y);
        return board;
    }
    
    int[] dx = {-1, 0, 1, -1, 1, 0, 1, -1};
    int[] dy = {-1, 1, 1, 0, -1, -1, 0, 1};
    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E')  return;
        
        int num = getNumsOfBombs(board, x, y);
    
        if (num == 0) {
            board[x][y] = 'B';
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                dfs(board, nx, ny);
            }
        } else {
            board[x][y] = (char)('0' + num);
        }
        
    }
    
    private int getNumsOfBombs(char[][] board, int x, int y) {
        int num = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i, ny = y + j;
                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length)    continue;
                if (board[nx][ny] == 'M' || board[nx][ny] == 'X') {
                    num++;
                }
            }
        }
        return num;
    }

    
    class Pair {
        int x;
        int y;
        public Pair (int i, int j) {
            x = i;
            y = j;
        }
        public boolean equals(Pair other) {
            return (this.x == other.x && this.y == other.y);
        }

    }
    //delta x and delta y steps as in every other matrix.
        int[] deltax = {0,1,0,-1,-1,-1,1,1};
        int[] deltay = {1,0,-1,0,1,-1,1,1};

     public char[][] updateBoardBFS(char[][] board, int[] click) {
        if(board == null || click == null) {
            return board;
        }
        int x = click[0];
        int y = click[1];
        
        //BFS queue
        Deque<Pair> queue =  new ArrayDeque<Pair>();
        //visited map
        Map<Pair,Boolean> map = new HashMap<Pair, Boolean>();
        //////////////execution////////////////////
        if (board[x][y] == 'M') {
            //if we step on the mine, game is over and don't mark anything.
            board[x][y] = 'X';
            return board;
        }
        Pair starter = new Pair(x,y);
        queue.add(starter);
        map.put(starter,true);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for ( int i = 0; i < size; i++) {
                Pair head = queue.remove();
                int mines = findMine(head,board);
                int curx = head.x;
                int cury = head.y;
                if (mines == 0) {
                    //if this element does not have any danger, put all neighbors back into the queue.
                    for(int d = 0; d < deltax.length; d++) {
                        Pair next = new Pair(head.x + deltax[d],head.y + deltay[d]);
                        if(!map.containsKey(next)) {
                            queue.add(next);
                            map.put(next,true);
                        }
                    }
                    //modify the current value.
                    board[head.x][head.y] = 'B';

                }  else {
                        board[head.x][head.y] = (char)mines;
                        board[head.x][head.y] += '0';
                    }

            }
        }
        //reaching here means we are done with the search
        return board;
    }
    public int findMine(Pair head, char[][] board) {
        int x = head.x;
        int y = head.y;
        int ret = 0;
        for (int d = 0; d < deltax.length; d++) {
            int nx = x + deltax[d];
            int ny = y + deltay[d];
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board.length && board[nx][ny] == 'M') {
                ret++;
            }   
        }
        return ret;
    }

}

public class Minesweeper{
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        //too hard to test locally, rely on leetcode.
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
