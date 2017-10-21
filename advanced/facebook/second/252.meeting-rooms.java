/*
 * [252] Meeting Rooms
 *
 * https://leetcode.com/problems/meeting-rooms
 *
 * algorithms
 * Easy (47.63%)
 * Total Accepted:    35.7K
 * Total Submissions: 75K
 * Testcase Example:  '[]'
 *
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all
 * meetings.
 * 
 * 
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
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
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals.length < 2) {
        	return true;
        }
        Arrays.sort(intervals, (Interval a, Interval b) -> (a.start - b.start));
        for (int i = 1; i < intervals.length; i++) {
        	if (intervals[i].start <intervals[i-1].end) {
        		return false;
        	}
        }
        return true;
    }
}
