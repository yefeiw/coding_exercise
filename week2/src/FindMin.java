import java.util.*;

class Solution {
    public int findMin(int[] nums) {
        if (!isValid(nums)) {
            return 0;
        }
        int target = nums[nums.length -1];
        int start = 0; int end = nums.length - 1;
        while (start < end - 1) {
            int mid = start + (end - start) / 2;
            int cand = nums[mid];
            if (cand <= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        } else return nums[end];
    }
    boolean isValid(int[] n) {
        if (null == n || n.length == 0) return false;
        else return true;
    }
}

public class sqrt{
   public static void main (String args[]) {
    int testIteration = 10000;
        //small test set for manual verification
    // int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int input = testInput.generateRandomInt();
        int output = sol.sqrt(input);
        int ref = (int)Math.sqrt(input);
        if (output != ref) {
            System.out.println ("Error: Mismatch found");
            System.out.println(output);
            System.out.println("vs.");
            System.out.println(ref);
            System.exit(1);
        }
    }
    System.out.println("test passed, ready to submit");
}
}
/* test cases used at leetcode:
1
2
3
4
10000
123345
*/