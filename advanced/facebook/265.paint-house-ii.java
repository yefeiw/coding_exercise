/*
 * [265] Paint House II
 *
 * https://leetcode.com/problems/paint-house-ii
 *
 * algorithms
 * Hard (38.03%)
 * Total Accepted:    22.8K
 * Total Submissions: 59.9K
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
        if (costs.length == 0 || costs[0].length == 0) {
        	return 0;
        }
        int m = costs.length;
        int n = costs[0].length;
        //idea: dyanamic programming
        //designation: dp[i][j] = minCost[0...i] and the ith house is of color j
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
        	Arrays.fill(row,Integer.MAX_VALUE);
        }
        //init: dp[0][color] = costs[0][color]
        for(int j = 0; j < n; j++) {
        	dp[0][j] = costs[0][j];
        }
        //recursion: dp[i][j] = min(dp[i-1][k!=j]+costs[i][j])
        for(int i = 1; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		for (int k = 0; k < n; k++) {
        			if (k ==j) continue;
        			dp[i][j] = Math.min(dp[i-1][k]+costs[i][j], dp[i][j]);
        		}
        	}
        }
        int ret = Integer.MAX_VALUE;
        //get the min of the last element
        for(int j = 0; j < n ; j++) {
        	ret = Math.min(ret,dp[m-1][j]);
        }
        return ret;

    }
}
/*
maintain the minimum two costs min1(smallest) and min2 (second to smallest) after painting i-th house.

    int minCostII(vector<vector<int>>& costs) {
        int n = costs.size();
        if(n==0) return 0;
        int k = costs[0].size();
        if(k==1) return costs[0][0];

        vector<int> dp(k, 0);
        int min1, min2;

        for(int i=0; i<n; ++i){
            int min1_old = (i==0)?0:min1;
            int min2_old = (i==0)?0:min2;
            min1 = INT_MAX;
            min2 = INT_MAX;
            for(int j=0; j<k; ++j){
                if(dp[j]!=min1_old || min1_old==min2_old){
                    dp[j] = min1_old + costs[i][j];
                }else{//min1_old occurred when painting house i-1 with color j, so it cannot be added to dp[j]
                    dp[j] = min2_old + costs[i][j];
                }

                if(min1<=dp[j]){
                    min2 = min(min2, dp[j]);
                }else{
                    min2 = min1;
                    min1 = dp[j];
                }
            }
        }

        return min1;
    }
    */
