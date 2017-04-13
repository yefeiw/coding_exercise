import java.util.*;

class Solution {
  public int maximumGap(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //get max and min
        for(int n : nums) {
            if (n > max) max = n;
            if (n < min) min = n;
        }
        double arrayDiff = (max - min) / (nums.length - 1.0);
        if (arrayDiff == 0) arrayDiff = 1;
        int[] localMin = new int[nums.length];
        int[] localMax = new int[nums.length];
        Arrays.fill(localMin,Integer.MAX_VALUE);
        Arrays.fill(localMax,Integer.MIN_VALUE);
        //bucket sort
        for(int n : nums) {
            int position = (int)((n - min) / arrayDiff);
            localMin[position] = Math.min(localMin[position], n);
            localMax[position] = Math.max(localMax[position], n);
        }
        //get diffs;
        int ret = 0;//returning max gap
        int left = 0; 
        int right = 1;
        while(right < nums.length) {
            while(right < nums.length && localMax[right] == Integer.MIN_VALUE) right++;
            if (right < nums.length) {
                ret = Math.max(ret, localMin[right] - localMax[left]);
                left = right;
                right++;
            }
        }
        return ret;
        
       
    } 
}

public class maxGap{
    public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            int[] input = testInput.generateRandomArray();
            int output = sol.maximumGap(input);
        }
        System.out.println("Test Executed, please manually check on leetcode");
    }
}
/* test cases used at leetcode:
[]
[0]
[0,1,2,3,4,5,6,7]
[0,1]
[0,1,2]
*/