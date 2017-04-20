import java.util.*;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // Note negative K and 0 are considered in leetcode, should not remove them.
        //if (k < 1) return false;
        if (nums == null || nums.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,-1);
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if(k != 0) {
                //Have to account for 0 as k
                runningSum %= k; 
            }
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }
}
public class subarraySum{
    public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            int k = i;
            int[] input  =  testInput.generateRandomArray();
            boolean output = sol.checkSubarraySum(input, k);
        }
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
