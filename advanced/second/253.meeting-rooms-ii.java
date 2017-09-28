/*
 * [253] Meeting Rooms II
 *
 * https://leetcode.com/problems/meeting-rooms-ii
 *
 * algorithms
 * Medium (38.93%)
 * Total Accepted:    45.3K
 * Total Submissions: 116.3K
 * Testcase Example:  '[]'
 *
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms
 * required.
 * 
 * 
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length < 2) {
        	return intervals.length;
        }
        Arrays.sort(intervals, (Interval a, Interval b) -> (a.start - b.start));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ret = 0;
        pq.offer(intervals[0].end);
        for(int i = 1; i < intervals.length; i++) {
        	while(!pq.isEmpty() && intervals[i].start >= pq.peek()) {
        		pq.poll();
        	}
        	ret = Math.max(ret, 1+pq.size());
        	pq.offer(intervals[i].end);
        }
        return ret;

    }
}
