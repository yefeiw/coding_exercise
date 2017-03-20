import java.util.*;

public class MinCoins {

	public static int numCoins(int amount, int[] coins) {
		if (!isValid(amount, coins)) {
			return -1;
		}
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		for (int i = 0; i < coins.length; i++) {
			if (coins[i] <= amount) {
				dp[coins[i]] = 1;
			}
		}
		dp[0] = -1;
		for (int i = 1; i <=amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i - coins[j] > 0) {
					dp[i] = Math.min(dp[i], dp[i-coins[j]]+ 1);
				}
			}
		}
		if(dp[amount] == Integer.MAX_VALUE) {
			return -1;
		} else {
			return dp[amount];
		}
	}
	public static boolean isValid(int amount, int[] coins) {
		if (amount <= 0) {
			return false;
		}
		if (null == coins || 0 == coins.length) {
			return false;
		}
		return true;
	}

	public static void main (String args[]) {
		int[] coins = {0,2,3,12};
		int amount = 11;
		System.out.println("Minimum coins required is "+ numCoins(amount, coins) );
 
 
	
	}
}