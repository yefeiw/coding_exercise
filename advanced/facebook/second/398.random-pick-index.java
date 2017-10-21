/*
 * [398] Random Pick Index
 *
 * https://leetcode.com/problems/random-pick-index
 *
 * algorithms
 * Medium (43.13%)
 * Total Accepted:    24.7K
 * Total Submissions: 57.1K
 * Testcase Example:  '["Solution","pick"]\n[[[1,2,3,3,3]],[3]]'
 *
 * 
 * Given an array of integers with possible duplicates, randomly output the
 * index of a given target number. You can assume that the given target number
 * must exist in the array.
 * 
 * 
 * 
 * Note:
 * The array size can be very large. Solution that uses too much extra space
 * will not pass the judge.
 * 
 * 
 * Example:
 * 
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * 
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should
 * have equal probability of returning.
 * solution.pick(3);
 * 
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 * 
 * 
 */
class Solution {
	private int[] array;
	Random rand;

    public Solution(int[] nums) {
        array = nums;
        rand = new Random();
    }
    
    public int pick(int target) {
       int ret = -1;
       int cnt = 0;
       for (int i  = 0; i < array.length; i++) {
       	if (array[i] == target) {
       		cnt++;
       		if (cnt == 1) {
       			ret = i;
       		} else {
       			if (rand.nextInt(cnt) == 0) {
       				ret = i;
       			}
       		}
       	}

       }
       return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
