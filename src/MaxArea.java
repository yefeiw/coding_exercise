import java.util.*;

class Solution {

public int maxArea(int [] height) {
    if (!isValid(height)) {
        return 0;
    } 
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;
    while (left < right) {
        int localArea = (right - left) * Math.min(height[left], height[right]);
        if (localArea > maxArea) {
            maxArea = localArea;
        }
        //here is the most tricky part.
        // We will advance or reverse only the shorter path trying to find a bigger one.
        // The reason is that, once we have started advancing, as the width reduces, 
        // we are guaranteed that a larger area will not be found unless there is a larger 
        // value to the center.
        if (height[left] < height[right]) {
            left ++;
        } else {
            right --;
        }
    }
    return maxArea;
}

boolean isValid(int[]  height) {
    if (null == height || height.length < 2) {
        return false;
    }
    return true;
}


//Solution: LeetCode Article
public int ref(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }


public class MaxArea {
   public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10000;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int[] numbers = testInput. generateRandomArray();
        int output = sol.threeSumClosest(numbers);
        int ref = sol.ref(numbers);
        if (output != ref) {
            System.out.println("Mismatch Occurred!");
            System.out.println(output);
            System.out.println("vs.");
            System.out.println(ref);
            System.exit(1);
        }



    }
    System.out.println("test passed, ready to submit");
}
}
/* test cases used at leetcode:
[0,0,0]
1
[1,2,-1,4]
1
[1,2,-1,4]
-1
*/