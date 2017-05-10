import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
   public String smallestGoodBase(String n) {
        long num  = Long.valueOf(n);
        int length = 64;
        while(length >=1) {
            if (((long)1 << length) < num ) {
                long k = getCand(num,length);
                if (k != -1) return String.valueOf(k);
            }
            length--;
        }
        return String.valueOf(num-1);
    }
    long getCand(long num, int length) {
        long start = 1; 
        long end = (long) (Math.pow(num,1.0/length) + 1);
        while(start < end - 1) {
            long mid = start + (end - start) / 2;
            long sum  = getSum(mid,length);
            // System.out.printf("sum is %d and num is %d\n",sum,num);
            // System.out.printf("start is %d and end is %d\n",start,end);
            if (sum == num) return mid;
            else if( sum > num) end = mid;
            else start = mid;
        }
        if (getSum(start,length) == num) return start;
        if (getSum(end,length) == num) return end;
        return -1;
    }
    long getSum(long cand, int length) {
        long sum  = 0; long cur = 1;
        for(int i = 0;i <= length; i++) {
            sum += cur;
            cur *= cand;
        }
        return sum;
    }
}
//best solution: http://hankerzheng.com/blog/LeetCode-Smallest-Good-Base

public class SmallestGoodBase {
    static public void main(String args[]) {
        int testIteration = 1;
        Input  utils = new Input();
        Solution sol = new Solution();
       
        for(int iteration = 0; iteration < testIteration; iteration++) {
            String input = String.valueOf(13);//(utils.generateRandomInt(testIteration));
            String output = sol.smallestGoodBase(input);
            //print
            System.out.println(input);
            System.out.println(output);
            System.out.println("============");
        }
        
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
