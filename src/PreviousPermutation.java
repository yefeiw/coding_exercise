import java.util.*;
private class Solution{
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
            swapItem(nums,j,i-1);
        }
        return nums;
    }
};
public class PreviousPermutation {
    public ArrayList<Integer> input;
    public ArrayList<Integer> output;
    public ArrayList<Integer> ref;
    int testIteration = 10000;
    void main (String args[]) {
        for (int i = 0; i < testIteration; i++) {
            input = generateRandomArrayList();
            output = PreviousPermuation.previousPermuation(input);
            ref = Ref.previousPermuation(input);
            if(output != ref) {
                System.out.println("diff found, exiting");
                return;
            }
        }
        System.out.println("test passed, ready to submit");
    }
}