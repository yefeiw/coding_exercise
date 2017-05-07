import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0 ) {
            return 0;
        }
        Map<Integer,Integer> preSum = new HashMap<Integer,Integer>();
        preSum.put(0,-1);
        int sum = 0;
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum,0) +1);
        }
        return result;
    }

}

public class SubarraySumK {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int[] input = utils.generateRandomArray(testIteration);
            int k =  utils.generateRandomInt(testIteration);
            int output = sol.subarraySum(input,k);
            //print
            utils.printArray(input);
            System.out.println(k);
            System.out.println(output);
        }
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
