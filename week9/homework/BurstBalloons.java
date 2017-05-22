import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //storng hint for  DP. The data structure is set up start to end
        //Use some more extra space for better readable code.
        List<Integer> input =  new ArrayList<Integer>(nums.length);
        for(int i = 0; i < nums.length; i++) {
            input.add(nums[i]);
        }
        //add corner condition for readability.
        input.add(0, 1);
        input.add(1);


        int[][] dp = new int[input.size()][input.size()];
        for (int len = 1; len <= nums.length; len++) {
            for (int start =  1; start <= nums.length - len +1; start++) {
                int end = start + len - 1;
                for (int k = start; k <= end; k++) {
                dp[start][end] = Math.max(dp[start][end], input.get(start-1)* input.get(k)* input.get(end+1) + dp[start][k - 1] + dp[k + 1][end]);
                }
            }
        }

        return dp[1][nums.length];
    }
}

public class BurstBalloons {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int [] input = utils.generateRandomArray(0,testIteration);
            int output = sol.maxCoins(input);
            //print
            utils.printArray(input);
            System.out.println(output);
        }
        System.out.println("Test executed without crashes, please manually verify input");

    }
}
