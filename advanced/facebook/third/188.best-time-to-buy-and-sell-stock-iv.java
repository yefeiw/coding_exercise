/*
* [188] Best Time to Buy and Sell Stock IV
*
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv
*
* algorithms
* Hard (24.50%)
* Total Accepted:    52.5K
* Total Submissions: 214.4K
* Testcase Example:  '2\n[]'
*
* Say you have an array for which the ith element is the price of a given
* stock on day i.
*
* Design an algorithm to find the maximum profit. You may complete at most k
* transactions.
*
* Note:
* You may not engage in multiple transactions at the same time (ie, you must
* sell the stock before you buy again).
*
* Credits:Special thanks to @Freezen for adding this problem and creating all
* test cases.
*/
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k > prices.length / 2) {
            return maxProfitInfinite(prices);
        }

        int[][] mustSell = new int[prices.length+1][k+1];
        int[][] max = new int[prices.length+1][k+1];

        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i-1];
            for (int j = 1; j <= k; j++) {
                mustSell[i][j] = Math.max(max[i-1][j-1]+ profit, mustSell[i-1][j]+ profit);
                max[i][j] = Math.max(max[i-1][j],mustSell[i][j]);
            }
        }
        return max[prices.length -1][k];
    }

    public int maxProfitInfinite (int[] prices) {
        int ret = 0;
        for (int i = 1 ; i < prices.length; i++) {
            int diff = prices[i] - prices[i-1];
            if (diff > 0) {
                ret += diff;
            }
        }
        return ret;
    }
}
