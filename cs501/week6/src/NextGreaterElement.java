import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (null == nums || 1 > nums.length) {
            return nums;
        }
        Deque<Integer>stack = new ArrayDeque<Integer>();
        int[] ret = new int[nums.length];
        Arrays.fill(ret,-1);
        for (int i = 0 ; i < nums.length*2; i++) {
            while (!stack.isEmpty()  && nums[stack.peek()] < nums[i%nums.length]) {
                //System.out.printf("replacing %d with %d\n",nums[stack.peek()], nums[i%nums.length]);
                //here we are using the stack to store the last lesser elements
                ret[stack.peek()] = nums[i%nums.length];
                stack.pop();
            } 
            if ( i < nums.length) {
                stack.push(i);
            }
        }
        return ret;
        
    }
}
public class NextGreaterElement {
     public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
  
        for (int i = 0; i < testIteration; i++) {
          int[] input = testInput.generateRandomArray(testIteration);
          int[] output = sol.nextGreaterElements(input);
          testInput.printArray(input);
          testInput.printArray(output);
        }  

        System.out.println("Test exeucted with no crashes, please verify on leetcode");
    }
}