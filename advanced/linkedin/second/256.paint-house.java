/*
 * [256] Paint House
 *
 * https://leetcode.com/problems/paint-house
 *
 * algorithms
 * Easy (46.05%)
 * Total Accepted:    25.2K
 * Total Submissions: 54.7K
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
         if (costs.length == 0) {
             return 0;
         }
         int m  = costs.length;
         //idea: dynamic programming
         int[] recent =  new int[3];
         recent[0] = costs[0][0];
         recent[1] = costs[0][1];
         recent[2] = costs[0][2];

         for (int i = 1; i < m; i++) {
             int[] current = new int[3];
             for (int j = 0; j < 3; j++) {
                 int decision = Integer.MAX_VALUE;
                 for (int k = 0; k < 3; k++) {
                     if (k == j) continue;
                     decision = Math.min(decision, recent[k]);
                 }
                 current[j] = decision + costs[i][j];
                 //System.out.printf("current[%d] = %d\n",j,current[j]);
             }
             for (int j = 0; j < 3; j++) {
                 recent[j] = current[j];
             }
         }

         int ret = Integer.MAX_VALUE;
         for (int j = 0; j < 3; j++) {
             ret = Math.min(ret, recent[j]);
         }
         return ret;
    }
}
