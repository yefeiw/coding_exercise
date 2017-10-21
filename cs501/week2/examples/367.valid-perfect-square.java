public class Solution {
    public boolean isPerfectSquare(int num) {
        int start = 1; int end = num;
        while(start +1 < end) {
            int mid =  (start + end) / 2;
            if ((double)num / mid == mid) {
                return true;
            } else if (num / mid < mid) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if ((double)num / start  == start) return true;
        if ((double)num / end == end) return true;
        return false;
    }
}
