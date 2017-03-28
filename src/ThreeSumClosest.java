import java.util.*;

class Solution {

 public int threeSumClosest(int[] numbers, int target) {
    if (!isValid(numbers,target)) {
        return -1;
    }
    Arrays.sort(numbers);
    int ret = numbers[0] + numbers[1] + numbers[2];
    int minDiff = Math.abs(target - ret);
    for (int i = 0; i < numbers.length - 2; i++) {
        int localTarget =  target - numbers[i];
         //two sums for numbers[i];
        int left = i+1;
        int right =  numbers.length - 1;
        while(left < right) {
            int cand = numbers[left] + numbers[right];
            int diff = Math.abs(localTarget - cand);
            if (diff < minDiff) {
                minDiff = diff;
                ret = cand + numbers[i];
            }
            if (cand < localTarget) {
                left++;
            } else if ( cand > localTarget) {
                right --;
            } else {
                //if they are equal
                return (cand + numbers[i]);
            }
        }
    }
    return ret;
}

boolean isValid(int[] numbers, int target) {
    if (null == numbers || numbers.length < 3) {
        return false;
    }
    return true;
}


//Solution: Jiuzhang
//Note: there will be known mismatches when the sum overflows.
 public int ref(int[] numbers, int target) {
        if (numbers == null || numbers.length < 3) {
            return -1;
        }
        
        Arrays.sort(numbers);
        int bestSum = numbers[0] + numbers[1] + numbers[2];
        for (int i = 0; i < numbers.length; i++) {
            int start = i + 1, end = numbers.length - 1;
            while (start < end) {
                int sum = numbers[i] + numbers[start] + numbers[end];
                if (Math.abs(target - sum) < Math.abs(target - bestSum)) {
                    bestSum = sum;
                }
                if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        
        return bestSum;
    }

}

public class ThreeSumClosest {
   public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10000;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        int[] numbers = testInput. generateRandomArray();
        int target = testInput.generateRandomInt();
        int output = sol.threeSumClosest(numbers,target);
        int ref = sol.ref(numbers,target);
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