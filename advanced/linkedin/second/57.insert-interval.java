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
        List<Interval> ret = new ArrayList<>();
        if (intervals.size() == 0) {
            ret.add(newInterval);
            return ret;
        }

        Collections.sort(intervals, (a,b)->(a.start - b.start));
        int idx = searchFirstLargeEqual(intervals,newInterval.start);
        intervals.add(idx, newInterval);

        int curStart = intervals.get(0).start;
        int boundary = intervals.get(0).end;

        for(int i = 1; i < intervals.size(); i++){
            if (intervals.get(i).start <= boundary) {
                boundary = Math.max(boundary,intervals.get(i).end);
            } else {
                ret.add(new Interval(curStart,boundary));
                curStart = intervals.get(i).start;
                boundary = intervals.get(i).end;
            }
        }
        ret.add(new Interval(curStart,boundary));

        return ret;
    }

    //utils
    private int searchFirstLargeEqual(List<Interval> intervals, int target) {
        int start = 0;
        int end = intervals.size() - 1;

        while (start < end - 1) {
            int mid = start + (end - start) / 2;
            int cand = intervals.get(mid).start;
            if (target <= cand) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (intervals.get(start).start >= target) return start;
        if (intervals.get(end).start >= target) return end;
        return intervals.size();
    }
}
