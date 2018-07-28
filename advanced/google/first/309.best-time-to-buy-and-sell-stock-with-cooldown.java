/*
 * [309] Best Time to Buy and Sell Stock with Cooldown
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 *
 * algorithms
 * Medium (42.33%)
 * Total Accepted:    67K
 * Total Submissions: 157.8K
 * Testcase Example:  '[1,2,3,0,2]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times) with the following restrictions:
 * 
 * 
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1
 * day)
 * 
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3,0,2]
 * Output: 3 
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * 
 * 
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 1) {
            return 0;
        }
        //rest after {rest, sell}
        int s0 = 0;
        //buy after rest or rest after buy
        int s1 = -prices[0];
        //sell after rest
        int s2 = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            int pre0 = s0;
            int pre1 = s1;
            int pre2 = s2;
            s0 = Math.max(pre0, pre2);
            s1 = Math.max(pre0 - prices[i], pre1);
            s2 = pre1 + prices[i];
        }

        return Math.max(s0, s2);
    }
}
