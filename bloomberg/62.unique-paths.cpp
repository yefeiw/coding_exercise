class Solution {
public:
    int uniquePaths(int m, int n) {
        //input validation
        if (m < 1 || n < 1) {
            return 0;
        }
        vector<vector<int>> dp(m,vector<int>(n,0));
        //start is always 1
        dp[0][0] = 1;
        //setup first row
        for(int i = 1; i < m ; i++) {
            dp[i][0] = 1;
        }
        //setup first column
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }
        //furnish the array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        //return result
        return dp[m-1][n-1];

    }
};
