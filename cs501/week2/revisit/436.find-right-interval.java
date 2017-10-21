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
    public int[] findRightInterval(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0];
        }
        //start point value -> index
        TreeMap<Integer,Integer> map  = new TreeMap<Integer, Integer>();
        //first: furnish treemap
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
        }
        //second round : find such intervals
        int[] ret = new int[intervals.length];
        for(int i =0; i < intervals.length; i++) {
            if(map.ceilingKey(intervals[i].end) == null) {
                ret[i] = -1;
            } else  {
                ret[i] = map.get(map.ceilingKey(intervals[i].end));
            }
        }
        return ret;

    }
}
