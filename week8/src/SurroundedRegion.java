import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:
// Careless Mistakes one after another:
// 1. Think at each line whether that input makes sense.
// 2. Think when copying/pasting code what is the actual condistion

    if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        //BFS: iteratively search for starting points and start if found.
        HashSet<Integer> visited = new HashSet<Integer>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'O' && !visited.contains(i*board[0].length + j)) {
                    search(board,visited,i,j);
                }
            }
        }
    }
    void search(char[][] board, HashSet<Integer> visited, int x, int y) {
        //board: input board
        //visited: whether each element is visited
        //x,y: starting position is board[x][y]
        int[] deltax = {0,-1,0,1};
        int[] deltay = {1,0,-1,0};
        Deque<Integer> queue = new ArrayDeque<Integer>();
        Set<Integer> buffer = new HashSet<Integer>();
        int pos  = x * board[0].length+y;
        visited.add(pos);
        queue.add(pos);
        buffer.add(pos);
        boolean found = false;
        while (!queue.isEmpty()) {
            int front = queue.remove();
            int frontx = front / board[0].length;
            int fronty = front % board[0].length;
            if (isBoundary(board, frontx,fronty)) {
                //System.out.println("Boundary " + frontx +"," + fronty);

                found = true;
            }
            for (int d = 0; d < 4; d++) {
                int candx = frontx + deltax[d];
                int candy = fronty + deltay[d];
                int candkey = candx * board[0].length + candy;
                if(isValid(board, candx, candy)&& !visited.contains(candkey)) {
                    //System.out.println("Adding " + candkey);
                    visited.add(candkey);
                    queue.add(candkey);
                    buffer.add(candkey);
                }
            }
        }
        if (!found){
            //overwrite all entries to X
            for(int key : buffer) {
                //System.out.println("Key is " + key);
                int keyx = key / board[0].length;
                int keyy = key % board[0].length;
                board[keyx][keyy] = 'X';
            }

        }
    }
    boolean isBoundary(char [][] board, int x, int y) {
        if (x == 0 || x == board.length -1) return true;
        if (y == 0 || y == board[0].length -1) return true;
        return false;
    }
    boolean isValid(char [][] board, int x, int y) {
        if (x < 0 || x >= board.length) return false;
        if (y < 0 || y >= board[0].length) return false;
        if (board[x][y] == 'X') return false;
        return true;
    }

public class SurroundedRegion{
    static public void main(String args[]) {

        System.out.println("Not able to verify locally. please manually verify input");
    }
}
