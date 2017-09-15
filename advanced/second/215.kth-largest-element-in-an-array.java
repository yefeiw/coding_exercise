/*
 * [215] Kth Largest Element in an Array
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array
 *
 * algorithms
 * Medium (39.39%)
 * Total Accepted:    149.9K
 * Total Submissions: 380.5K
 * Testcase Example:  '[1]\n1'
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 * Credits:Special thanks to @mithmatt for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < k) {
        	return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++) {
        	pq.add(nums[i]);
        	if(pq.size() > k) {
        		pq.poll();
        	}
        }
        return pq.poll();
    }
}
