/*
 * [477] Total Hamming Distance
 *
 * https://leetcode.com/problems/total-hamming-distance
 *
 * algorithms
 * Medium (46.82%)
 * Total Accepted:    19.6K
 * Total Submissions: 41.8K
 * Testcase Example:  '[4,14,2]'
 *
 * The Hamming distance between two integers is the number of positions at
 * which the corresponding bits are different.
 * 
 * Now your job is to find the total Hamming distance between all pairs of the
 * given numbers.
 * 
 * 
 * Example:
 * 
 * Input: 4, 14, 2
 * 
 * Output: 6
 * 
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is
 * 0010 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2
 * + 2 + 2 = 6.
 * 
 * 
 * 
 * Note:
 * 
 * Elements of the given array are in the range of 0  to 10^9
 * Length of the array will not exceed 10^4. 
 * 
 * 
 */
class Solution {
    public int totalHammingDistance(int[] nums) {
        int mask = 0;
        int ans = 0;
        for (int i = 0; i <31; i++) {
        	mask = 1 << i;
        	int zeros = 0;
        	int ones = 0;
        	for (int num : nums) {
        		int masked = num & mask;
        		if (masked == 0) {
        			zeros++;
        		} else {
        			ones++;
        		}
        	}
        	ans += zeros * ones;
        }
        return ans;
    }
}
