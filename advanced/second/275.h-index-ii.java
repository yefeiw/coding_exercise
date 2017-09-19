/*
 * [275] H-Index II
 *
 * https://leetcode.com/problems/h-index-ii
 *
 * algorithms
 * Medium (34.49%)
 * Total Accepted:    52.3K
 * Total Submissions: 151.5K
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
        int start = 0; 
        int n = citations.length;
        int end = citations.length-1 ;
        while(start < end - 1) {
        	int mid = start + (end - start) /2;
        	int cand = citations[mid];
        	if (cand >= n-mid) {
        		end = mid;
        	} else {
        		start  = mid;
        	}
        }
        if (citations[start] >= n - start) return n - start;
        if (citations[end] >= n - end) return n - end;
        return 0;
    }
}
