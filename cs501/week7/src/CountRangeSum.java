import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        TreeMap<Integer,Integer> RBT = new TreeMap<Integer,Integer>();
        int sum = 0;
        int ret = 0;
        RBT.put(0,1);
        for (int num : nums) {
            sum += num;
            int from = num - upper;
            int to = num - lower;
            Map<Integer,Integer> subtree = RBT.subMap(from,true,to,true);
            for (Integer value : subtree.values()) {
                ret += value;
            }
            if(RBT.containsKey(sum)) {
                RBT.put(sum, RBT.get(sum) + 1);
            } else {
                RBT.put(sum,1);
            }
        }
        return ret;
    }
}

public class CountRangeSum {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
       
        for(int iteration = 0; iteration < testIteration; iteration++) {
            int[] input = utils.generateRandomArray(testIteration);
            int min = utils.generateRandomInt()%testIteration;
            int max = utils.generateRandomInt()%testIteration;
            if (min > max) {
                int temp = min;
                min = max;
                max = temp;
            }
            int output = sol.countRangeSum(input,min,max);
            //print
            utils.printArray(input);
            System.out.println(min);
            System.out.println(max);
            System.out.println(output);
            System.out.println("============");
        }
        
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
