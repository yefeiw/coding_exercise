import java.util.*;
public class PreviousPermutation{
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
        for (int i = inputSize -1; i >0 && nums.get(i) >= nums.get(i-1);i--);
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
