import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        //Practice using Lambda Expressions...
        Arrays.sort(envelopes, (int[] a, int[] b) -> (a[0] == b[0]) ? (b[1] - a[1]) : (a[0] - b[0]));
        //Sorting increasing on one direction and decreasing on the other, thus, this is now a LIS problem.
        //DP solution.
        int[] dp  = new int[envelopes.length];
        int ret = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                //envelopes[j][0] is known to be less than envelopes[i][0]
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ret = Math.max(dp[i], ret);
                }
            }
        }
        return ret;
    }
    public int maxEnvelopesSearch(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        //Practice using Lambda Expressions...
        Arrays.sort(envelopes, (int[] a, int[] b) -> (a[0] == b[0]) ? (b[1] - a[1]) : (a[0] - b[0]));
        //Sorting increasing on one direction and decreasing on the other, thus, this is now a LIS problem.
        //DP+Binary Search solution
        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int[] x : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, x[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = x[1];
            if (index == len) {
                len++;
            }
        }
        return len;
    }
}

public class RussianDollEnvelop {
    static public void main(String args[]) {
        int testIteration = 1000;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int [][] input = utils.generateRandomMatrix(testIteration);
            int output = sol.maxEnvelopes(input);
            int other = sol.maxEnvelopesSearch(input);
            //print
            utils.printMatrix(input);
            System.out.println(output);
            System.out.println(other);
            //check
            if (output != other) {
                System.out.printf("Mismatch!!! output %d, other %d\n");
                return;
            }
        }
        System.out.println("Test executed without crashes, please manually verify input");

    }
}
