import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public int[] countBits(int num) {
        int[] dp = new int[num+1];
        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i>>1] + (i & 1);
        }
        return dp;
    }
}

public class CountingBits{
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int input = utils.generateRandomInt(testIteration);
            input = Math.abs(input);
            int[] output = sol.countBits(input);
            //print
            System.out.println(input);
            utils.printArray(output);
        }

        System.out.println("Test executed without crashes, please manually verify input");

    }
}
