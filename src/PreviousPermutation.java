import java.util.*;
//import Input.*;

public class PreviousPermutation {

    public static void main (String args[]) {
        int testIteration = 10000;
        ArrayList<Integer> input;
        ArrayList<Integer> output;
        ArrayList<Integer> ref;

        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            input = testInput.generateRandomArrayList();
            System.out.println("generated input size"+input.size());
            output = sol.previousPermuation(input);
            ref = sol.previousPermuation(input);
            if(output != ref) {
                System.out.println("diff found, exiting");
                return;
            }
        }
        System.out.println("test passed, ready to submit");
    }

};
class Solution{
    private void swapList(ArrayList<Integer> nums, int start, int end) {
        while (start < end) {
            Collections.swap(nums,start,end);
            start++;
            end--;
        }
    }
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
        // write your code
        if (null == nums || nums.isEmpty()) {
            return nums;
        }
        int inputSize = nums.size();
        int i = inputSize -1;
        for (i = inputSize -1; i >0 && nums.get(i) >= nums.get(i-1);i--);
            swapList(nums,i,inputSize -1);
        if (i != 0 ) {
            int j = i;
            while(nums.get(j) >= nums.get(i-1) ) {
                j++;
            }
            Collections.swap(nums,j,i-1);
        }
        return nums;
    }
};