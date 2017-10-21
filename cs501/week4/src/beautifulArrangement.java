import java.util.*;

class Solution {
    //static variable: keep track of the state.
    int count = 0;
    public int countArrangement(int N) {
        if (N < 1) {
            return 0;
        }
        int[] used = new int[N+1];
        count = 0;
        Arrays.fill(used,0);
        backtrack(N,N,used);
        return count;
    }
    void backtrack(int target, int remaining, int[] used) {
        if (remaining == 0) {
            count++;
            return;
        }
        for ( int i = 1; i <= target; i++) {
            if ((used[i] != 0 )|| ((remaining % i != 0) && (i % remaining != 0))) {
                continue;
            }
            used[i] = 1;
            backtrack(target, remaining -1, used);
            used[i] = 0;
        }
    }

}
public class beautifulArrangement{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int input = i;
        System.out.printf("input is %d\n", input);
        int output = sol.countArrangement(input);
        System.out.printf("output is %d\n", output);
    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
