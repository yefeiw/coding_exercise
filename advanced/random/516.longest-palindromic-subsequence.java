class Solution {
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0) {
        	return 0;
        }
        //Strategy: DP
        int len = s.length();
        //dp[i][j]: LPS from index i to index j;
        int[][] dp = new int[len][len];
        //initial conditions: single characters
        for(int i = 0; i < len; i++) {
        	dp[i][i] = 1;
        }
        //main recursion
        //BUGWARNING: i need to decrement. Are you really familiar with DP?
        for(int i = len-1 ; i >= 0; i--) {
        	for(int j = i+1; j < len; j++) {
        		if (s.charAt(i) == s.charAt(j)) {
        			dp[i][j] = dp[i+1][j-1]+2;
        		} else {
        			dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
        		}
        	}
        }
        //returning
        return dp[0][len-1];
    }
}