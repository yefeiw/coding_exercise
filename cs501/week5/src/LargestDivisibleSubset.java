import java.util.*;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();
        if (null == nums || 0 == nums.length) {
            return ret;
        }
        //first, sort the array
        Arrays.sort(nums);
        //setDP and trace the max.
        int[] localMax = new int[nums.length];
        int[] pre = new int[nums.length];
        Arrays.fill(pre,-1);
        int globalMax = 1;
        localMax[0] = 1;
        pre[0] = -1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if ( 0 == nums[i]%nums[j]) {
                    if(localMax[j] +1 > localMax[i]) {
                        //update both dp values and pre value
                        localMax[i] = localMax[j] + 1;
                        pre[i] = j;
                        globalMax = Math.max(globalMax,localMax[i]);
                    }
                }
            }
        }
        //After DP values, search for the answer;
        int start = nums.length -1;
        while(localMax[start] != globalMax) start--;
        //now we have got the start;
        while(start != -1) {
            ret.add(nums[start]);
            start = pre[start];
        }
        //But then the list is reversed, need to get it back;
        Collections.reverse(ret);
        return ret;
        
    }
}

public class LargestDivisibleSubset {
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for(int i = 0; i < testIteration; i++) {
        int[] input = testInput.generateRandomArray();
        List<Integer> output = sol.largestDivisibleSubset(input);
        System.out.println("------------------------");
        testInput.printArray(input);
        System.out.println("++++++++++++++++++++++++");
        testInput.printArray(output.stream().mapToInt(j->j).toArray());
    }
    
    System.out.println("Test executed with no crashes, please verify on LeetCode");
}
}
