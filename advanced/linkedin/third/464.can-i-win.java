/*
 * [464] Can I Win
 *
 * https://leetcode.com/problems/can-i-win
 *
 * algorithms
 * Medium (25.05%)
 * Total Accepted:    15.1K
 * Total Submissions: 60.1K
 * Testcase Example:  '10\n11'
 *
 * In the "100 game," two players take turns adding, to a running total, any
 * integer from 1..10. The player who first causes the running total to reach
 * or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of
 * numbers of 1..15 without replacement until they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players
 * play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20
 * and desiredTotal will not be larger than 300.
 *
 *
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers
 * from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >=
 * desiredTotal.
 * Same with other integers chosen by the first player, the second player will
 * always win.
 *
 *
 */
class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            if (maxChoosableInteger >= desiredTotal) {
                return true;
            }
            int sum = 0;
            for (int i = 1; i <= maxChoosableInteger; i++) {
                sum += i;
            }

            if (sum < desiredTotal)  {
                return false;
            }

            return helper(new boolean[maxChoosableInteger+1],new HashMap(),desiredTotal);
    }

    private boolean helper(boolean[] used, Map<Integer,Boolean> cache, int desiredTotal) {
        if (desiredTotal <= 0) {
            return false;
        }
        int key = formKey(used);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        for (int i = 1; i < used.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            if (helper(used,cache, desiredTotal - i) == false) {
                used[i] = false;
                cache.put(key,true);
                return true;
            }
            used[i] = false;
        }
        //reaching here means that there is no must-win policy for any player.
        cache.put(key,false);
        return false;
    }

    private int formKey(boolean[] used) {
        int key = 0;
        for (int i = 0; i < used.length; i++) {
            key *= 2;
            if (used[i] == true) {
                key += 1;
            }
        }
        return key;
    }
}
