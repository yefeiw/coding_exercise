
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
    // problem 3
    public int getPerfectCount(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int[] count = new int[0];
        int[] curSum = new int[0];
        dfs(nums, 0, curSum, count, new ArrayList<>());
        return count[0];
    }

    private void dfs(int[] nums, int cur, int[] curSum, int[] count, List<Integer>
    temp) {
    if (cur == nums.length) {
    return;
    }
    for (int i = cur; i < nums.length; i++) {
    temp.add(nums[i]);
    curSum[0] += nums[i];
    if (temp.size() != 1 && temp.get(temp.size() ‑1 ) == curSum[0] ‑
    temp.get(temp.size() ‑1 ) ) {
    count[0]++;
    return;
    }
    dfs(nums, cur + 1, curSum, count, temp);
    int lastIndex = temp.size() ‑ 1;
    int last = temp.get(lastIndex);
    curSum[0] ‑= last;
    temp.remove(lastIndex);
    } 
}