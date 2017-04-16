import java.util.*;

class Solution {
    int limit;
    public List<List<Integer>> getFactors(int n) {
        limit = n;
        List<List<Integer>> ret =  new ArrayList<List<Integer>>();
        if (n < 2) {
            return ret;
        }
        List<Integer> stack =  new ArrayList<Integer>();
        backtrack(n, ret, stack, 2);
        return ret;  
    }
    void backtrack(int target, List<List<Integer>> ret, List<Integer> stack, int min) {
        // System.out.printf("the current stack multiples up to %d\n",cur_mul);
        //good return condition
        if (1 == target) {
            List<Integer>cur_list = new ArrayList<Integer>();
            for(int i : stack) {
                cur_list.add(i);
            }
            ret.add(cur_list);
            return;
        }
        if (target < min) {
            return;
        }
        for (int i = min; i <=target; i++) {
            if (target % i != 0 || i >= limit) {
                continue;
            }
            //if the number is a factor, to begin with.
            stack.add(0,i);
            backtrack(target / i, ret, stack, i);
            stack.remove(0);
        }
    }
    
    //Utility class == print
    void printOutput(List<List<Integer>> output) {
        System.out.println("output is:");
        for (List<Integer> solution : output) {
            System.out.println("+++++++++++++++++++");
            for (int x : solution) {
                System.out.printf("%d ",x);
            }
            System.out.println("\n-------------------");
        }
        System.out.println("As shown above.");
    }

}
public class factorCombo{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int input  = i;
        System.out.printf("input is %d\n", input);
        List<List<Integer>> output = sol.getFactors(input);
        sol.printOutput(output);
    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
