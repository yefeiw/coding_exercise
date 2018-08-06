
//*******************************************************************
// Welcome to CompileJava!
// If you experience any issues, please contact us ('More Info')  -->
// Also, sorry that the "Paste" feature no longer works! GitHub broke
// this (so we'll switch to a new provider): https://blog.github.com\
// /2018-02-18-deprecation-notice-removing-anonymous-gist-creation/
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class
import java.util.*;

// one class needs to have a main() method
public class p3 {
    // arguments are passed using the text field below this editor
    public static void main(String[] args) {
        int[][] test1 = null;
        int[][] test2 = new int[0][0];
        int[][] test3 = { { 0, 0, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 1 } };
        int[] test4 = null;
        int[] test5 = { 2, 7, 1, 3, 5 };

        Solution solution = new Solution();

        System.out.println(solution.getPerfectCount(test5));
    }
}

class Solution {
    public int getPerfectCount(int[] nums){
        //brute force - nested for loop O n^2 * (n-2) = n^3
        //sorted, [1, 2, 3, 7, 5]
        // 1 2,3 3/4/5/6, 7/8/9/10/10/11/12/13
        //O n^2
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int num: nums){
        set.add(num);
        }
        int res = 0;
        HashMap<Integer, > map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
        for (int j = 0; j <= i; j++){
        for (int num: map.get(nums[j])){
        int sum = nums[i] + num;
        map.put(nums[i]).add(sum);
        if (set.contains(sum)){
        res ++;
        }
        }
        }
        }
        return res;
        }
}