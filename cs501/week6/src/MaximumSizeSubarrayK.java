import java.util.*;

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int maxLength = 0;
        if (null == nums || 0 == nums.length) {
            return maxLength;
        }
        HashMap<Integer,Integer> locations = new HashMap<Integer,Integer>();
        locations.put(0,-1);
        int prefixSum = 0;
        for(int i = 0;i < nums.length; i++) {
            prefixSum += nums[i];
            if (locations.containsKey(prefixSum - k)) {
                int curLength = i - locations.get(prefixSum - k);
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
            }
            if(!locations.containsKey(prefixSum)) {
                locations.put(prefixSum, i);
            }
        }
        return maxLength;
    }
}
public class MaximumSizeSubarrayK{
    public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
        //Sanity Test
        // int[] input = {1,-1,5,-2,3};
        // int k = 3;
        // int output = sol.maxSubArrayLen(input,k);
        // System.out.printf("output for the default input is %d\n", output);
        for (int i = 0; i < testIteration; i++) {
            int[] input = testInput.generateRandomArray();
            int k = testInput.generateRandomInt()%100;
            testInput.printArray(input);
            int output = sol.maxSubArrayLen(input, k);
            System.out.printf("for k of %d, output is %d\n\n\n",k,output);
        }  

        System.out.println("Test exeucted with no crashes, please verify on leetcode");
    }
}
