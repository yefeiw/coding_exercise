
//*******************************************************************
// Welcome to CompileJava!
// If you experience any issues, please contact us ('More Info')  -->
// Also, sorry that the "Paste" feature no longer works! GitHub broke
// this (so we'll switch to a new provider): https://blog.github.com\
// /2018-02-18-deprecation-notice-removing-anonymous-gist-creation/
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class
import java.util.*;

// one class needs to have a main() method
public class p3{
    // arguments are passed using the text field below this editor
    public static void main(String[] args) {
        int[][] test1 = null;
        int[][] test2 = new int[0][0];
        int[][] test3 = { { 0, 0, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 1 } };
        int[] test4 = null;
        int[] test5 = { 2, 7, 1, 3, 5 };

        Solution solution = new Solution();

        System.out.println(solution.getPerfectCount(test5));
    }
}

class Solution {
    int res = 0;

    public int getPerfectCount(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            helper(nums, nums[i], 0, i);
        }
        return res;
    }

    private void helper(int[] nums, int target, int startindex, int endIndex) {
        if (target == 0) {
            res++;
        }
        for (int i = startindex; i < endIndex; i++) {
            helper(nums, target - nums[i], i + 1, endIndex);
        }
    }
}