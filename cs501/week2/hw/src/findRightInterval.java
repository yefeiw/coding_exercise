import java.util.*;

class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        
        int[] ret = new int[intervals.length];
        if (null == intervals || 0 == intervals.length) {
            return ret;
        }
        int[] starts = new int[intervals.length];
        HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            dict.put(starts[i],i);
        }
        Arrays.sort(starts);
        for (int i = 0; i < intervals.length; i++) {
            int target = intervals[i].end;
            int rightstart = binarySearch(starts, target);
            System.out.println("start found as" + rightstart);
            //Special note: this is wrong because -1 will also be a valid input
            //Need to use other conditions.
            //if (-1 == rightstart) {
            if (rightstart < target) {
                ret[i] = -1;
            } else {
                ret[i] = dict.get(rightstart);
            }
        }
        return ret;
    }
    int binarySearch(int[] array, int target) {
        int start = 0; int end = array.length -1;
        while(start < end -1) {
            int mid = start + (end - start) / 2;
            int cand = array[mid];
            if (cand >= target) {
                end = mid;
            } else {
                start =  mid;
            }
        }
        if(array[start] >= target) {
            return array[start];
        } else if (array[end] >= target) {
            return array[end];
        } else {
            return Integer.MIN_VALUE;
        }
    }
}

public class findRightInterval {
   public static void main (String args[]) {
    //int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        Interval[] input = testInput.generateRandomIntervals();
        testInput.printIntervals(input);
        int[] output = sol.findRightInterval(input);
        testInput.printArray(output);
    }
    System.out.println("test executed with no errors, please manually check for outputs");
}
}
/* test cases used at leetcode:
1
2
3
4
10000
123345
*/