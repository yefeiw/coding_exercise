
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
    // return -1 if 1 is not found
    public int getFirstColumnContainsOne(int[][] nums) {
        int m = nums.length;
        if (m == 0) {
            return -1;
        }
        int n = nums[0].length;
        if (n == 0) {
            return -1;
        }
        int j = n - 1;
        for (int i = 0; i < m; i++) {
            while (j >= 0 && nums[i][j] == 1) {
                j -= 1;
            }
        }
        if (j == n - 1) {
            return -1;
        } else {
            return j + 2;
        }
    }
    }