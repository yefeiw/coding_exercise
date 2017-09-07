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
        List<Interval> ans = new ArrayList<Interval>();
        int idx = 0;
        //first, find the starting point
        while(idx < intervals.size() && newInterval.start > intervals.get(idx).start) {
        	idx++;
        }
        //second, add the new interval to the set of intervals.
        intervals.add(idx, newInterval);
        //third, merge all possible intervals
        Interval last = null;
        for(Interval item : intervals) {
        	if (last == null || item.start > last.end) {
        		ans.add(item);
        		last = item;
        	} else {
        		last.end = Math.max(item.end, last.end);
        	}
        }

        return ans;
    }
}