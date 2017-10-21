import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int ret = 0;
        if (k >= n / 2) {
            //trick: if there are too many available chances:
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    ret += (prices[i] - prices[i - 1]);
                }
            }
            return ret;
        }
        int[][] dp = new int[n + 1][n + 1];
        //initialize:
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }
}

public class Stock {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int[] input = utils.generateRandomArray(testIteration);
            int k = utils.generateRandomInt(testIteration);
            int output = sol.maxProfit(k, input);
            //print
            utils.printArray(input);
            System.out.println(k);
            System.out.println(output);
        }

        System.out.println("Test executed without crashes, please manually verify input");

    }
}
