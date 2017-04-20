import java.util.*;

class Solution {
    public ArrayList<Integer> grayCode(int n) {  
        if(n==0) {  
            ArrayList<Integer> result = new ArrayList<Integer>();  
            result.add(0);  
            return result;  
        }  
          
        ArrayList<Integer> result = grayCode(n-1);  
        int topBit = 1 << (n-1);
        int originalsize=result.size();
        
        for(int i=originalsize-1;i>=0;i--) {  
            result.add(topBit + result.get(i));  
        }  
        return result;  
    }
    //util function: print output
    public void print(ArrayList<Integer> output) {
        for ( int i = 0; i < output.size(); i++) {
            System.out.printf("%x ", output.get(i));
        }
        System.out.println();
    }

}
public class grayCode{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int input = i;
        System.out.printf("input is %d\n", input);
        ArrayList<Integer> output = sol.grayCode(input);
        sol.print(output);

    }
    System.out.println("Test executed without crashes, please manually verify input");
}
}
