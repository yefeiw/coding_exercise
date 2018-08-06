def maxA(N):
    if N < 7:
        return N
    dp = range(N+1)
    for i in xrange(7, N+1):
        dp[i % 6] = max(dp[(i-4) % 6]*3, dp[(i-5) % 6]*4)
    return dp[N % 6]


print(maxA(7))