import java.util.*;

class Solution {
    public int arrayPairSum(int[] nums) {
        //input is bounded -- strong hint for non-com sort.
        nums = bucketSort(nums);
        //System.out.println(Arrays.toString(nums));
        //max of min -- min with min, max with max
        int ret = 0;
        for(int i = 0; i < nums.length; i+=2) {
            ret += nums[i];
        }
        return ret;
    }
    int[] bucketSort(int[] input) {
        int[] bucket = new int[20001];
        Arrays.fill(bucket,0);
        for (int i : input) {
            bucket[i + 10000]++;
        }
        int count = 0;
        for (int i = 0; i < 20001; i++) {
            if(bucket[i] > 0) {
                //System.out.printf("bucket[%d] = %d\n",i,bucket[i]);
                for (int j = 0;j < bucket[i];j++) {
                    input[count++] = i - 10000;
                }
            }
        }
        return input;
    }
}
public class ArrayPartition {
	 public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
  
        for (int i = 0; i < testIteration; i++) {
            int[] input = testInput.generateRandomArray(testIteration);
            int output = sol.arrayPairSum(input);
            testInput.printArray(input);
            System.out.println(output);

        }  

        System.out.println("Test exeucted with no crashes, please verify output manually");
    }
}