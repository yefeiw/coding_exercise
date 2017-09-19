/*
 * [221] Maximal Square
 *
 * https://leetcode.com/problems/maximal-square
 *
 * algorithms
 * Medium (29.10%)
 * Total Accepted:    68.1K
 * Total Submissions: 234K
 * Testcase Example:  '["10100","10111","11111","10010"]'
 *
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * 
 * 
 * For example, given the following matrix:
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Return 4.
 * 
 * 
 * Credits:Special thanks to @Freezen for adding this problem and creating all
 * test cases.
 */
class Solution {
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int ret = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++) {
			dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
			ret = Math.max(ret, dp[i][0]);
		}
		for(int j = 0; j < n; j++) {
			dp[0][j] = matrix[0][j] == '1' ? 1  : 0;
			ret = Math.max(ret, dp[0][j]);

		}
		for (int i = 1; i < m ; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == '0') {
					dp[i][j] = 0;
				} else {
					dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
					ret = Math.max(ret, dp[i][j]);
				}
			}
		}
        //Note: we are only recording the length, we need to return the area
		return ret * ret;
	}
}
