/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (Interval a, Interval b) -> (a.start - b.start));
        int range = Integer.MIN_VALUE;
        int count = 0;
        int j = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[j].end) {
                j = intervals[i].end < intervals[j].end ? i : j;
                count++;
            } else {
                j = i;
            }
        }
        return count;
    }
}
