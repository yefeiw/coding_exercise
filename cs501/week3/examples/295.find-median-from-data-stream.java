//Notes:
// 1. There need to be two priority Queue. One is min (default), the other is max(Collections.reverseOrder());
// 2. When adding numbers, we need to add it to one of the heap and then poll one heap to the other. This is the ONLY way to keep ordering.
// 3. When returning, we need to make sure that one heap always feed the other when size is not the same.
public class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> min = new PriorityQueue();
    PriorityQueue<Integer> max = new PriorityQueue(1000,Collections.reverseOrder());
    public MedianFinder() {
        min = new PriorityQueue();
        max = new PriorityQueue(1000,Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if(min.size() > max.size()) {
            max.offer(min.poll());
        }
    }
    
    public double findMedian() {
       if(min.size() == max.size()) {
           //return the avg of the two numbers;
           return (max.peek() + min.peek()) / 2.0;
       } else {
           //note in previous logic, the max is always the larger one
           return max.peek();
       }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
