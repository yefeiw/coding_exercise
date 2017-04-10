import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].lenth == 0) {
            return 0;
        }   
        int m = grid.length;//num of rows
        int n = grid[0].length;//num of columns
        int ret = 0;//total number of islands
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    //is an island, increment and search.
                    ret++;
                    searchIsland(grid, i,j);
                }
            }
        }
        return ret;
    }
    void searchIsland(char[][] grid, int i, int j) {
        int[] deltax = {-1,0,1,0};
        int[] deltay = {0,1,0,-1};
        ArrayDeque<Integer> queuex = new ArrayDeque<Integer>();
        ArrayDeque<Integer> queuey = new ArrayDeque<Integer>();
        queuex.add(i);
        queuey.add(j);
        while(!queuex.isEmpty()) {
            int candx = queuex.remove();
            int candy = queuey.remove();
            for (int i = 0; i < deltax.length; i++) {
                int newx = candx+deltax[i];
                int newy = candy+deltay[i];
                if (isLand(grid, newx, newy)) {
                    queuex.add(newx);
                    queuey.add(newy);
                    //important: clear newx and newy
                    grid[newx][newy] = 0;
                }
            }
        }
        return;
    }
    boolean isLand(char[][] grid, int x, int y) {
        if (x < 0 || x >=grid.length) return false;
        if (y < 0 || y >=grid[0].length) return false;
        return (grid[x][y] == 1);
    }
}
public class NumIslands{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        char[][] input = testInput.generateBinaryMatrix(testIteration);
        testInput.printMatrix(input);
        int output = numIslands(input);
        System.out.printf("output is %d\n",output);
    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
