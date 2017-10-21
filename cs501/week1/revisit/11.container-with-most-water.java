public class Solution {
    public int maxArea(int[] height) {
        if (null == height || height.length < 2) {
            return 0;
        }
        int start = 0;
        int end = height.length -1;
        int determinant = Math.min(height[start],height[end]);
        int ret = Math.min(height[start],height[end]) * (end - start);
        while (start < end ) {
            while(start < end && height[start] <= determinant) start++;
            while(start < end && height[end] <= determinant) end --;
            if (start < end) {
                ret = Math.max(ret, Math.min(height[start],height[end])*(end - start));
                determinant = Math.min(height[start],height[end]);
            }
        }
        return ret;
    }
}
