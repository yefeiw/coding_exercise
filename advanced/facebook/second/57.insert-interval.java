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
        List<Interval> ret =  new ArrayList<>();
        if (intervals.size() == 0) {
        	ret.add(newInterval);
        	return ret;
        }
        int i = 0;
        for(i = 0; i < intervals.size();i++ ) {
        	//dont worry about end vs start
        	//since the merge intervals is about start, put start and continue;
        	if (intervals.get(i).start >= newInterval.start) {
        		break;
        	}
        }
        intervals.add(i,newInterval);
        // System.out.println("size of intervals is "+intervals.size());
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for(i = 1; i < intervals.size(); i++) {
        	if (intervals.get(i).start > end) {
        		ret.add(new Interval(start,end));
        		start = intervals.get(i).start;
        		end = intervals.get(i).end;
        	} else {
        		end = Math.max(intervals.get(i).end, end);
        	}
        }
        ret.add(new Interval(start,end));
        return ret;
        
    }
}
