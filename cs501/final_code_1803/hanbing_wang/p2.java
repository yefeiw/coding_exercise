
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
        System.out.println(getFirstColumnContainsOne(test3));
    }

    static public int getFirstColumnContainsOne(int[][] nums) {
        if (nums == null)
            return 0;
        int row = nums.length;
        int col = nums[0].length;
        int idx = 0, i, j;
        for (i = 0, j = col - 1; i < row; i++) {
            while (nums[i][j] == 1 && j >= 0) {
                idx = i;
                j--;
            }
        }
        return j;
    }

}