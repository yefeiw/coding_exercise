import java.util.*;

class Solution {
    public int firstMissingPositive(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 1;
        }
        for(int i = 0; i< nums.length; i++) {
            while(nums[i] <= nums.length && nums[i] > 0 && nums[nums[i] -1] != nums[i]) {
                int temp =  nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
        //here, after quick select, all numbers should be in place.
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) return i+1;
        }
        return nums.length+1;
    }
}

public class FirstMissingPositive {
	 public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
  
        for (int i = 0; i < testIteration; i++) {
            int[] input = testInput.generateRandomArray(testIteration);
            int out = sol.firstMissingPositive(input);
            testInput.printArray(input);
            System.out.printf(" output is %d \n", out);
        }  

        System.out.println("Test exeucted with no crashes, please verify on leetcode");
    }
}