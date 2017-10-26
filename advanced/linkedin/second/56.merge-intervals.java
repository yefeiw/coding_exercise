/*
 * [56] Merge Intervals
 *
 * https://leetcode.com/problems/merge-intervals
 *
 * algorithms
 * Medium (30.33%)
 * Total Accepted:    142.5K
 * Total Submissions: 470K
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
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
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>();

        if (intervals.size() == 0 ) {
            return ret;
        }

        Collections.sort(intervals,(a,b)->(a.start - b.start));

        int curStart = intervals.get(0).start;
        int boundary = intervals.get(0).end;
        for(int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= boundary) {
                boundary = Math.max(boundary, intervals.get(i).end);
            } else {
                ret.add(new Interval(curStart, boundary));
                curStart = intervals.get(i).start;
                boundary = intervals.get(i).end;
            }
        }
        //here the last entry is still there
        ret.add(new Interval(curStart,boundary));

        return ret;
    }
}
