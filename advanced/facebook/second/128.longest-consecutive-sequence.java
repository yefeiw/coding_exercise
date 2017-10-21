/*
 * [128] Longest Consecutive Sequence
 *
 * https://leetcode.com/problems/longest-consecutive-sequence
 *
 * algorithms
 * Hard (36.99%)
 * Total Accepted:    112.6K
 * Total Submissions: 304.4K
 * Testcase Example:  '[100,4,200,1,3,2]'
 *
 * 
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
 * length: 4.
 * 
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 */
class Solution {
	public int longestConsecutive(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		Set<Integer> set = new HashSet<>();
		for(int i : nums) {
			set.add(i);
		}
		int ret = 1;
		for(int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {

				int increase = nums[i];
				int length = 1;
				while(set.contains(++increase)) {
					// System.out.println("set contains" +increase);
					length++;
					set.remove(increase);
				}
				int decrease = nums[i];
				while(set.contains(--decrease)) {
					// System.out.println("set contains" +decrease);
					length++;
					set.remove(decrease);
				}
				ret = Math.max(ret,length);
			}
		}
		return ret;
	}
}
