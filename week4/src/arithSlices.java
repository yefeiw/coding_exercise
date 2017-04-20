import java.util.*;

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (null == A || A.length < 3) {
            return 0;
        }
        int ret = 0;//total returning number
        int cur = 0;//current consecutive numbers that are good.
        for ( int i =2 ; i < A.length; i++) {
            if (A[i]-A[i-1] == A[i-1] - A[i-2]) {
                cur++;
                ret += cur;
            } else {
                cur = 0;
            }
        }
        return ret;
    }
}
public class arithSlices{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int[] input = testInput.generateRandomArray();
        int output = sol.numberOfArithmeticSlices(input);
        System.out.printf("output is %d\n", output);
    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
