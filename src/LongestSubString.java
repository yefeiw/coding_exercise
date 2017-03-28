import java.util.*;

class Solution {
   public static final int ASCII_SIZE = 256;

    public int lengthOfLongestSubstring(String s) {
        if (!isValid(s)) {
            return s.length();
        }
        int ret = 1;//maximum returning value;
        int[] positions = new int[ASCII_SIZE];
        Arrays.fill(positions,-1);
        int j = 0;
        for(int i = 0; i < s.length(); i++) {
            j = Math.max(j, positions[s.charAt(i)]+1);
            int cur_length = i - j + 1;
            ret = Math.max(ret, cur_length);
            positions[s.charAt(i)] = i;
        }
        return ret;
    }

    boolean isValid(String s) {
        if (null == s || s.length() == 0) {
            return false;
        }
        return true;
    }
}

public class LongestSubString {
 public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        String input = testInput.generateRandomString();
        System.out.println(input);
        int output = sol.lengthOfLongestSubstring(input);
            
        System.out.println(output);
    }
    System.out.println("test passed, ready to submit");
}
}
/* test cases used at leetcode:
"abcabcbb"
"76faf6c3-fef0-48e5-bc5f-b1d74d1110d3"
""
"c"
"1234567890"
*/