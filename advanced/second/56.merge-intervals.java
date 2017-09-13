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
        List<Interval> ret =  new ArrayList<>();
        if (intervals.size() == 0) {
        	return ret;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
        	@Override
        	public int compare(Interval a, Interval b) {
        		return a.start - b.start;
        	}
        });
        int border = intervals.get(0).end;
        int start = intervals.get(0).start;
        for (int i = 1; i < intervals.size(); i++) {
        	if (intervals.get(i).start > border) {
        		ret.add(new Interval(start,border));
        		start = intervals.get(i).start;
        		border = intervals.get(i).end;
        	} else {
        		border = Math.max(border, intervals.get(i).end);
        	}
        }
        //here the last interval is not inserted anyways.
        ret.add(new Interval(start,border));
        return ret;
    }
}
