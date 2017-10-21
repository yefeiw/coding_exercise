public class Solution {
    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            //max bound is all ones, thus we can do this step
            dp[i] = i;
            for (int j  = (int) Math.sqrt(i); j >=1; j--) {
                dp[i] = Math.min(dp[i-j*j]+1, dp[i]);
            }
        }
        return dp[n]; 
    }
}
