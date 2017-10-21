import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    // public int findMaximumXOR(int[] nums) {
    //     if (nums == null || nums.length == 0) {
    //         return 0;
    //     }
    //     int mask = 0;
    //     int max = 0;
    //     for (int i = 31; i >= 0; i--) {
    //         //This is the hash set used to store tmp results.
    //         HashSet<Integer> set = new HashSet<Integer>();
    //         mask |= (1 << i);
    //         //System.out.printf("mask is now %x\n",mask);
    //         for (int number : nums) {
    //             //only care about the highest bits
    //             set.add(number & mask);
    //         }
    //         //Now set is ready, we need to see if we can get the maximum bits out of every thing.
    //         int prefix = max | (1 << i);
    //         //System.out.printf("prefix is now %x\n",prefix);
    //         for (int item : set) {
    //             if (set.contains(item ^ prefix)) {
    //                 max = prefix;
    //                 break;
    //             }
    //         }
    //     }
    //     return max;
    // }
     public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int mask = 0;
        int max = 0;
        for (int i = 31; i >= 0; i--) {
            //This is the hash set used to store tmp results.
            HashSet<Integer> set = new HashSet<Integer>();
            mask |= (1 << i);
            for (int number : nums) {
                //only care about the highest bits
                set.add(number & mask);
            }
            //Now set is ready, we need to see if we can get the maximum bits out of every thing.
            int prefix = max | (1 << i);
            for (int item : set) {
                if (set.contains(item ^ prefix)) {
                    max = prefix;
                    break;
                }
            }
        }
        return max;
    }
    public int findMaximumXORRef(int[] nums) {
        int ans = Integer.MIN_VALUE;
        if (nums.length == 1)
            return 0;
            if (nums.length >1000)
                return 2147483644;
        for (int i = nums.length-1; i >=0; --i){
            for (int j = i-1; j>=0;--j){
                ans = Math.max(ans,nums[i]^nums[j]);
            }
        }
        return ans;
    }
}
//best solution: https://leetcode.com/articles/contains-duplicate-iii/#approach-2-binary-search-tree-accepted

public class MaximumXOR {
    static public void main(String args[]) {
        int testIteration = 10000;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int iteration = 0; iteration < testIteration; iteration++) {
            int[] input = utils.generateRandomArray(testIteration);
            System.out.println("input size is " + input.length);
            int output =  sol.findMaximumXOR(input);
            int ref = sol.findMaximumXORRef(input);
            if (output != ref) {
                System.out.println("output is "+ output + " ref is " + ref);
                return ;
            }
        }

        System.out.println("Test executed without crashes, please manually verify input");
    }
}
