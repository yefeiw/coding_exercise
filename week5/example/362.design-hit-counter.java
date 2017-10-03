/*

import java.util.Deque;
 * [362] Design Hit Counter
 *
 * https://leetcode.com/problems/design-hit-counter
 *
 * algorithms
 * Medium (54.20%)
 * Total Accepted:    18.1K
 * Total Submissions: 33.3K
 * Testcase Example:  '["HitCounter","hit","hit","hit","getHits","hit","getHits","getHits"]\n[[],[1],[2],[3],[4],[300],[300],[301]]'
 *
 * Design a hit counter which counts the number of hits received in the past 5
 * minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you
 * may assume that calls are being made to the system in chronological order
 * (ie, the timestamp is monotonically increasing). You may assume that the
 * earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Example:
 *
 * HitCounter counter = new HitCounter();
 *
 * // hit at timestamp 1.
 * counter.hit(1);
 *
 * // hit at timestamp 2.
 * counter.hit(2);
 *
 * // hit at timestamp 3.
 * counter.hit(3);
 *
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 *
 * // hit at timestamp 300.
 * counter.hit(300);
 *
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 *
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 *
 *
 *
 * Follow up:
 * What if the number of hits per second could be very large? Does your design
 * scale?
 *
 *
 * Credits:Special thanks to @elmirap for adding this problem and creating all
 * test cases.
 */
class HitCounter {
    private Deque<Integer> queue;
    /** Initialize your data structure here. */
    public HitCounter() {
        this.queue = new ArrayDeque<>();
    }

    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int threshold = timestamp - 300;
        while(queue.size() > 0 && queue.peek() <= threshold) {
          queue.remove();
        }
        queue.add(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
      int threshold = timestamp - 300;
      while(queue.size() > 0 && queue.peek() <= threshold) {
        queue.remove();
      }
      return queue.size();

    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
