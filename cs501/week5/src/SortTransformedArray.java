import java.util.*;

class Solution {
    int calc(int x, int a, int b, int c) {
        return a*x*x+b*x+c;
    }
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length  < 2) {
            return nums;
        }
        int i = 0; 
        int j = nums.length - 1;
        int index = (a>=0) ? nums.length - 1 : 0;
        int[] sorted = new int[nums.length];
        while(i <= j) {
            int left = calc(nums[i],a,b,c);
            int right = calc(nums[j],a,b,c);
            if (a >= 0) {
                if (left > right) {
                    sorted[index--] = left;
                    i++;
                } else {
                    sorted[index--] = right;
                    j--;
                }
            } else {
                if (left < right) {
                    sorted[index++] = left;
                    i++;
                } else {
                    sorted[index++] = right;
                    j--;
                }
            }
        }
        return sorted;
    }
}

public class SortTransformedArray {
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for(int i = 0; i < testIteration; i++) {
        int[] input = testInput.generateRandomArray();
        Arrays.sort(input);
        int a = testInput.generateRandomInt() % 100;
        int b = testInput.generateRandomInt() % 100;
        int c = testInput.generateRandomInt() % 100;
        int[] output = sol.sortTransformedArray(input,a,b,c);
        testInput.printArray(input);
        System.out.printf("a is %d, b is %d, c is %d\n",a,b,c);
        testInput.printArray(output);
    }
    
    System.out.println("Test executed with no crashes, please verify on LeetCode");
}
}
