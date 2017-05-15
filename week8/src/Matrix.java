import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:
//



class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        int[][] ret = new int[matrix.length][matrix[0].length];
        for(int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                ret[x][y] = Integer.MAX_VALUE;
            }
        }
        Deque<Pair> queue = new ArrayDeque<Pair>();
        Set<Pair> visited = new HashSet<Pair>();
        //add all zeros
        int xsize = matrix.length;
        int ysize = matrix[0].length;
        for (int x = 0; x < xsize; x++) {
            for (int y = 0; y < ysize; y++) {
                if (matrix[x][y] == 0) {
                    ret[x][y] = 0;
                    Pair cand = new Pair(x, y);
                    queue.add(cand);
                    visited.add(cand);
                }
            }
        }
        //Then, follow standard BFS searching routine.
        while (!queue.isEmpty()) {
            Pair head = queue.remove();
            for (int d = 0; d < 4; d++) {
                Pair cand = movePair(head, d);
                if (isValid(cand, matrix) && matrix[cand.x][cand.y] != 0) {
                    if (ret[cand.x][cand.y] < ret[head.x][head.y] + 1) continue;
                    ret[cand.x][cand.y] = ret[head.x][head.y] + 1;
                    queue.add(cand);
                    visited.add(cand);
                }
            }
        }
        return ret;
    }
    Pair movePair(Pair head, int i) {
        int[] deltax = { -1, 0, 1, 0};
        int[] deltay = {0, 1, 0, -1};
        Pair ret = new Pair(head.x + deltax[i], head.y + deltay[i]);
        return ret;
    }
    boolean isValid(Pair cand, int[][] matrix) {
        int x = cand.x;
        int y = cand.y;
        if (x < 0 || x >= matrix.length) return false;
        if (y < 0 || y >= matrix[0].length) return false;
        return true;
    }
}

public class Matrix {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        //Random testing
        for (int r = 0; r < testIteration; r++) {
            int[][] input = utils.generateRandomMatrix(testIteration);
            utils.printMatrix(input);

            int[][] output =  sol.updateMatrix(input);
            //print
            utils.printMatrix(output);
        }
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
