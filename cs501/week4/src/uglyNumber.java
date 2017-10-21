import java.util.*;

class Solution {
    public int nthUglyNumber(int n) {
        if ( n < 1) {
            return -1;
        }  
        ArrayList<Integer> scan =  new ArrayList<Integer>();
        scan.add(1);
        //indexes
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        for(int i = 1; i < n; i++) {
            int limit = scan.get(i-1);
            while(scan.get(p2) * 2 <= limit) p2++;
            while(scan.get(p3) * 3 <= limit) p3++;
            while(scan.get(p5) * 5 <= limit) p5++;
            int tail = Math.min(Math.min(scan.get(p2)*2, scan.get(p3)*3),scan.get(p5) * 5);
            scan.add(tail);
        }  
       return scan.get(n-1); 
    }
}
public class uglyNumber{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int input = i;
        System.out.printf("input is %d\n", input);
        int output = sol.nthUglyNumber(input);
        System.out.printf("output is %d\n", output);
    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
