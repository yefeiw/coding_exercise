import java.util.*;

//private comparator class
class myComparator implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
        if (a.end != b.end) return a.end - b.end;
        //when there are multiple segments of the same end,
        //try the one with the largest start because that is the least possible one to cause an overlap.
        else return b.start - a.start;
    }
}

class Solution {
   
        public int eraseOverlapIntervals(Interval[] intervals) {
            if (!isValid(intervals)) {
                return 0;
            }
            Arrays.sort(intervals, new myComparator());
            int ret = 0;//returning count of 
            int marker = intervals[0].end;//"static"marker to walkthrough the list
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i].start >= marker) {
                    //not overlapping
                    marker = intervals[i].end;//if start >= marker, end is guaranteed >= marker, just update.
                } else {
                    ret++;
                }
            }
            return ret;
    }
    boolean isValid(Interval[] intervals) {
        if (null == intervals || 0 == intervals.length) {
            return false;
        }
        return true;
    }

    //Solution: Leetcode
    public int ref(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end != o2.end) return o1.end - o2.end;  //first sort by end
                return o2.start - o1.start;  //second sort by start
            }
        });

        int end = Integer.MIN_VALUE;
        int count = 0;
        for (Interval interval : intervals) {
            if (interval.start >= end) end = interval.end;
            else count++;
        }

        return count;
    }
}

public class eraseOverlapIntervals{
 public static void main (String args[]) {
        int testIteration = 10000;
        //small test set for manual verification
    // int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        Interval[] input = testInput.generateRandomIntervals();
        int output = sol.eraseOverlapIntervals(input);
        int ref = sol.ref(input);    
        if (output != ref) {
            System.out.println ("Error: Mismatch found");
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
"/"
"/../"
"//a/b/c/../../"
"////home/foo////bar"
"../../a/b/c"
*/