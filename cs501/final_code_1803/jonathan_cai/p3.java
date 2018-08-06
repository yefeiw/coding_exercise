
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
public class p3 {
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
    private void dfs(int[] nums, int start, ArrayList<Integer> path, int[] finalAns) {
        if (path.size() >= 3) {
            int n = path.size();
            int greatest = path.get(n - 1);
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += path.get(i);
            }
            if (sum == greatest) {
                finalAns[0] += 1;
            }
        }
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, finalAns);
            path.remove(path.size() - 1);
        }
    }

    public int getPerfectCount(int[] nums) {
        if (nums.length <= 2) {
            return 0;
        }
        Arrays.sort(nums);
        int[] finalAns = new int[1];
        dfs(nums, 0, new ArrayList<Integer>(), finalAns);
        return finalAns[0];
    }
}