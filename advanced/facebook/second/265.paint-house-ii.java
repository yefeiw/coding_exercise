/*
 * [265] Paint House II
 *
 * https://leetcode.com/problems/paint-house-ii
 *
 * algorithms
 * Hard (38.08%)
 * Total Accepted:    23.6K
 * Total Submissions: 62K
 * Testcase Example:  '[]'
 *
 * 
 * There are a row of n houses, each house can be painted with one of the k
 * colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the
 * same color.
 * 
 * 
 * The cost of painting each house with a certain color is represented by a n x
 * k cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color 0; costs[1][2] is the cost of painting house 1 with color 2, and so
 * on... Find the minimum cost to paint all houses.
 * 
 * 
 * Note:
 * All costs are positive integers.
 * 
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */
class Solution {
	public int minCostII(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}
		int firstMin = 0;
		int secondMin = 0;
		int preIndex = -1;
		int n = costs.length;
		int k = costs[0].length;
		for(int i = 0; i < n; i++) {
			int currentIndex = -1;
			//first iteration: record the colors.
			for (int j = 0; j < k; j++) {
				if (j == preIndex) {
					costs[i][j] += secondMin;
				} else {
					costs[i][j] += firstMin;
				}
			}
			//second iteration: update the mins
			int localFirstMin = Integer.MAX_VALUE;
			int localSecondMin = Integer.MAX_VALUE;
			for(int j = 0; j < k; j++) {
				if (costs[i][j] < localFirstMin) {
					localSecondMin = localFirstMin;
					localFirstMin = costs[i][j];
					currentIndex = j;
				} else if (costs[i][j] < localSecondMin) {
					localSecondMin = costs[i][j];
				}
			}
			//update
			firstMin = localFirstMin;
			secondMin = localSecondMin;
			preIndex = currentIndex;
		}
		//get min
		return firstMin;
	}
}
