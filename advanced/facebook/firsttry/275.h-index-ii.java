/*
 * [275] H-Index II
 *
 * https://leetcode.com/problems/h-index-ii
 *
 * algorithms
 * Medium (34.41%)
 * Total Accepted:    51K
 * Total Submissions: 148.3K
 * Testcase Example:  '[]'
 *
 * 
 * Follow up for H-Index: What if the citations array is sorted in ascending
 * order? Could you optimize your algorithm?
 * 
 */
class Solution {
    public int hIndex(int[] citations) {
        if (citations.length == 0) {
        	return 0;
        }
        int m = citations.length;
        int start = 0; int end = m-1;
        while(start < end - 1) {
        	int mid = start + (end - start) / 2;
        	if (citations[mid] >= m - mid) {
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        if (citations[start] >= m - start) return m - start;
        if (citations[end] >= m - end) return m - end;
        return 0;

    }
}
