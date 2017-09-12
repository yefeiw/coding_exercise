/*
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum
 *
 * algorithms
 * Easy (34.86%)
 * Total Accepted:    609.8K
 * Total Submissions: 1.7M
 * Testcase Example:  '[3,2,4]\n6'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * 
 * Example:
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * 
 * 
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
       Map<Integer, Integer> map = new HashMap<>();
       for(int i = 0; i < nums.length; i++) {
       	if(map.containsKey(target - nums[i])) {
       		int[] ans = new int[2];
       		ans[0] = map.get(target - nums[i]);
       		ans[1] = i;
       		return ans;
       	} else {
       		map.put(nums[i],i);
       	}
       } 
       return new int[2];
    }
}
