import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
     public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //there are two DP problems.
        //1. How to tell whether a substring is palindrome
        //2. How to determine the mincut.
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        int[] minCut = new int[s.length()+1];
        for (int  i = 0; i < minCut.length; i++) {
            //initialize to the number of characters contained in the array
            //Note that for 0 we have -1, just for readability.
            minCut[i] = i - 1;
        }
        for (int start = s.length() - 1; start >= 0; start--) {
            for (int end = start; end < s.length(); end++) {
                if (((start + 1 > end -1)|| isPalindrome[start+1][end-1]) && s.charAt(start) == s.charAt(end)) {
                    isPalindrome[start][end] = true;
                }
            }
        }
        //after furnishing palindrome array, furnish minCut
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i-1; j >= 0; j --) {
                if (isPalindrome[j][i-1]) {
                    minCut[i] = Math.min(minCut[i],minCut[j]+1);
                }
            }
        }
        return minCut[s.length()];
    }
}

public class PalindromePartition {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            String input = utils.generateRandomString(testIteration);
            int output = sol.minCut(input);
            //print
            System.out.println(input);
            System.out.println(output);
        }
        System.out.println("Test executed without crashes, please manually verify input");

    }
}
