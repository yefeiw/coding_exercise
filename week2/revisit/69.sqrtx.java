public class Solution {
    public int mySqrt(int x) {
        if ( x < 0) return -1;
        if ( x < 2) return x;
        int start = 1; int end = Math.min(x, (int)Math.sqrt(Integer.MAX_VALUE));
        while(start < end - 1) {
            int mid = start + (end - start) / 2;
            if(x / mid == mid) return mid;
            if (x / mid < mid) {
                end = mid;
            } else {
                start  = mid;
            }
        }
        //Two notes here:
        //1. the comparison need to be geq. This is for Integer.MAX_VALUE;
        //2. Here there is no concept of invalid returns. If end is not the one, start is guaranteed to be.
        if (x / end >= end) return end;
        return start;
    }
}
