public class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int start  = 0;
        int end = nums.length -1;
        int target = nums[nums.length -1];
        while(start < end -1) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
               start = mid; 
            } else {
                end = mid;
            }
        }
        return Math.min(nums[start],nums[end]);
    }
}
