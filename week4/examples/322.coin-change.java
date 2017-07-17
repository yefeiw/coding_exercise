public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if(coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int coin : coins) {
            if (coin <= amount) {
                dp[coin] = 1;
            }
        }
        //going dp now
        for(int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin < i && dp[i-coin] < Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) return -1;
        else return dp[amount];
    }
}
