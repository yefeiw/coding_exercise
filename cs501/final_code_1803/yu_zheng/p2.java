
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
        final int N = nums.length;
        final int M = nums[0].length;
        int col = M - 1;
        for (int i = 0; i < N; i++) {
            if (col == 0)
                break; // minimum value
            // find smaller column index
            for (int j = 0; j < col; j++) {
                if (nums[i][j] == 1) {
                    col = j;
                    break;
                }
            }
        }
        return col + 1;
    }
}