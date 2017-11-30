/*
 * [254] Factor Combinations
 *
 * https://leetcode.com/problems/factor-combinations
 *
 * algorithms
 * Medium (42.75%)
 * Total Accepted:    28.5K
 * Total Submissions: 66.8K
 * Testcase Example:  '12'
 *
 * Numbers can be regarded as product of its factors. For example,
 *
 * 8 = 2 x 2 x 2;
 * ⁠ = 2 x 4.
 *
 * Write a function that takes an integer n and return all possible
 * combinations of its factors.
 *
 *
 * Note:
 *
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 *
 *
 *
 * Examples:
 * ⁠input: 1
 * ⁠output:
 *
 * []
 *
 * input: 37
 * ⁠output:
 *
 * []
 *
 * ⁠input: 12
 * ⁠output:
 *
 * [
 * ⁠ [2, 6],
 * ⁠ [2, 2, 3],
 * ⁠ [3, 4]
 * ]
 *
 * ⁠input: 32
 * ⁠output:
 *
 * [
 * ⁠ [2, 16],
 * ⁠ [2, 2, 8],
 * ⁠ [2, 2, 2, 4],
 * ⁠ [2, 2, 2, 2, 2],
 * ⁠ [2, 4, 4],
 * ⁠ [4, 8]
 * ]
 *
 *
 */
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ret = new ArrayList();
        if (n < 2) {
            return ret;
        }
        //alternative solution: standard backtracking, and always remove the last element
        //first: backtracking
        helper(n, 2, ret,new ArrayList());
        //second: remove the last element
        ret.remove(ret.size() -1);
        return ret;
    }

    //util functions
    private void helper(int n , int start, List<List<Integer>> ret, List<Integer> stack) {
        if (n == 1) {
            ret.add(new ArrayList(stack));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                stack.add(i);
                helper(n/i,i,ret,stack);
                stack.remove(stack.size() - 1);
            }
        }
    }
}
