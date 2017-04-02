import java.util.*;

class Solution {
    public int sqrt (int n) {
        if (!isValid(n)) {
            return 0;
        }
        //very important: we need to rule out div by zero;
        if (0 == n) return 0;
        int start = 0;//left edge of binary search
        int end = n;//right edge of binary search
        while(start < end - 1) {
            int mid = start + (end - start) / 2;
            int cand = n / mid;
            if (cand  == mid) {
                return mid;
            } else if (cand > mid) {
                //n/mid > mid; n > mid * mid; increase mid;
                start = mid;
            } else {
                end = mid;
            }
        }
        if (n / end > end) {
            return end;
        } else return start;

    }
    boolean isValid(int n) {
        if (n < 0) return false;
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