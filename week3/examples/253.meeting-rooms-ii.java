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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int ret = 1;//returning max number of meeting rooms.
        Arrays.sort(intervals,(a,b)->(a.start - b.start));
        //Key point here: Priority Queue
        //Always keep the first finishing meeting at the top.
        PriorityQueue<Interval> queue = new PriorityQueue<>(intervals.length, (a,b)->(a.end - b.end));
        queue.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= queue.peek().end) {
                //if start is greater than the current end, pop.
                queue.poll();
            }
            //In any situation, push the current meeting to the queue.
            queue.offer(intervals[i]);
            ret = Math.max(ret,queue.size());
        }
        return ret;
    }
}
