/*
 * [88] Merge Sorted Array
 *
 * https://leetcode.com/problems/merge-sorted-array
 *
 * algorithms
 * Easy (32.06%)
 * Total Accepted:    180.9K
 * Total Submissions: 564.4K
 * Testcase Example:  '[1]\n1\n[]\n0'
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
 * one sorted array.
 * 
 * 
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to
 * m + n) to hold additional elements from nums2. The number of elements
 * initialized in nums1 and nums2 are m and n respectively.
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m+n -1;
        int idxM = m-1;
        int idxN = n-1;
        while(index >=0) {
        	int candM = (idxM >= 0) ? nums1[idxM] : Integer.MIN_VALUE;
        	int candN = (idxN >= 0) ? nums2[idxN] : Integer.MIN_VALUE;
        	if (candM > candN) {
        		nums1[index] = candM;
        		idxM--;
        	} else {
        		nums1[index] = candN;
        		idxN--;
        	}
        	index --;
        }
    }
}
