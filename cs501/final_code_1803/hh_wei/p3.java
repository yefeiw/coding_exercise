
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
    public int getPerfectCount(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (sumFront(list.get(i)) == list.get(i).get(list.get(i).size() - 1)) {
                count++;
            }
        }
        return count;
    }

    private int sumFront(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            sum = sum + list.get(i);
        }
        return sum;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}