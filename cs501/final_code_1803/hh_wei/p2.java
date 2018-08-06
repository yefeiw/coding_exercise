
//*******************************************************************
// Welcome to CompileJava!
// If you experience any issues, please contact us ('More Info')  -->
// Also, sorry that the "Paste" feature no longer works! GitHub broke
// this (so we'll switch to a new provider): https://blog.github.com\
// /2018-02-18-deprecation-notice-removing-anonymous-gist-creation/
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class

// one class needs to have a main() method
public class HelloWorld {
    // arguments are passed using the text field below this editor
    public static void main(String[] args) {
        int[][] test1 = null;
        int[][] test2 = new int[0][0];
        int[][] test3 = { { 0, 0, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 1 } };
        int[][] test4 = { { 0, 0, 0, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 1 } };

        Solution sol = new Solution();

        System.out.println(sol.getFirstColumnContainsOne(test2));
    }
}

class Solution {
    public int getFirstColumnContainsOne(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        int cur = 0;
        for (int i = 0; i < m; i++) {
            if (nums[0][i] == 1) {
                cur = i;
                break;
            }
        }
        int j = 1;
        while (j < n) {
            while (cur > 0) {
                if ((nums[j][cur] == 1)) {
                    cur--;
                } else
                    break;
            }
            j++;
        }
        return cur;
    }
}