/*
 * [254] Factor Combinations
 *
 * https://leetcode.com/problems/factor-combinations
 *
 * algorithms
 * Medium (43.02%)
 * Total Accepted:    30.7K
 * Total Submissions: 71.5K
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
        return helper(n, 2);
    }

    //util function
    private List<List<Integer>> helper(int n, int start) {
        
        List<List<Integer>> ret = new ArrayList<>();

        for (int i = start; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                List<List<Integer>> deduce = helper(n / i, i);
                for (List<Integer> list : deduce) {
                    list.add(0,i);
                    ret.add(list);
                }
                List<Integer> itself =  new ArrayList<>();
                itself.add(i);
                itself.add(n / i);
                ret.add(itself);
            }
        }
        return ret;
    }
}
