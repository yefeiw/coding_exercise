public class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int min = nums[0];
        int max = nums[0];
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int len =(int)Math.ceil((double) (max - min) / (nums.length - 1));
        int[] bucketMin = new int[nums.length - 1];
        int[] bucketMax = new int[nums.length - 1];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        for (int i : nums) {
            if (i ==  min || i == max) {
                continue;
            }
            int idx = (i - min) / len;
            bucketMin[idx] = Math.min(i, bucketMin[idx]);
            bucketMax[idx] = Math.max(i, bucketMax[idx]);
        }
        //scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < nums.length -1; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE || bucketMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, bucketMin[i] - previous);
            previous = bucketMax[i];
        }
        //Note, we have not calculated the max element yet
        maxGap =  Math.max(maxGap, max - previous);
        return maxGap;

    }
}
