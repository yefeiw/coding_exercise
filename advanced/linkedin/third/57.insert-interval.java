/*
 * [57] Insert Interval
 *
 * https://leetcode.com/problems/insert-interval
 *
 * algorithms
 * Hard (27.80%)
 * Total Accepted:    101.9K
 * Total Submissions: 366.7K
 * Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
 *
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their
 * start times.
 *
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 *
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
 * [1,2],[3,10],[12,16].
 *
 *
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 */
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int idx = search(intervals,newInterval);
        intervals.add(idx,newInterval);
        return mergeInterval(intervals);
    }

    //util functions
    private int search(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() == 0) {
            return 0;
        }
        int start = 0; int end  = intervals.size() -1;
        while(start < end -1) {
            int mid = start + (end - start) / 2;
            if (intervals.get(mid).start < newInterval.start) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (intervals.get(start).start >= newInterval.start) return start;
        if (intervals.get(end).start >= newInterval.start) return end;
        return intervals.size();
    }

    private List<Interval> mergeInterval(List<Interval> intervals) {
        List<Interval> ret = new ArrayList();
        int curStart = intervals.get(0).start;
        int boundary = intervals.get(0).end;
        for (int i = 1; i < intervals.size();i++) {
            if (intervals.get(i).start > boundary) {
                ret.add(new Interval(curStart,boundary));
                curStart = intervals.get(i).start;
                boundary = intervals.get(i).end;
            } else {
                boundary = Math.max(boundary,intervals.get(i).end);
            }
        }
        ret.add(new Interval(curStart,boundary));
        return ret;
    }
}
