/*
 * [414] Third Maximum Number
 *
 * https://leetcode.com/problems/third-maximum-number/description/
 *
 * algorithms
 * Easy (27.95%)
 * Total Accepted:    50K
 * Total Submissions: 178.9K
 * Testcase Example:  '[3,2,1]'
 *
 * Given a non-empty array of integers, return the third maximum number in this
 * array. If it does not exist, return the maximum number. The time complexity
 * must be in O(n).
 *
 * Example 1:
 *
 * Input: [3, 2, 1]
 *
 * Output: 1
 *
 * Explanation: The third maximum is 1.
 *
 *
 *
 * Example 2:
 *
 * Input: [1, 2]
 *
 * Output: 2
 *
 * Explanation: The third maximum does not exist, so the maximum (2) is
 * returned instead.
 *
 *
 *
 * Example 3:
 *
 * Input: [2, 2, 3, 1]
 *
 * Output: 1
 *
 * Explanation: Note that the third maximum here means the third maximum
 * distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 *
 *
 */
class Solution {
    public int thirdMax(int[] nums) {
        //There will be integer overflows, therefore we have to do null based.
        Integer[] largest = new Integer[3];
        Arrays.fill (largest,null);

        for (int i = 0; i < nums.length; i++) {
            Integer cand = nums[i];
            //equals takes care of null, while ++ will have problems in null dereference
            if (cand.equals(largest[0]) || cand.equals(largest[1]) || cand.equals(largest[2])) {
                //duplicates are never counted
                continue;
            }
            if (largest[0] == null || cand > largest[0]) {
                largest[2] = largest[1];
                largest[1] = largest[0];
                largest[0] = cand;
            } else if (largest[1] == null || cand > largest[1]) {
                largest[2] = largest[1];
                largest[1] = cand;
            } else if (largest[2] == null || cand > largest[2]) {
                largest[2] = cand;
            }
        }

        if (largest[2] == null ) {
            return largest[0];
        } else {
            return largest[2];
        }
    }
}
