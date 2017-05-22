import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
     public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int ret_sqrt = 0;
        //usual strategy to avoid special processing
        int[][] dp = new int[m+1][n+1];
        //fill first line and first row
        for (int i = 1; i <= m; i++) {
            for( int j =1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    ret_sqrt = Math.max(ret_sqrt,dp[i][j]);
                }
            }
        }
        return ret_sqrt*ret_sqrt;
    }
}

public class MaxSquare{
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            char [][] input = utils.generateCharMatrix(testIteration);
            int output = sol.maximalSquare(input);
            //print
            utils.printMatrix(input);
            System.out.println(output);


            System.out.println("Test executed without crashes, please manually verify input");

        }
    }
}
