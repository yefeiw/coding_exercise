/*
 * [256] Paint House
 *
 * https://leetcode.com/problems/paint-house
 *
 * algorithms
 * Easy (46.23%)
 * Total Accepted:    29.1K
 * Total Submissions: 63.1K
 * Testcase Example:  '[]'
 *
 *
 * There are a row of n houses, each house can be painted with one of the three
 * colors: red, blue or green. The cost of painting each house with a certain
 * color is different. You have to paint all the houses such that no two
 * adjacent houses have the same color.
 *
 *
 * The cost of painting each house with a certain color is represented by a n x
 * 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color red; costs[1][2] is the cost of painting house 1 with color green, and
 * so on... Find the minimum cost to paint all houses.
 *
 *
 * Note:
 * All costs are positive integers.
 */
class Solution {
    public int minCost(int[][] costs) {
        int[] dp = new int[3];

        for(int i = 0; i < costs.length; i++) {
            int[] cur = new int[3];
            Arrays.fill(cur,Integer.MAX_VALUE);
            for (int j = 0; j < costs[0].length; j++) {
                for (int k = 0; k < costs[0].length; k++) {
                    if (j == k) continue;
                    cur[k] = Math.min(cur[k],dp[j]+costs[i][k]);
                }
            }
            for (int j = 0; j< 3; j++) {
                dp[j] = cur[j];
            }
        }

        int ret = dp[0];
        for (int i = 1; i < 3; i++) {
            ret = Math.min(ret, dp[i]);
        }

        return ret;

    }
}
