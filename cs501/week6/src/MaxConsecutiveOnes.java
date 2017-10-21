import java.util.*;

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(null == nums || 0 == nums.length) {
            return 0;
        }
        int res = 0;//returning array length
        int left = 0;//last visited left endpoint
        Deque<Integer> queue =  new ArrayDeque<Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                queue.add(i);
            }
            if(queue.size() > 1) {
                left = queue.remove() + 1;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
        
    }
}
public class MaxConsecutiveOnes {
     public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
  
        for (int i = 0; i < testIteration; i++) {
          int[] input = testInput.generateRandomArray(testIteration);
          int output = sol.findMaxConsecutiveOnes(input);
          testInput.printArray(input);
          System.out.printf("output is %d\n", output);
        }  

        System.out.println("Test exeucted with no crashes, please verify on leetcode");
    }
}