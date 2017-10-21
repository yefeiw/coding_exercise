import java.util.*;

class Solution {
     public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<List<String>>();
        int[] cand = new int[n];
        if(n  <= 0) {
            List<String> bad = new ArrayList<String>();
            ret.add(bad);
            return ret;
        }
        backtrack(ret, cand,0);
        return ret;
    }
    void backtrack(List<List<String>> ret, int[] cand, int cur_level) {
        if(cur_level >= cand.length) {
            //means, valid solution
            ret.add(buildSolution(cand));
            return;
        }
        for (int i = 0; i < cand.length; i++) {
            //System.out.printf("cand[%d] is %d\n", i, cand[i]);
            cand[cur_level] = i;
            if(conflict(cand,cur_level)) {
                continue;
            }
            backtrack(ret,cand,cur_level+1);
        }
        //return
    }
    List<String> buildSolution(int[] cand) {
        List<String> ret = new ArrayList<String>();
        for(int i = 0; i < cand.length; i++) {
            StringBuffer sb =  new StringBuffer();
            for(int j = 0; j < cand.length; j++) {
                if (j == cand[i]) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }
    boolean conflict(int[] cand, int cur_level) {
        for (int i = 0; i < cur_level; i++) {
            if (Math.abs(cand[cur_level] - cand[i]) == (cur_level - i)) {
                //found conflict
                return true;
            }
            if (cand[cur_level] == cand[i]) {
                //found conflict
                return true;
            }
        }
        //reaching here means we are good.
        return false;
    }
    //utility == print
    void printOutput(List<List<String>> output) {
        for (List<String> solution : output) {
            for(String s : solution) {
                System.out.println(s);
            }
            System.out.println("++++++++++++++++++++");
        }
    }
}
public class nQueens{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int input  = i;
        System.out.printf("input is %d\n", input);
        List<List<String>> output = sol.solveNQueens(input);
        sol.printOutput(output);
    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
