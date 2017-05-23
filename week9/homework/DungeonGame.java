import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (null == dungeon || dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        //This is clearly a dynamic programming problem
        int[][] dp = new int[m][n];
        //first step, determine the very end cell
        int last = dungeon[m-1][n-1];
        dp[m-1][n-1] = last >= 0? 1 : 1 - last;
        for(int i = m-2; i >=0; i--) {
            dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);
        }
        for(int i = n-2; i >=0; i--) {
            dp[m-1][i] = Math.max(1, dp[m-1][i+1] - dungeon[m-1][i]);
        }
        for(int i = m-2; i >= 0; i--) {
            for (int j = n -2; j >= 0; j--) {
                int right = Math.max(1, dp[i][j+1] - dungeon[i][j]);
                int down = Math.max(1, dp[i+1][j] - dungeon[i][j]);
                dp[i][j] = Math.min(right,down);
            }
        }
        return dp[0][0];
    }
}

public class DungeonGame {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int [][] input = utils.generateRandomMatrix(testIteration);
            int output = sol.calculateMinimumHP(input);
            //print
            utils.printMatrix(input);
            System.out.println(output);
        }
        System.out.println("Test executed without crashes, please manually verify input");

    }
}
