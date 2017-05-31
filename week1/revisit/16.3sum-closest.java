public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int ret = Integer.MAX_VALUE;
        for(int i =0; i < nums.length - 2; i++) {
            int goal = target - nums[i];
            int start = i+1; int end = nums.length -1;
            while(start < end) {
                int tempSum = nums[start] + nums[end];
                if (Math.abs(goal-tempSum) < diff) {
                    diff = Math.abs(goal-tempSum);
                    ret = tempSum+nums[i];
                }
                if (tempSum  == goal) {
                    //ret must be zero at this point
                    return ret;
                } else if (tempSum > goal) {
                    end --;
                } else {
                    //tempSum < goal
                    start++;
                }
            }
        }
        return ret;
                
    }
}
